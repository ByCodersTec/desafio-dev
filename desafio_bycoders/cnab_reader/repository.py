from .models import cnabModel
from .formatter import CnabFormatter
from django.db.models import Sum

class CnabRepository():
    def __init__(self):
        self.cnab_model = cnabModel
        self.cnab_formatter = CnabFormatter()

    def create_transation(self, cnab_formatter):
        cnab_model = self.cnab_model(**cnab_formatter)
        cnab_model.save()

    def get_all_transactions(self):
        query = self.cnab_model.objects.all().order_by('nome_loja')
        formatted_query = []
        for item in query:
            item = self.cnab_formatter.query_formatted(item)
            formatted_query = formatted_query + [item]
        return formatted_query

    def get_transaction_by_id(self, id):
        return self.cnab_model.objects.get(id=id)

    def get_transaction_by_store_name(self, store_name):
        query = self.cnab_model.objects.filter(nome_loja=store_name).order_by('nome_loja')
        query_total_value = query.aggregate(Sum('valor'))
        query_total_value =  str(round(query_total_value['valor__sum'], 2))
        formatted_query = []
        for item in query:
            item = self.cnab_formatter.query_formatted(item)
            formatted_query = formatted_query + [item]
        return formatted_query, query_total_value