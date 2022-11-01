

class Information():
    def __init__(self, row=None):
        self.row = row

    def fields_map(self):
        try:
            information_map = {
                'transaction': int(self.row[0]),
                'transaction_occurrence_date': f'{self.row[1:5]}-{self.row[5:7]}-{self.row[7:9]}',
                'transaction_value': int(self.row[9:19]) / 100,
                'client_cpf': self.row[19:30],
                'client_credit_card': self.row[30:42],
                'transaction_hour': int(self.row[42:48]),
                'store_owner': self.row[48:62],
                'store_name': self.row[62:82]
            }
            return information_map
        except Exception as error:
            return error


    @staticmethod
    def get_standard_transactions_type():
        description = ["Débito", "Boleto", "Financiamento",
                       "Crédito", "Recebimento Empréstimo",
                       "Vendas", "Recebimento TED", "Recebimento DOC",
                       "Aluguel"]

        mode = ["Entrada", "Saída", "Saída", "Entrada",
                "Entrada", "Entrada", "Entrada", "Entrada", "Saída"]

        signal = ["+", "-", "-", "+", "+", "+", "+", "+", "-"]

        dict_type_transactions = [] 
        for ind, i in enumerate(description):
            res = {}
            fields = {}
            res['model'] = 'dataexplore.transactiontypemodel'
            res['pk'] = ind+1
            fields['name'] = i
            fields['mode'] = mode[ind]
            fields['signal'] = signal[ind]
            res['fields'] = fields
            dict_type_transactions.append(res)
        return dict_type_transactions
