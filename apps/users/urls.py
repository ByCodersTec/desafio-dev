"""Here the urls app users are registered."""

from django.urls import path
from apps.users import views  # pylint: disable=import-error


urlpatterns = [
    path('create', views.create_user, name='user_create'),
    path('list', views.list_users, name='user_list'),
    path('update/<int:pk>', views.update_user, name='user_update'),
    path('delete/<int:pk>', views.delete_user, name='user_delete'),
]
