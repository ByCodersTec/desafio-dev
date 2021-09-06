from rest_framework.decorators import action
from rest_framework import viewsets

from core.models import (
    Cnab
)
from core.serializers.serializer import (
    CnabSerializer
)

class ImportadorViewSet(viewsets.ModelViewSet):
    queryset = Cnab
    serializer_class = CnabSerializer

    @action(detail=False, methods=["post"], url_path="cnab")
    def importar_cnab(self, request, *args, **kwargs):
        
        print("============")
        print(request.FILES.getlist('file'))
        print("============")
        #serializer = PonteirosSerializer(queryset)
        #return Response(serializer.data)