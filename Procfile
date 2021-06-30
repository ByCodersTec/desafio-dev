release: python cnab_project/manage.py makemigrations
release: python cnab_project/manage.py migrate
web: gunicorn --pythonpath cnab_project cnab_project.wsgi