import datetime
from queue import Empty

class CnabFormatter():
    """
    Classe responsável por formatar o arquivo de retorno do banco
    """

    def cnab_formatted(self, line):
        if line is not None:
            return {
                'tipo_transacao': line[0:1],
                'data': datetime.date(int(line[1:5]), int(line[5:7]), int(line[7:9])),
                'valor':  self.__transaction_in_out(int(line[0:1]), float(line[9:19]) / -100.00),
                'cpf': line[19:30],
                'cartao': line[30:42],
                'hora_transacao': datetime.time(int(line[42:44]), int(line[44:46]), int(line[46:48])),
                'nome_dono_loja': line[48:62],
                'nome_loja': line[62:81],
            }


    def query_formatted(self, query):
        if query is not None:
            return {
                'tipo_transacao': self.__transaction_type_formatter(query.tipo_transacao),
                'data': query.data,
                'valor': query.valor,
                'cpf': query.cpf,
                'cartao': query.cartao,
                'hora_transacao': query.hora_transacao,
                'nome_dono_loja': query.nome_dono_loja,
                'nome_loja': query.nome_loja,
            }

    def __transaction_type_formatter(self, transaction_type):
        if transaction_type == 1:
            return "Débito"
        elif transaction_type == 2:
            return "Boleto"
        elif transaction_type == 3:
            return "Financiamento"
        elif transaction_type == 4:
            return "Crédito"
        elif transaction_type == 5:
            return "Recebimento Empréstimo"
        elif transaction_type == 6:
            return "Vendas"
        elif transaction_type == 7:
            return "Recebimentos TED"
        elif transaction_type == 8:
            return "Recebimentos DOC"
        elif transaction_type == 9:
            return "Aluguel"

    def __transaction_in_out(self, transaction_type, value):
        if transaction_type in [2,3,9]:
            print('negativo')
            return value
        else:
            print('positivo')
            return abs(value)
        