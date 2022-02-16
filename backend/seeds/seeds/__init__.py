from seeds.transactionsTypes import getTransactionsTypesData


def get_seeds():
    return {
        "TransactionsTypes": getTransactionsTypesData()
    }
