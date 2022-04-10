from flask import Blueprint
from app.main.controllers.StoreController import StoreController

store_bp = Blueprint('store_bp', __name__)

store_bp.route('/', methods=['GET'])(StoreController.index)
