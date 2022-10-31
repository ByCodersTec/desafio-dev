"""Here the data explore views are created."""
from django.shortcuts import render
from rest_framework.decorators import api_view
from apps.dataexplore.forms import DataForm


@api_view(['POST'])
def upload_files(request):  # pylint: disable=unused-argument
    """This view make the upload files."""
    context = {}
    form = DataForm(request.POST, request.FILES)
    if form.is_valid():
        context['data'] = 'Your file has been saved!'
        form.save()
    else:
        context['data'] = 'Error in to save file.'
    context['form'] = form
    return render(request, "create.html", context)


@api_view(['GET'])
def data_transform():
    """This view transform the data from file in informations to database."""


@api_view(['GET'])
def dashboard():
    """This views brings informations about the transactions."""
