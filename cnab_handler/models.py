from django.db import models
from django.contrib.auth.models import User

from django.utils.translation import ugettext_lazy as _
from django.core.validators import MinValueValidator, MaxValueValidator


class TransactionNature(models.TextChoices):
    ADD = ("+", _("Entrada"))
    SUB = ("-", _("Saída"))


class TransactionType(models.Model):

    type_ref = models.IntegerField(
        validators=[MinValueValidator(1), MaxValueValidator(9)]
    )
    description = models.CharField(max_length=120)
    nature = models.CharField(choices=TransactionNature.choices, max_length=120)


class Transaction(models.Model):
    value = models.IntegerField(help_text=_("Valor da movimentação."))
    date = models.DateField(help_text=_("Data da ocorrência"))
    time = models.TimeField(
        help_text=_("Hora da ocorrência atendendo ao fuso de UTC-3")
    )

    recipient_cpf = models.IntegerField(help_text=_("CPF do beneficiário"))
    card = models.CharField(
        max_length=120, help_text=_("Cartão utilizado na transação")
    )

    store_owner = models.CharField(
        max_length=120, help_text=_("Nome do representante da loja")
    )
    store_name = models.CharField(max_length=120, help_text=_("Nome da loja"))

    type = models.ForeignKey(
        TransactionType, on_delete=models.PROTECT, related_name="transactions"
    )
    manager = models.ForeignKey(
        User, on_delete=models.CASCADE, related_name="transactions"
    )
