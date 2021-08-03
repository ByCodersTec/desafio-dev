from django.urls import reverse
from django.http import HttpResponseRedirect
from django.contrib.auth.mixins import LoginRequiredMixin

from rest_framework.views import APIView
from rest_framework.response import Response

from rest_framework.renderers import TemplateHTMLRenderer
from rest_framework.parsers import FormParser, MultiPartParser

from rest_framework.permissions import IsAuthenticated
from rest_framework.authentication import TokenAuthentication, SessionAuthentication

from .models import Transaction
from .services import CnabServices
from .serializers import CnabFileSerializer, TransactionSerializer


class CnabUploadCreateView(LoginRequiredMixin, APIView):
    authentication_classes = [TokenAuthentication, SessionAuthentication]
    permission_classes = [IsAuthenticated]

    renderer_classes = [TemplateHTMLRenderer]
    parser_classes = [FormParser, MultiPartParser]
    template_name = "file_upload.html"

    def get(self, request, *args, **kwargs):
        file_upload_form = CnabFileSerializer()

        return Response({"file_form": file_upload_form})

    def post(self, request, *args, **kwargs):
        file = request.FILES["file"]

        cnab_handler = CnabServices(file)

        transaction_list = cnab_handler.parse_transactions()
        cnab_handler.register_transactions(transaction_list, request.user.pk)

        return HttpResponseRedirect(redirect_to=reverse("list-cnab"))


class CnabListView(LoginRequiredMixin, APIView):
    permission_classes = [IsAuthenticated]
    authentication_classes = [TokenAuthentication, SessionAuthentication]

    renderer_classes = [TemplateHTMLRenderer]
    template_name = "cnab_list.html"

    def get(self, request, *args, **kwargs):
        transactions = (
            Transaction.objects.filter(manager=request.user.pk)
            .select_related("type")
            .all()
        )
        transactions_serializer = TransactionSerializer(transactions, many=True)
        balance = CnabServices.get_user_balance(transactions_serializer.data)

        cnab_html_table = CnabServices.get_cnab_html_table(
            transactions_serializer.data, balance
        )

        return Response({"transactions": cnab_html_table})
