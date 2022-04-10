
from ..models.Store import Store
from app import db
from flask_sqlalchemy import SQLAlchemy
from ..utils import serialize
from app.auth_decorator import login_required

class StoreController():
    #@login_required
    def index():
        stores = Store.query.all()
        return { "response": serialize(stores) }

    def store(obj):
        db.session.add(obj)
        db.session.commit()
        
    def show(id):
        store = Store.query.filter(Store.id == id).first()
        return { "response": serialize(store) }

    def update(id):
        ...
        
    def delete(id):
        store = Store.query.filter(Store.id == id).delete()
        return { "response_deleted": serialize(store) }

