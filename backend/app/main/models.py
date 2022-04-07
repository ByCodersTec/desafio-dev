from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.orm import relationship
from datetime import datetime
from app import db

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

class Store(db.Model):
    __tablename__ = 'store'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    owner = db.Column(db.String(80))
    name = db.Column(db.String(120))


class TransactionType(db.Model):
    __tablename__ = 'transaction_type'
    id = db.Column(db.Integer, primary_key=True)
    description = db.Column(db.String(120))
    nature = db.Column(db.String(11))
    type = db.Column(db.String(1))

