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
                'valor': float(line[9:19]) / 100.00,
                'cpf': line[19:30],
                'cartao': line[30:42],
                'hora_transacao': datetime.time(int(line[42:44]), int(line[44:46]), int(line[46:48])),
                'nome_dono_loja': line[48:62],
                'nome_loja': line[62:81],
            }
