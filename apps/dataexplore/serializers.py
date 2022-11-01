from rest_framework import serializers
from apps.dataexplore.models import TransactionModel, TransactionTypeModel



class TransactionTypeSerializer(serializers.ModelSerializer):
    class Meta:
        model = TransactionTypeModel
        fields = ['name', 'mode', 'signal']

class TransactionSerializer(serializers.ModelSerializer):
    class Meta:
        model = TransactionModel
        fields = ['transaction', 'transaction_occurrence_date', 'transaction_value', 'client_cpf']

