from django.contrib.auth.models import User
from django.core.files.uploadedfile import InMemoryUploadedFile

from .models import Transaction, TransactionType
from .utils import TRANSACTION_TYPES, get_transaction_data


class CnabServices:
    def __init__(self, cnab_file: InMemoryUploadedFile) -> None:
        self.file = cnab_file

    def parse_transactions(self) -> list[dict]:
        transaction_list = []

        with self.file.open(mode="rb") as f:
            for line in f:
                log_line = line.decode("UTF-8")
                transaction_list.append(get_transaction_data(log_line))

        return transaction_list

    def register_transactions(self, transactions: list, user_pk) -> None:
        user = User.objects.get(pk=user_pk)

        for transaction in transactions:
            type_data: dict = TRANSACTION_TYPES[transaction.pop("type_ref")]

            transaction_type, created = TransactionType.objects.get_or_create(
                **type_data
            )
            Transaction.objects.create(
                **transaction, type=transaction_type, manager=user
            )

    @staticmethod
    def get_user_balance(transaction_list: list[dict]):
        balance = 0

        for transaction in transaction_list:
            if transaction["type"]["nature"] == "+":
                balance += transaction["value"]

            elif transaction["type"]["nature"] == "-":
                balance -= transaction["value"]

        return balance
