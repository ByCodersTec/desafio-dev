from django.db import models

class DataModel(models.Model):
    """This class define the data model."""
    description = models.CharField(max_length=255, blank=True)
    filename = models.FileField(upload_to='files/', unique=True)
    date_created = models.DateTimeField(auto_now_add=True)

    def __str__(self) -> str:
        return str(self.filename)

class TransactionModel(models.Model):
    pass

class TransactionTypeModel(models.Model):
    pass
