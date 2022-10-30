from django.shortcuts import render
from drf_yasg.utils import swagger_auto_schema
from rest_framework.decorators import api_view
import os 

from .forms import DataForm

# Create your views here.
"""
TODO: Create the view to handle with divisions informations
"""

# @api_view(['POST'])
def upload_files(request):  # pylint: disable=unused-argument
    """This view make the upload files."""
    context = {}
    form = DataForm(request.POST, request.FILES)
    if form.is_valid():
        form.save()
    context['form'] = form
    return render(request, "create.html", context)

@api_view(['GET'])
def data_transform():
    """This view transform the data from file in informations to database."""
    pass

@api_view(['GET'])
def dashboard():
    """This views brings informations about the transactions."""
    pass

