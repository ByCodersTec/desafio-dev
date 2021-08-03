from prettytable import PrettyTable

from django.contrib.auth.models import User
from django.core.files.uploadedfile import InMemoryUploadedFile

from .models import Transaction, TransactionType
from .utils import TRANSACTION_TYPES, get_transaction_data, get_cnab_field_data


class CnabServices:
    def __init__(self, cnab_file: InMemoryUploadedFile) -> None:
        self.file = cnab_file

    def parse_transactions(self) -> list[dict]:
        transaction_list = []

        with self.file.open(mode="rb") as f:
            for line in f:
                log_line = line.decode("UTF-8")
                transaction_data = get_transaction_data(log_line)

                if transaction_data:
                    transaction_list.append(transaction_data)

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

    @staticmethod
    def get_cnab_html_table(transaction_list: list[dict], balance: int):
        table = PrettyTable()
        table.format = True

        cnab_field_names = [
            "Tipo",
            "Descrição",
            "Valor",
            "Data",
            "Hora",
            "CPF Beneficiário",
            "Cartão",
            "Dono da Loja",
            "Nome da Loja",
            "Saldo",
        ]
        table.field_names = cnab_field_names

        for idx, transaction in enumerate(transaction_list):
            transaction["balance"] = (
                balance if idx == len(transaction_list) - 1 else "--"
            )
            cnab_field_data = get_cnab_field_data(transaction)

            if cnab_field_data:
                table.add_row(cnab_field_data)

        return table.get_html_string()
