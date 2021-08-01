from rest_framework import serializers

from .models import Transaction, TransactionType


class CnabFileSerializer(serializers.Serializer):
    file = serializers.FileField(label="Arquivo CNAB")


class TransactionTypeSerializer(serializers.ModelSerializer):
    class Meta:
        model = TransactionType
        fields = [
            "type_ref",
            "description",
            "nature",
        ]


class TransactionSerializer(serializers.ModelSerializer):
    type = TransactionTypeSerializer()

    class Meta:
        model = Transaction
        fields = [
            "value",
            "date",
            "time",
            "recipient_cpf",
            "card",
            "store_owner",
            "store_name",
            "type",
        ]
        depth = 1
