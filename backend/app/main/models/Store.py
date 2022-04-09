from app import db

class Store(db.Model):
    __tablename__ = 'store'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    owner = db.Column(db.String(80))
    name = db.Column(db.String(120))
