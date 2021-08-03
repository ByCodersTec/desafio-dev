from django.contrib import admin
from .models import Transaction, TransactionType


admin.register(Transaction, TransactionType)
