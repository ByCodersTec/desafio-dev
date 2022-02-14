from providers.api import API


class TransactionsRepository(API):

    enpoint = "transactions"

    def create_transaction(self, data):
        _, res = self.post(self.enpoint, data)

        if not _:
            print(res)
            return False

        return res
