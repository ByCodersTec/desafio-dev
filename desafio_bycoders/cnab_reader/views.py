from django.shortcuts import render, HttpResponseRedirect
from django.views.generic import DetailView, FormView
from django.urls import reverse_lazy
from .forms import uploadForm, StoreFilter
from django.views.decorators.csrf import csrf_exempt, csrf_protect
from django.utils.decorators import method_decorator
from .use_cases.cnab_reader_use_case import CnabReaderUseCase
from .use_cases.operations_list_use_case import OperationsListUseCase



class indexView(FormView):
    template_name = "index.html"
    form_class = uploadForm
    success_url=('operations_list/')
    use_case = CnabReaderUseCase()

    def post(self, request, *args, **kwargs):
        form_class = self.get_form_class()
        form = self.get_form(form_class)
        files = request.FILES.get('cnab_file')
        if form.is_valid():
            file = files.read()
            self.use_case.execute(file)
            return self.form_valid(form)
        else:
            return self.form_invalid(form)

class OperationsListMixin(FormView):
    store_name = None
    form_class = StoreFilter
    template_name = "operations_list.html"
    success_url=('/operations_list/')
    use_case = OperationsListUseCase()

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['operations'] = self.use_case.execute(self.store_name)
        return context

    def form_valid(self, form):
        return super().form_valid(form)


class OperationsListIndex(OperationsListMixin):
    def post(self, request, *args, **kwargs):
        context = super().get_context_data(**kwargs)
        form_class = self.get_form_class()
        form = self.get_form(form_class)
        if form.is_valid():
            self.cpf = form.cleaned_data['store']
            context['operations'], context['total'] = self.use_case.execute(self.cpf)
            return self.render_to_response(context)
        else:
            return self.form_invalid(form)
