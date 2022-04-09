from flask import Blueprint
from ..controllers.TransactionController import index, show, upload, show_by_store

transaction_bp = Blueprint('transaction_bp', __name__)

transaction_bp.route('/', methods=['GET'])(index)
transaction_bp.route('/<id>', methods=['GET'])(show)
transaction_bp.route('/store/<store_id>', methods=['GET'])(show_by_store)
transaction_bp.route('/upload', methods=['POST'])(upload)

