from django import forms

class UploadCNABFileForm(forms.Form):
    cnab_file = forms.FileField()