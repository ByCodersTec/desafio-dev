from ..repository import CnabRepository

class OperationsListUseCase:
    def __init__(self):
        self.repository = CnabRepository()

    def execute(self, store_name):
        if store_name:
            return self.repository.get_transaction_by_store_name(store_name)
        else:
            return self.repository.get_all_transactions()