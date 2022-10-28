"""Here the urls app data-explore are registered."""

from django.urls import path
from apps.dataexplore import views  # pylint: disable=import-error


urlpatterns = [
  path('upload-files', views.upload_files, name='upload-files'),
  path('transform', views.data_transform, name='transform-data'),
  path('dashboard', views.dashboard, name='dashboard'),
]