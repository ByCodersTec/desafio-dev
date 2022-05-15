from django.urls import path
from cnab_reader.views import indexView

urlpatterns = [
    path('', indexView.as_view())
]