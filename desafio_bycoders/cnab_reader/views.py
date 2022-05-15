from django.shortcuts import render
from django.views.generic import TemplateView, FormView, CreateView
from django.urls import reverse_lazy
from .forms import uploadForm
from django.views.decorators.csrf import csrf_exempt, csrf_protect
from django.utils.decorators import method_decorator
from .use_case import CnabReaderUseCase


class indexView(FormView):
    template_name = "index.html"
    form_class = uploadForm
    success_url=('/')
    use_case = CnabReaderUseCase()

    def post(self, request, *args, **kwargs):
        print('entrou no post')
        form_class = self.get_form_class()
        form = self.get_form(form_class)
        files = request.FILES.get('cnab_file')
        print(files)
        if form.is_valid():
            file = files.read()
            self.use_case.execute(file)
            return self.form_valid(form)
        else:
            return self.form_invalid(form)

class OperationsList(TemplateView):
    template_name = "operations_list.html"

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['operations'] = self.use_case.get_operations()
        return context