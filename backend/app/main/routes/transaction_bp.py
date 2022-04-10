from flask import Blueprint
from ..controllers.TransactionController import TransactionController

transaction_bp = Blueprint('transaction_bp', __name__)

transaction_bp.route('/', methods=['GET'])(TransactionController.index)
transaction_bp.route('/<id>', methods=['GET'])(TransactionController.show)
transaction_bp.route('/store/<store_id>', methods=['GET'])(TransactionController.show_by_store)
transaction_bp.route('/upload', methods=['POST'])(TransactionController.upload)
transaction_bp.route('/<id>', methods=['DELETE'])(TransactionController.delete)


