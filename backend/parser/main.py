import base64
import os
import time

import kafka.errors
from kafka import KafkaConsumer

from entities.transactions import Transactions
from infra.repositories.stores import StoresRepository
from infra.repositories.parserStatus import ParserStatusRepository
from infra.repositories.transactions import TransactionsRepository
from infra.repositories.transactionsTypes import TransactionsTypesRepository
from helpers.transaction import get_transaction_block

TOPIC_NAME = os.environ['KAFKA_TOPIC_NAME']
BOOTSTRAP_SERVER = f"{os.environ['KAFKA_INTERNAL_HOST']}:{os.environ['KAFKA_INTERNAL_PORT']}"

stores_repository = StoresRepository()
transactions_repository = TransactionsRepository()
parser_status_repository = ParserStatusRepository()
transactions_types_repository = TransactionsTypesRepository()


def get_parsed_transactions_block(key: str, value: str) -> dict:
    transaction_types = transactions_types_repository.get_transactions_types()

    if len(transaction_types) == 0:
        raise "Transactions Types is needed to parse transactions"

    block_of_transaction = {}
    for line in value.split("\n"):
        if line.strip("") == "":
            continue
        single_transaction = get_transaction_block(line)
        single_transaction.update(
            {"transactionTypeId": transaction_types[single_transaction["transactionType"]], "parserStatusId": key})

        transaction = Transactions(single_transaction)

        if transaction.getStoreId() not in block_of_transaction:
            block_of_transaction[transaction.getStoreId()] = {
                "store": {
                    "id": transaction.getStoreId(),
                    "storeName": transaction.getStoreName(),
                    "storeOwner": transaction.getStoreOwner()
                },
                "transactions": []
            }

        block_of_transaction[transaction.getStoreId()]["transactions"].append(transaction)

    return block_of_transaction


def send_transactions_to_server(transactions):
    processed = 0
    for storeId, data in transactions.items():
        has_store = stores_repository.get_store(storeId)
        if not has_store:
            stores_repository.create_store(data["store"])

        processed += len(data["transactions"])
        for transaction in data["transactions"]:
            transaction: Transactions
            try:
                transactions_repository.create_transaction(transaction.__dict__)
            except Exception as e:
                print("Error in transaction creation: ", e)

    return processed


def parser_transaction(transaction):
    key = transaction.key.decode("utf8")
    value = base64.b64decode(str(transaction.value.decode("utf8"))).decode('utf-8')
    parser_status_repository.update_parser_status(key, status="processing", message="Parsing transactions block...")

    try:
        parsed_transactions_block = get_parsed_transactions_block(key, value)

        if len(parsed_transactions_block) == 0:
            raise "No parsed transactions"

        processed = send_transactions_to_server(parsed_transactions_block)
        parser_status_repository.update_parser_status(key, status="complete", message="Successfully", count=processed)

    except Exception as e:
        parser_status_repository.update_parser_status(key, status="error", message=e)


def start_consumer():
    wait_for_transactions = []

    try:
        wait_for_transactions = KafkaConsumer(TOPIC_NAME, bootstrap_servers=BOOTSTRAP_SERVER, reconnect_backoff_ms=5000)
    except kafka.errors.NoBrokersAvailable as nobroker:
        print(nobroker)
        while True:
            print("Trying reconnect ...")
            time.sleep(5)
            break

    for transation in wait_for_transactions:
        parser_transaction(transation)


if __name__ == "__main__":
    start_consumer()
