from django.urls import reverse
from django.core.files.uploadedfile import SimpleUploadedFile

from django.contrib.auth.models import User
from cnab_handler.models import Transaction
from cnab_handler.serializers import TransactionSerializer

from rest_framework.test import APITestCase


class TestCnabViews(APITestCase):
    REGISTER_URL = reverse("register-user")
    LOGIN_URL = reverse("rest_framework:login")

    UPLOAD_FILE_URL = reverse("upload-cnab")
    UPLOAD_FILE_TEMPLATE = "file_upload.html"

    CNAB_LIST_URL = reverse("list-cnab")
    CNAB_LIST_TEMPLATE = "cnab_list.html"

    @classmethod
    def setUpTestData(cls):
        cls.user_data_01 = {
            "username": "test_user_01",
            "password": "1234",
        }
        User.objects.create_user(
            username=cls.user_data_01["username"], password=cls.user_data_01["password"]
        )
        f_content = """
        3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO    \n
        5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ
        """
        cls.cnab_file = SimpleUploadedFile("test_cnab", f_content.encode("UTF-8"))

        f_invalid_content = "32**BAR---   3232648945564\nasnjcuia867623123***sdcsdc--"

        cls.invalid_cnab_file = SimpleUploadedFile(
            "invalid_test_cnab", f_invalid_content.encode("UTF-8")
        )
        cls.expected_transaction_data_01 = {
            "value": 142,
            "date": "2019-03-01",
            "time": "15:34:53",
            "recipient_cpf": 9620676017,
            "card": "4753****3153",
            "store_owner": "João Macedo",
            "store_name": "Bar Do João",
            "type": {
                "type_ref": 3,
                "description": "Financiamento",
                "nature": "-",
            },
        }
        cls.expected_transaction_data_02 = {
            "value": 132,
            "date": "2019-03-01",
            "time": "14:56:07",
            "recipient_cpf": 55641815063,
            "card": "3123****7687",
            "store_owner": "Maria Josefina",
            "store_name": "Loja Do Ó - Matriz",
            "type": {
                "type_ref": 5,
                "description": "Recebimento Empréstimo",
                "nature": "+",
            },
        }
        cls.expected_displayed_transactions = [
            "Saída",
            "Financiamento",
            "R$ 142",
            "2019-03-01",
            "15:34:53",
            "9620676017",
            "4753****3153",
            "João Macedo",
            "Bar Do João",
            "--",
            "Entrada",
            "Recebimento",
            "Empréstimo",
            "R$ 132",
            "2019-03-01",
            "14:56:07",
            "55641815063",
            "3123****7687",
            "Maria Josefina",
            "Loja Do Ó - Matriz",
            "-10",
        ]

    def test_upload_template_used(self):
        logged_in = self.client.login(
            username=self.user_data_01["username"],
            password=self.user_data_01["password"],
        )
        self.assertTrue(logged_in)

        upload_page_response = self.client.get(self.UPLOAD_FILE_URL)

        self.assertTemplateUsed(upload_page_response, self.UPLOAD_FILE_TEMPLATE)
        self.assertContains(upload_page_response, "Registrar Transações")

    def test_cnab_list_template_used(self):
        logged_in = self.client.login(
            username=self.user_data_01["username"],
            password=self.user_data_01["password"],
        )
        self.assertTrue(logged_in)

        cnab_page_response = self.client.get(self.CNAB_LIST_URL)

        self.assertTemplateUsed(cnab_page_response, self.CNAB_LIST_TEMPLATE)
        self.assertContains(cnab_page_response, "Transações Registradas")

    def test_cnab_list_redirect_when_not_logged_in(self):
        cnab_page_response = self.client.get(self.CNAB_LIST_URL)

        self.assertRedirects(
            cnab_page_response, self.LOGIN_URL + "?next=/api/cnab/", 302, 200
        )

    def test_cnab_upload_redirect_when_not_logged_in(self):
        cnab_page_response = self.client.get(self.UPLOAD_FILE_URL)

        self.assertRedirects(
            cnab_page_response, self.LOGIN_URL + "?next=/api/cnab/upload/", 302, 200
        )

    def test_should_register_transactions_with_valid_file(self):
        logged_in = self.client.login(
            username=self.user_data_01["username"],
            password=self.user_data_01["password"],
        )
        self.assertTrue(logged_in)

        up_file_response = self.client.post(
            self.UPLOAD_FILE_URL, {"file": self.cnab_file}
        )
        self.assertRedirects(up_file_response, self.CNAB_LIST_URL, 302, 200)

        transactions_list = Transaction.objects.all()

        self.assertEqual(transactions_list.count(), 2)

        returned_transactions = TransactionSerializer(transactions_list, many=True).data

        self.assertEqual(returned_transactions[0], self.expected_transaction_data_01)
        self.assertEqual(returned_transactions[1], self.expected_transaction_data_02)

    def test_should_not_register_transactions_with_invalid_file(self):
        logged_in = self.client.login(
            username=self.user_data_01["username"],
            password=self.user_data_01["password"],
        )
        self.assertTrue(logged_in)

        up_file_response = self.client.post(
            self.UPLOAD_FILE_URL, {"file": self.invalid_cnab_file}
        )
        self.assertRedirects(up_file_response, self.CNAB_LIST_URL, 302, 200)

        transactions_list = Transaction.objects.all()

        self.assertEqual(transactions_list.count(), 0)

    def test_should_display_transaction_list(self):
        logged_in = self.client.login(
            username=self.user_data_01["username"],
            password=self.user_data_01["password"],
        )
        self.assertTrue(logged_in)

        self.client.post(self.UPLOAD_FILE_URL, {"file": self.cnab_file})
        cnab_page_response = self.client.get(self.CNAB_LIST_URL)

        for transaction in self.expected_displayed_transactions:
            self.assertContains(cnab_page_response, transaction)
