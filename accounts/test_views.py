from django.urls import reverse
from django.contrib.auth.models import User

from rest_framework.test import APITestCase


class TestAccountViews(APITestCase):
    REGISTER_TEMPLATE = "registration/register.html"
    REGISTER_URL = reverse("register-user")
    LOGIN_URL = reverse("rest_framework:login")

    @classmethod
    def setUpTestData(cls):
        cls.user_data_01 = {
            "username": "test_user_01",
            "password": "1234",
        }
        cls.user_data_02 = {
            "username": "test_user_02",
            "password": "1234",
        }

    def test_template_used(self):
        reg_page_response = self.client.get(self.REGISTER_URL)

        self.assertTemplateUsed(reg_page_response, self.REGISTER_TEMPLATE)
        self.assertContains(reg_page_response, "Cadastro de Usuário")

    def test_should_register_user(self):
        reg_user_01_response = self.client.post(self.REGISTER_URL, self.user_data_01)
        reg_user_02_response = self.client.post(self.REGISTER_URL, self.user_data_02)

        self.assertRedirects(reg_user_01_response, self.LOGIN_URL, 302, 200)
        self.assertRedirects(reg_user_02_response, self.LOGIN_URL, 302, 200)

        found_user_01 = User.objects.get(username=self.user_data_01["username"])
        found_user_02 = User.objects.get(username=self.user_data_02["username"])

        found_user_data_01 = {
            "id": found_user_01.id,
            "username": found_user_01.username,
        }
        found_user_data_02 = {
            "id": found_user_02.id,
            "username": found_user_02.username,
        }

        self.assertEqual(
            found_user_data_01, {"id": 1, "username": self.user_data_01["username"]}
        )
        self.assertEqual(
            found_user_data_02, {"id": 2, "username": self.user_data_02["username"]}
        )
        self.assertEqual(User.objects.all().count(), 2)
