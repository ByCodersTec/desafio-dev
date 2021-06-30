from django.urls import path
from . import views
app_name = 'transactions'
urlpatterns = [
    path('', views.index, name='index'),
    path('upload', views.importer, name='upload'),
    path('<int:id>', views.details, name='details'),
    path('errors', views.errors, name='errors'),
]