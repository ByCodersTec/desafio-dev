from django import forms
from .models import DataModel
 
 
class DataForm(forms.ModelForm):
    class Meta:
        model = DataModel
        fields = [
            "filename",
        ]