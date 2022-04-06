from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from flask_migrate import Migrate
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime

app = Flask(__name__)
app.config.from_object('config')

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
    transaction_type = db.Column(db.Integer)


class TransactionType(db.Model):
    __tablename__ = 'transaction_types'
    id = db.Column(db.Integer, primary_key=True)
    description = db.Column(db.String(120))
    nature = db.Column(db.String(11))
    type = db.Column(db.String(1))



    
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
    with open(filename, mode="r", encoding="utf-8") as f:
        line = f.readline()
        while line:
            line = f.readline()
            transaction_type = line[0:1]
            date = line[1:9]
            value = float(line[9:19])/100 if line[9:19] != '' else 0
            cpf = line[19:30]
            card = line[30:42]
            hour = line[42:48]
            store_owner = line[48:62]
            store_name = line[62:81].rstrip()

            cnab = Cnab(transaction_type=transaction_type,
                        date=date,
                        value=value,
                        cpf=cpf,
                        card=card,
                        hour=hour,
                        store_owner=store_owner,
                        store_name=store_name)
            db.session.add(cnab)

        db.session.commit()
