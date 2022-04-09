
from ..models.Store import Store
from app import db
from flask_sqlalchemy import SQLAlchemy
from ..utils import serialize
from app.auth_decorator import login_required

#@login_required
def index():
    stores = Store.query.all()
    return { "response": serialize(stores) }

def store():
    ...
def show(id):
    ...
def update(id):
    ...
def delete(id):
    ...