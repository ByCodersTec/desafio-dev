from django.http import HttpResponseRedirect
from django.contrib.auth.mixins import LoginRequiredMixin

from rest_framework.views import APIView
from rest_framework.response import Response

from rest_framework.renderers import TemplateHTMLRenderer
from rest_framework.parsers import FormParser, MultiPartParser

from rest_framework.permissions import IsAuthenticated
from rest_framework.authentication import TokenAuthentication, SessionAuthentication


# from .models import Transaction
from .serializers import CnabFileSerializer


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

        with file.open(mode="rb") as f:
            for line in f:
                line.decode("UTF-8")
        # create a new Transaction on each line of the loop
        return HttpResponseRedirect(redirect_to="/api/cnab/")


class CnabListView(LoginRequiredMixin, APIView):
    permission_classes = [IsAuthenticated]
    authentication_classes = [TokenAuthentication, SessionAuthentication]

    renderer_classes = [TemplateHTMLRenderer]
    template_name = "cnab_list.html"
