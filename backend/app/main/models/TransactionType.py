from flask_sqlalchemy import SQLAlchemy
from app import db


class TransactionType(db.Model):
    __tablename__ = 'transaction_type'
    id = db.Column(db.Integer, primary_key=True)
    description = db.Column(db.String(120))
    nature = db.Column(db.String(11))
    type = db.Column(db.String(1))