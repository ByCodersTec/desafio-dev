from django.db import models

# Create your models here.
class cnabModel(models.Model):
    tipo_transacao = models.IntegerField()	
    data = models.DateField()
    valor = models.DecimalField(max_digits=15, decimal_places=2)
    cpf = models.CharField(max_length=11)
    cartao = models.CharField(max_length=16)
    hora_transacao = models.TimeField()
    nome_dono_loja = models.CharField(max_length=50)
    nome_loja = models.CharField(max_length=50)
