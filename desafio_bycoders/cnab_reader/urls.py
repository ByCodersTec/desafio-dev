from django.urls import path
from cnab_reader.views import indexView, OperationsListIndex

urlpatterns = [
    path('', indexView.as_view()),
    path('operations_list/', OperationsListIndex.as_view()),
]