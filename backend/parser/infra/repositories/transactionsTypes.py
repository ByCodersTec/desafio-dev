from providers.api import API


class TransactionsTypesRepository(API):

    def get_transactions_types(self):
        response, data = self.get("transactions-types")
        if not response:
            return {}

        return {d["code"]: d["id"] for d in data}