from flask import Blueprint
cnab_bp = Blueprint('cnab', __name__)

from interview.cnab import cnab
