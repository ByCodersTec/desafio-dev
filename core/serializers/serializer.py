from rest_framework import serializers
from core.models import Cnab, TipoTransacao

class DynamicFieldsModelSerializer(serializers.ModelSerializer):
    """
    A ModelSerializer that takes an additional `fields` argument that
    controls which fields should be displayed.
    """

    def __init__(self, *args, **kwargs):
        fields = self.return_fields(**kwargs)
        kwargs.pop("fields", None)

        # Instantiate the superclass normally
        super(DynamicFieldsModelSerializer, self).__init__(*args, **kwargs)

        if fields is not None:
            # Drop any fields that are not specified in the `fields` argument.
            allowed = set(fields["fields"])
            existing = set(self.fields)
            for field_name in existing - allowed:
                self.fields.pop(field_name)

    def return_fields(self, **kwargs):
        # Don't pass the 'fields' arg up to the superclass
        # se for apenas 1 valor no campo fields, ele não vira string e
        # permanece como tupla
        if "fields" in kwargs:
            fields = (
                {"fields": (kwargs["fields"],)}
                if isinstance(kwargs["fields"], str)
                else {"fields": kwargs["fields"]}
            )
            return fields
        return None

        
class TipoTransacaoSerializer(DynamicFieldsModelSerializer, serializers.ModelSerializer):
    class Meta:
        model = TipoTransacao
        fields = "__all__"

class CnabSerializer(DynamicFieldsModelSerializer, serializers.ModelSerializer):
    tipo_transacao = TipoTransacaoSerializer(source='tipo', fields={"tipo", "descricao", "naturesa", "sinal"}) 
    class Meta:
        model = Cnab
        fields = "__all__"

