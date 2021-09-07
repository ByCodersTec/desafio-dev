from rest_framework.decorators import action
from rest_framework import viewsets
from rest_framework import status as Status
from django.http import JsonResponse
from django.core.files.storage import FileSystemStorage
from django.shortcuts import get_object_or_404
from django.forms.models import model_to_dict
from decimal import Decimal
from core.models import (
    Cnab, TipoTransacao
)
from core.serializers.serializer import (
    CnabSerializer
)
from pprint import pprint


class UtilsViewSet:

    @staticmethod
    def salva_arquivo(file):
        erro = False
        try:
            fs = FileSystemStorage(location='./media/')
            arquivo = fs.save(file.name, file)
        except Exception as e:
            print(e)
            erro = True
        
        return {"arquivo": arquivo, "erro": erro}

    @staticmethod
    def cria_instancia(linha):
        
        #if(len(linha) < 80):
        #    raise Exception("A Linha não contém o número de caracteres necessário para o processamento.")

        normalizacao = {
            1: {"inicio": 0, "fim": 1},
            2: {"inicio": 1, "fim": 9},
            3: {"inicio": 9, "fim": 19},
            4: {"inicio": 19, "fim": 30},
            5: {"inicio": 30, "fim": 42},
            6: {"inicio": 42, "fim": 48},
            8: {"inicio": 48, "fim": 61},
            7: {"inicio": 62, "fim": 81},
        }
        header = iter(["tipo", "data_ocorrencia", "valor", "cpf", "cartao", "hora", "dono_loja", "nome_loja"])
        data = {}
        for key, value in normalizacao.items():
            valor_extraido = linha[ value["inicio"]: value["fim"] ]
            data.update(
                { 
                    next(header): valor_extraido 
                }
            )
        
        data["tipo"] = get_object_or_404(TipoTransacao, tipo=int( data["tipo"] ))
        ano, mes, dia  = data["data_ocorrencia"][0:4], data["data_ocorrencia"][4:6], data["data_ocorrencia"][6:8] 
        data["data_ocorrencia"] = f'{ano}-{mes}-{dia}'
        hora, minuto, segundo = data["hora"][0:2], data["hora"][2:4], data["hora"][4:6]
        data["hora"] = f"{hora}:{minuto}:{segundo}"
        data["valor"] = Decimal(data["valor"]) / 100

        return Cnab(
            **data
        )

class ImportadorViewSet(viewsets.ModelViewSet):
    queryset = Cnab
    serializer_class = CnabSerializer

    @action(detail=False, methods=["post"], url_path="cnab")
    def importar_cnab(self, request, *args, **kwargs):
        if(len(request.FILES.getlist('file')) == 0 ):
            return JsonResponse({}, safe=False, status=Status.HTTP_422_UNPROCESSABLE_ENTITY)

        file = request.FILES.getlist('file')[0]
        data = UtilsViewSet.salva_arquivo(file)
        lista_instancia = [] 
        if(not data["erro"]):
            f = open("media/"+data["arquivo"], encoding='utf-8', errors='ignore')
            linhas = f.readlines()
            lista_instancia += [ UtilsViewSet.cria_instancia(linha) for linha in linhas ]

        self.queryset.objects.bulk_create(lista_instancia)
        serializer = self.serializer_class(lista_instancia, fields={
            "tipo_transacao"
        }, many=True)
        
        return JsonResponse(serializer.data, safe=False)
