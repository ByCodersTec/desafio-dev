from django.http.response import HttpResponse, HttpResponseBadRequest, HttpResponseNotFound
from .forms import UploadCNABFileForm
from django.shortcuts import get_object_or_404, render
from django.db.models import Sum, Count
from django.core.paginator import Paginator
from .models import Transaction, TransactionError
from .utils import verify_cnab_file
from .models import negative_types

def index(request):
    paginator = Paginator(Transaction.objects.order_by('-id'), 10)
    transactions = {
                    'transactions': paginator.get_page(request.GET.get('page', 1)),
                    'total': Transaction.objects.count(),
                    'incomes': Transaction.objects.exclude(type__in=negative_types()).aggregate(Sum('amount')),
                    'expenses': Transaction.objects.filter(type__in=negative_types()).aggregate(Sum('amount')),
                    'customers': Transaction.objects.all().aggregate(Count('nin', distinct=True))
                   }
    return render(request, 'transactions/index.html', transactions)

def errors(request):
    paginator = Paginator(TransactionError.objects.order_by('-id'), 10)
    transactions = {
                    'errors': paginator.get_page(request.GET.get('page', 1)),
                   }
    return render(request, 'transactions/errors.html', transactions)

def details(request, id):
    transaction = {'transaction': get_object_or_404(Transaction, pk=id)}
    return render(request, 'transactions/detail.html', transaction)


def importer(request):
    if request.method == 'POST':
        form = UploadCNABFileForm(request.POST, request.FILES)
        if form.is_valid():
            verify_cnab_file(request.FILES['cnab_file'])
            return HttpResponse('File uploaded')
        return HttpResponseBadRequest('Error to send file')
    return HttpResponseNotFound('Page not found')