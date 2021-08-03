import datetime

from unittest import TestCase
from django.core.files.uploadedfile import SimpleUploadedFile


from cnab_handler.services import (
    CnabServices,
    get_transaction_data,
    get_cnab_field_data,
)


class TestServices(TestCase):
    def setUp(self):
        f_content = """
        3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO    \n
        5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ
        """
        cnab_file = SimpleUploadedFile("test_cnab", f_content.encode("UTF-8"))

        f_invalid_content = "32**BAR---   3232648945564\nasnjcuia867623123***sdcsdc--"

        invalid_cnab_file = SimpleUploadedFile(
            "invalid_test_cnab", f_invalid_content.encode("UTF-8")
        )
        self.cnab_handler = CnabServices(cnab_file)
        self.invalid_cnab_handler = CnabServices(invalid_cnab_file)

    def test_should_return_transaction_data(self):
        given_line = "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO    "  # noqa
        expected_data = {
            "value": 142.0,
            "date": datetime.date(2019, 3, 1),
            "time": datetime.time(15, 34, 53),
            "recipient_cpf": 9620676017,
            "card": "4753****3153",
            "store_owner": "João Macedo",
            "store_name": "Bar Do João",
            "type_ref": "3",
        }

        returned = get_transaction_data(given_line)

        self.assertEqual(returned, expected_data)

    def test_should_return_empty_transaction_with_invalid_input(self):
        given_line = "0asxc****21316  -- JJEEd***asd123   "
        expected_data = {}

        returned = get_transaction_data(given_line)

        self.assertEqual(returned, expected_data)

    def test_should_return_cnab_field_values(self):
        given_cnab = {
            "value": 142,
            "date": "2019-03-01",
            "time": "15:34:53",
            "recipient_cpf": 9620676017,
            "card": "4753****3153",
            "store_owner": "João Macedo",
            "store_name": "Bar Do João",
            "type": {"type_ref": 3, "description": "Financiamento", "nature": "-"},
            "balance": 142,
        }
        expected_data = [
            "Saída",
            "Financiamento",
            "R$ 142",
            "2019-03-01",
            "15:34:53",
            9620676017,
            "4753****3153",
            "João Macedo",
            "Bar Do João",
            142,
        ]

        returned = get_cnab_field_data(given_cnab)

        self.assertEqual(returned, expected_data)

    def test_should_return_empty_cnab_list_with_invalid_input(self):
        given_cnab = {
            "value": 142,
            "date": "2019-03-01",
            "type": {"type_ref": 3, "description": "Financiamento", "nature": "-"},
            "balance": 142,
        }
        expected_data = []

        returned = get_cnab_field_data(given_cnab)

        self.assertEqual(returned, expected_data)

    def test_should_return_transaction_list(self):
        expected_data = [
            {
                "value": 142.0,
                "date": datetime.date(2019, 3, 1),
                "time": datetime.time(15, 34, 53),
                "recipient_cpf": 9620676017,
                "card": "4753****3153",
                "store_owner": "João Macedo",
                "store_name": "Bar Do João",
                "type_ref": "3",
            },
            {
                "value": 132.0,
                "date": datetime.date(2019, 3, 1),
                "time": datetime.time(14, 56, 7),
                "recipient_cpf": 55641815063,
                "card": "3123****7687",
                "store_owner": "Maria Josefina",
                "store_name": "Loja Do Ó - Matriz",
                "type_ref": "5",
            },
        ]

        returned = self.cnab_handler.parse_transactions()

        self.assertEqual(returned, expected_data)
