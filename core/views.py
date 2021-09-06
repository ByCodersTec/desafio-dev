from django.shortcuts import render


class importadorView:
    
    @staticmethod
    def index(request):
        return render(request, "core/index.html")