from django.core.validators import RegexValidator
from django.db import models
from django.utils.timezone import utc
from django.utils.translation import gettext_lazy as _
class Transaction(models.Model):
    class TransactionType(models.TextChoices):
        DEBITO = '1', _('Débito')
        BOLETO = '2', _('Boleto')
        FINANCIAMENTO = '3', _('Financiamento')
        CREDITO = '4', _('Crédito')
        RECEBIMENTO_EMPRESTIMO = '5', _('Recebimento Empréstimo')
        VENDAS = '6', _('Vendas')
        RECEBIMENTO_TED = '7', _('Recebimento TED')
        RECEBIMENTO_DOC = '8', _('Recebimento DOC')
        ALUGUEL = '9', _('Aluguel')

    type = models.CharField(
        max_length=1,
        choices=TransactionType.choices,
    )
    datetime = models.DateTimeField()
    amount = models.DecimalField(decimal_places=2, max_digits=10)
    nin = models.CharField(max_length=11, validators=[RegexValidator('^([0-9]{11})*$', message=_('Invalid NIN'))])
    card = models.CharField(max_length=12, validators=[RegexValidator('^([0-9]{4}[\*|0-9]{4}[0-9]{4})*$', message=_('Invalid Card'))])
    store_owner = models.CharField(max_length=14)
    store = models.CharField(max_length=19)
    created_at = models.DateTimeField(auto_now=True, blank=True)

    def __str__(self):
        return f'CPF:{self.nin}/Card:{self.card} paid ${self.amount} at {self.datetime} to {self.store}({self.store_owner})'

    @property
    def amount_with_operation(self):
        if self.type in negative_types():
            return self.amount * -1
        return self.amount

    def save(self):
        super().save(self)


def negative_types():
        return [Transaction.TransactionType.BOLETO, Transaction.TransactionType.FINANCIAMENTO, Transaction.TransactionType.ALUGUEL]


class TransactionError(models.Model):
    description = models.TextField(null=False)
    detail = models.TextField(null=True)
    created_at = models.DateTimeField(auto_now=True, blank=True)

    def __str__(self) -> str:
        return f'Description:{self.description}|{self.detail}'