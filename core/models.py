from django.db import models

# Create your models here.

class TipoTransacao(models.Model):
    tipo = models.IntegerField(primary_key=True)
    descricao = models.CharField(max_length=22, blank=False, null=False)
    natureza = models.CharField(max_length=7, blank=False, null=False)
    sinal = models.CharField(max_length=1, blank=False, null=False)

class Cnab(models.Model):
    id = models.AutoField(primary_key=True)
    data_ocorrencia = models.DateField(blank=True, null=True)
    valor = models.DecimalField(
        max_digits=15,
        decimal_places=2,
        blank=True,
        null=True,
    )
    cpf = models.BigIntegerField(
        blank=True, null=True
    )
    cartao = models.CharField(
        max_length=12, blank=True, null=True
    )
    hora = models.TimeField()
    dono_loja = models.CharField(max_length=14, blank=True, null=True)
    nome_loja = models.CharField(max_length=19, blank=True, null=True)
    tipo = models.ForeignKey(TipoTransacao, on_delete=models.CASCADE)

