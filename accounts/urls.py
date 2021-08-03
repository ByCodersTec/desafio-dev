from django.urls import path, include

from .views import RegisterUserView


urlpatterns = [
    path("accounts/", include("rest_framework.urls", namespace="rest_framework")),
    path("accounts/register/", RegisterUserView.as_view(), name="register-user"),
]
