from flask import Blueprint
coders_bp = Blueprint('byCoders', __name__)

from interview.by_coders import by_coders
