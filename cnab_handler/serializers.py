from rest_framework import serializers

from .models import Transaction, TransactionType


class CnabFileSerializer(serializers.Serializer):
    file = serializers.FileField(label="Arquivo CNAB")


class TransactionTypeSerializer(serializers.ModelSerializer):
    class Meta:
        model = TransactionType
        fields = [
            "type_id",
            "description",
            "nature",
        ]


class TransactionSerializer(serializers.ModelSerializer):
    type = TransactionTypeSerializer()

    class Meta:
        model = Transaction
        fields = "__all__"
        depth = 1
