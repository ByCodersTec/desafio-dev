from django.urls import reverse
from django.http import HttpResponseRedirect

from rest_framework.views import APIView
from rest_framework.response import Response

from rest_framework.parsers import FormParser, MultiPartParser
from rest_framework.renderers import TemplateHTMLRenderer

from .serializers import RegisterUserSerializer


class RegisterUserView(APIView):
    renderer_classes = [TemplateHTMLRenderer]
    template_name = "registration/register.html"

    parser_classes = [FormParser, MultiPartParser]

    def get(self, request, *args, **kwargs):
        register_user_form = RegisterUserSerializer()

        return Response({"register_user_form": register_user_form})

    def post(self, request, *args, **kwargs):
        user_serializer = RegisterUserSerializer(data=request.data)
        user_serializer.is_valid(raise_exception=True)

        user_serializer.save()

        return HttpResponseRedirect(redirect_to=reverse("rest_framework:login"))
