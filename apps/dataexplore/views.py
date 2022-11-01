"""Here the data explore views are created."""
import os
import pandas as pd
from django.shortcuts import render
from django.http import HttpResponseRedirect
from django.db.models import Sum
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view
from config.settings import MEDIA_ROOT

from apps.dataexplore.forms import DataForm
from apps.dataexplore.manage_files import Information
from apps.dataexplore.models import TransactionModel, TransactionTypeModel
from apps.dataexplore.serializers  import TransactionSerializer




class TransformData():
    """This class transform the data from file in informations to database."""
    def __init__(self, filename):
        self.filename = f'{MEDIA_ROOT}/{filename}'
        self.file = 'media/'+filename

    def save_in_database(self, file):
        """This function save informations in database."""
        for id_row in file.index:
            row = file[0][id_row]
            information = Information(row).fields_map()
            transaction_id = information['transaction']
            transaction_type = TransactionTypeModel.objects.get(pk=transaction_id)  # pylint: disable=no-member
            transaction = TransactionSerializer(instance=transaction_type, data=information)
            if transaction.is_valid():
                information.pop('transaction')
                TransactionModel.objects.get_or_create(transaction=transaction_type, **information)  # pylint: disable=no-member

    def get_informations(self):
        """This function get informations from database."""
        result = (TransactionModel.objects  # pylint: disable=no-member
            .values('store_name', 'transaction')
            .annotate(total_transaction=Sum('transaction_value'))
            .order_by('store_name')
        )
        for transaction_id in result:
            transaction_type = TransactionTypeModel.objects.get(  # pylint: disable=no-member
                pk=transaction_id['transaction'])
            transaction_id.pop('transaction')
            transaction_id['transaction'] = transaction_type.name
        return result


    def read_file(self):
        """This function read the txt file."""
        try:
            file = pd.read_csv(self.filename, header=None)
            return file
        except Exception as err:  # pylint: disable=broad-except
            print('Error in read file', err)
            return 500

    def delete_file(self):
        """This function delete the txt file."""
        if os.path.exists(self.file):
            os.remove(self.file)

    def __call__(self):
        file = self.read_file()
        self.save_in_database(file)
        result = self.get_informations()
        return result

    def main(self):
        """This main function."""
        return self.__call__()

def handle_uploaded_file(file):
    """This function save the data in media files."""
    with open('media/files/data.txt', 'wb+') as destination:
        for chunk in file.chunks():
            destination.write(chunk)

def upload_files(request):  # pylint: disable=unused-argument
    """This function make the upload files."""
    context = {}
    form = DataForm(request.POST, request.FILES)
    if form.is_valid():
        context['data'] = 'Your file has been saved!'
        handle_uploaded_file(request.FILES['filename'])
        return HttpResponseRedirect('/')

    context['data'] = 'Error in to save file.'
    context['form'] = form
    return render(request, "create.html", context)


@api_view(['GET'])
def dashboard(request):
    """This views brings informations about the transactions."""
    result = TransformData(filename='files/data.txt').main()
    if result != 500:
        total = {'results': result}
        return render(request, "dashboard.html", total)
    return Response(status=status.HTTP_404_NOT_FOUND)
