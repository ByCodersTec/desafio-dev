from django import forms
from django.core.validators import FileExtensionValidator

class uploadForm(forms.Form):
    cnab_file = forms.FileField(label='',  validators=[FileExtensionValidator(allowed_extensions=["txt"])])