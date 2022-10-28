"""In this module the user views are registered."""
from django.shortcuts import get_object_or_404, render
from django.contrib.auth.models import User
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status, serializers

from apps.users.serializers import UserSerializer
# from .forms import UsersForm


@api_view(['POST'])
def create_user(request):
    """This view create user."""
    user = UserSerializer(data=request.data)
    if User.objects.filter(**request.data).exists():
        raise serializers.ValidationError('This data already exists')

    if user.is_valid():
        User.objects.create_user(**request.data)
        return Response(user.data)
    return Response(status=status.HTTP_404_NOT_FOUND)


@api_view(['GET'])
def list_users(request):
    """This view list users."""
    if request.query_params:
        users = User.objects.get(**request.query_param.dict())
    else:
        users = User.objects.all()
    if users:
        data_users = UserSerializer(users, many=True)
        return Response(data_users.data)
    return Response(status.HTTP_404_NOT_FOUND)


@api_view(['POST'])
def update_user(request, user_id):
    """This view update user."""
    user = User.objects.get(pk=user_id)
    data = UserSerializer(instance=user, data=request.data)
    if data.is_valid():
        data.save()
        return Response(data.data)
    return Response(status=status.HTTP_404_NOT_FOUND)


@api_view(['DELETE'])
def delete_user(request, user_id):  # pylint: disable=unused-argument
    """This view delete user."""
    user = get_object_or_404(User, pk=user_id)
    user.delete()
    return Response(status=status.HTTP_202_ACCEPTED)


# def create_view(request):  # pylint: disable=unused-argument
#     """This view create user by UsersForm."""
#     context = {}
#     form = UsersForm(request.POST or None)
#     if form.is_valid():
#         form.save()
#     context['form'] = form
#     return render(request, "create.html", context)
