from django.core.files.uploadedfile import SimpleUploadedFile
from django.test import Client
from rest_framework import status as Status
from core.actions.importador import UtilsViewSet
from core.models import Cnab
import unittest


class TestImportador(unittest.TestCase):

    def setUp(self):
        self.client = Client()
    
    def test_cnab_deve_retornar_status_422_caso_nao_tenha_parametro(self):
        url = "/api/importador/cnab/"
        data = {"file": ""}
        response = self.client.post(url, data)
        self.assertEqual(response.status_code, Status.HTTP_422_UNPROCESSABLE_ENTITY)

    def test_cnab_deve_retornar_status_200_caso_consiga_processar(self):
        url = "/api/importador/cnab/"
        txt = open("CNAB.txt", "r") 
        data = {"file": txt}
        response = self.client.post(url, data)
        self.assertEqual(response.status_code, Status.HTTP_200_OK)
    
    def test_cnab_deve_retornar_status_200_caso_consiga_processar(self):
        url = "/api/importador/get_all_cnab/"
        response = self.client.get(url)
        self.assertEqual(response.status_code, Status.HTTP_200_OK)
    


class TestUtils(unittest.TestCase):

    def test_salva_arquivo_deve_levantar_exception_caso_nao_consiga_salvar(self):
        parameters = ["", None]
        with self.subTest(parameters=parameters):
            for parameter in parameters:
                self.assertRaises(Exception, UtilsViewSet.salva_arquivo, parameter)

    def test_salva_arquivo_deve_retornar_erro_false_caso_consiga_salvar(self):
        txt = open("CNAB.txt", "r") 
        data = UtilsViewSet.salva_arquivo(txt)
        self.assertFalse(data["erro"])


    def test_cria_instancia_deve_retornar_uma_instancia_caso_nao_possua_erros(self):
        linha = "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO"
        data = UtilsViewSet.cria_instancia(linha)
        self.assertIsInstance(Cnab, data)

    #def test_cria_instancia_deve_retornar_deve_levantar_uma_exception_caso_o_parametro_esteja_errado(self):
    #    self.assertRaises(Exception, UtilsViewSet.cria_instancia)
        
        

    