from django.urls import path, include
from .views import importadorView
from django.conf import settings
from django.conf.urls.static import static
from rest_framework import routers
from .actions.importador import (
    ImportadorViewSet
)

router = routers.SimpleRouter()
router.register(r"importador", ImportadorViewSet, basename="ImportadorViewSet")  


urlpatterns = [
    path("", importadorView.index, name="index",),
    path("api/", include(router.urls))
]

if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)