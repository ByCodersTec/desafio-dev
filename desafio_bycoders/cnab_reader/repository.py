from .models import cnabModel

class CnabRepository():
    def __init__(self):
        self.cnab_model = cnabModel

    def create_transation(self, cnab_formatter):
        cnab_model = self.cnab_model(**cnab_formatter)
        cnab_model.save()

    def get_all_transactions(self):
        return self.cnab_model.objects.all()

    def get_transaction_by_id(self, id):
        return self.cnab_model.objects.get(id=id)

    def get_transaction_by_cpf(self, cpf):
        return self.cnab_model.objects.filter(cpf=cpf)