from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from flask_migrate import Migrate
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime

app = Flask(__name__)
app.config.from_object('config')
app.config['SQLALCHEMY_DATABASE_URI'] = app.config['SQLALCHEMY_DATABASE_URI']

db = SQLAlchemy(app)
migrate = Migrate(app, db)

class Cnab(db.Model):
    __tablename__ = 'cnabs'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    date = db.Column(db.DateTime, default=datetime.utcnow)
    value = db.Column(db.Float)
    cpf = db.Column(db.String(11))
    card = db.Column(db.String(12))
    hour = db.Column(db.DateTime)
    store_owner = db.Column(db.String(80))
    store_name = db.Column(db.String(120))
    
    transaction_type_id = db.Column(db.Integer, db.ForeignKey('transaction_types.id'),
        nullable=False)

class TransactionType(db.Model):
    __tablename__ = 'transaction_types'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    description = db.Column(db.String(120))
    nature = db.Column(db.String(11))
    signal = db.Column(db.String(1))

    cnabs = db.relationship('Cnab', backref='transaction_types', lazy=True)


    
@app.route("/")
def home():
    return "Hello, Flask! First Commit!"

@app.route('/upload')
def upload_file():
   return render_template('upload.html')
	
@app.route('/uploader', methods = ['GET', 'POST'])
def upload_file_function():
   if request.method == 'POST':
      f = request.files['file']
      f.save(secure_filename(f.filename))
      read_cnab(f.filename)
      return 'file uploaded successfully'

@app.route('/read_cnab', methods = ['GET', 'POST'])
def read_cnab(filename): 
    lista = []  
    with open(filename) as f:
        line = f.readline()
        while line:
            line = f.readline()
            tipo = line[0:1]
            data = line[1:9]
            valor = float(line[9:19])/100 if line[9:19] != '' else 0
            cpf = line[19:30]
            cartao = line[30:42]
            hora = line[42:48]
            dono = line[48:62]
            nome = line[62:81]

            lista.append({'tipo': tipo, 'data': data, 'valor': valor, 'cpf': cpf, 'hora': hora, 'cartao': cartao, 'nome': nome, 'hora': hora, 'dono': dono})
        print(lista)


