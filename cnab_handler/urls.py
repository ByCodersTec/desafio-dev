from django.urls import path

from .views import CnabUploadCreateView, CnabListView


urlpatterns = [
    path("cnab/upload/", CnabUploadCreateView.as_view(), name="upload-cnab"),
    path("cnab/", CnabListView.as_view(), name="list-cnab"),
]
