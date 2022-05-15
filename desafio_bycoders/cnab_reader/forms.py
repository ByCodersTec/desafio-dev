from django import forms
from django.core.validators import FileExtensionValidator
from .models import cnabModel


class uploadForm(forms.Form):
    cnab_file = forms.FileField(label='',  validators=[FileExtensionValidator(allowed_extensions=["txt"])])

class StoreFilter(forms.Form):
    stores = (('', 'Todos'),)
    all_stores = cnabModel.objects.values('nome_loja').distinct().order_by('nome_loja')
    for store in all_stores:
        stores = stores + ((store['nome_loja'], store['nome_loja']),)

    store = forms.ChoiceField(choices=stores, label="", required=False)
