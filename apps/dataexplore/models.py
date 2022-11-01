"""Here the data-explore models are defined."""
from django.db import models


class DataModel(models.Model):
    """This class define the data model."""
    description = models.CharField(max_length=255, blank=True)
    filename = models.FileField(upload_to='files/', unique=True)
    date_created = models.DateTimeField(auto_now_add=True)

    def __str__(self) -> str:
        return str(self.filename)

class TransactionTypeModel(models.Model):
    """This class define the transaction type model."""
    name = models.CharField(max_length=50, unique=True)
    mode = models.CharField(max_length=20, blank=True)
    signal = models.CharField(max_length=1, blank=True)

    def __str__(self) -> str:
        return str(self.name)

class TransactionModel(models.Model):
    """This class define the transaction model."""
    transaction = models.ForeignKey(TransactionTypeModel, on_delete=models.CASCADE, related_name='transaction')
    transaction_occurrence_date =  models.CharField(max_length=10, blank=True)
    transaction_value = models.FloatField(default=0.0)
    client_cpf = models.CharField(max_length=11, blank=True)
    client_credit_card = models.CharField(max_length=12, blank=True)
    transaction_hour = models.IntegerField(default=0)
    store_owner = models.CharField(max_length=250, blank=True)
    store_name = models.CharField(max_length=250, blank=True)

    def __str__(self) -> str:
        return str(self.transaction)

