from datetime import datetime

from .models import TransactionNature


TRANSACTION_TYPES = {
    "1": {"type_ref": 1, "description": "Débito", "nature": TransactionNature.ADD},
    "2": {"type_ref": 2, "description": "Boleto", "nature": TransactionNature.SUB},
    "3": {
        "type_ref": 3,
        "description": "Financiamento",
        "nature": TransactionNature.SUB,
    },
    "4": {"type_ref": 4, "description": "Crédito", "nature": TransactionNature.ADD},
    "5": {
        "type_ref": 5,
        "description": "Recebimento Empréstimo",
        "nature": TransactionNature.ADD,
    },
    "6": {"type_ref": 6, "description": "Vendas", "nature": TransactionNature.ADD},
    "7": {
        "type_ref": 7,
        "description": "Recebimento TED",
        "nature": TransactionNature.ADD,
    },
    "8": {
        "type_ref": 8,
        "description": "Recebimento DOC",
        "nature": TransactionNature.ADD,
    },
    "9": {"type_ref": 9, "description": "Aluguel", "nature": TransactionNature.SUB},
}


def get_transaction_data(log_line: str) -> dict:
    idx_date = slice(1, 9)
    idx_value = slice(9, 19)
    idx_cpf = slice(19, 30)
    idx_card = slice(30, 42)
    idx_time = slice(42, 48)
    idx_store_owner = slice(48, 62)
    idx_store_name = slice(62, 81)

    log_datetime = datetime.strptime(
        log_line[idx_date] + log_line[idx_time], "%Y%m%d%H%M%S"
    )

    return {
        "type_ref": log_line[0],
        "value": int(log_line[idx_value]) / 100,
        "date": log_datetime.date(),
        "time": log_datetime.time(),
        "recipient_cpf": int(log_line[idx_cpf]),
        "card": log_line[idx_card],
        "store_owner": log_line[idx_store_owner].strip().title(),
        "store_name": log_line[idx_store_name].strip().title(),
    }
