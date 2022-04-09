from app import db
from datetime import datetime
from sqlalchemy.orm import relationship


class Transaction(db.Model):
    __tablename__ = 'transaction'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_store = db.Column(db.ForeignKey('store.id'), index=True)
    date = db.Column(db.DateTime, default=datetime.utcnow)
    value = db.Column(db.Float)
    cpf = db.Column(db.String(11))
    card = db.Column(db.String(12))
    hour = db.Column(db.DateTime)
    transaction_type = db.Column(db.Integer)

    store = relationship('Store')