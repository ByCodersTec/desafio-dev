from django.db import models
from datetime import datetime

class DataModel(models.Model):
    """This class define the data model."""
    filename = models.CharField(max_length=255)
    date_created = models.DateTimeField(default=datetime.now)
    last_update = models.DateTimeField(default=datetime.now, blank=True)

    def __str__(self) -> str:
        return str(self.filename)

class TransactionModel(models.Model):
    pass

class TransactionTypeModel(models.Model):
    pass
