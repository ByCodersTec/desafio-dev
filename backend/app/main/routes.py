from flask import Flask, render_template, request, Blueprint
from werkzeug.utils import secure_filename
import json
from datetime import datetime
from app.main.models import Store, Transaction, TransactionType
from app import db

main = Blueprint('main', __name__)

    
@main.route("/")
def home():
    return "Hello, Flask! First Commit!"

@main.route('/upload')
def upload_file():
   return render_template('upload.html')
	
@main.route('/uploader', methods = ['GET', 'POST'])
def upload_file_function():
   if request.method == 'POST':
      f = request.files['file']
      f.save(secure_filename(f.filename))
      read_cnab(f.filename)
      return 'file uploaded successfully'

@main.route("/transactions", methods=["GET"])
def get_transactions():
    transactions = Transaction.query.all()
    return {"response": serialize(transactions)}

@main.route("/transaction_type/<id>", methods=["GET"])
def get_transaction_type(id):
    transaction_type = TransactionType.query.filter(TransactionType.id == id).all()
    return {"response": serialize(transaction_type)}

@main.route("/transactions/<store_id>", methods=["GET"])
def get_transactions_by_store(store_id):
    transactions = Transaction.query.join(Store, Transaction.id_store == Store.id).filter(Store.id == store_id).all()
    value_sum = 0
    for t in transactions:
        t.transaction_type = TransactionType.query.filter(TransactionType.id == t.transaction_type).with_entities(TransactionType.description).first().description
        value_sum += t.value
    return {"response": serialize(transactions), "value_sum": value_sum}

@main.route("/stores", methods=["GET"])
def get_stores():
    stores = Store.query.all()
    return {"response": serialize(stores)}

def serialize(itens):
    response = []
    for item in itens:
        try:
            item_serialized = item.__dict__
            try:
                item_serialized.pop('_sa_instance_state')
            except:
                ...
            response.append(item_serialized)
        except:
            ...
    return response

@main.route('/read_cnab', methods = ['GET', 'POST'])
def read_cnab(filename): 
    with open(filename, mode="r", encoding="utf-8") as f:
        line = f.readline()
        while line:
            try:
                line = f.readline()
                if len(line) > 0:
                    transaction_type = line[0:1]
                    date = datetime.strptime(line[1:9],"%Y%m%d")
                    value = float(line[9:19])/100 if line[9:19] != '' else 0
                    cpf = line[19:30]
                    card = line[30:42]
                    hour = datetime.strptime(line[1:9]+line[42:48],"%Y%m%d%H%M%S")
                    store_owner = line[48:62]
                    store_name = line[62:81].rstrip()

                    try:
                        store = Store.query.filter(Store.name == store_name).first()
                        if store == None:
                            store = Store(owner=store_owner, name=store_name)
                            db.session.add(store)
                            db.session.flush()
                        
                        transaction = Transaction(transaction_type = transaction_type, date = date, value = value, cpf = cpf, card = card, hour = hour, id_store = store.id)
                        db.session.add(transaction)
                    except Exception as e:
                        print(str(e))

            except Exception as e:
                return "Error when reading the CNAB file. Error: ", str(e)

        db.session.commit()
