from django.contrib.auth.models import User
from rest_framework.authtoken.models import Token

from rest_framework import serializers


class RegisterUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ["username", "password"]
        extra_kwargs = {
            "password": {
                "style": {"input_type": "password"},
                "write_only": True,
            },
        }

    def create(self, validated_data):

        new_user = User.objects.create_user(
            username=validated_data["username"], password=validated_data["password"]
        )
        Token.objects.create(user=new_user)

        return new_user
