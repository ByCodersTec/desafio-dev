
from ..models.Transaction import Transaction
from ..models.Store import Store
from ..models.TransactionType import TransactionType

from app import db
from flask_sqlalchemy import SQLAlchemy
from ..utils import serialize
from datetime import datetime
from flask import request
from werkzeug.utils import secure_filename
import os

class InvalidUsage(Exception):
    status_code = 400

    def __init__(self, message, status_code=400):
        Exception.__init__(self)
        self.error_message = message
        if status_code is not None:
            self.status_code = status_code
        

    def to_dict(self):
        return {
            'message': self.error_message,
            'error': True,
        }

class TransactionController():
        
    def index():
        try:
            transactions = Transaction.query.all()
            return {"response": serialize(transactions)}
        except Exception as e:
            return { "error": str(e) }

    def store():
        ...
    def show(id):
        ...
    def delete(id):
        try:
            transaction = Transaction.query.filter(Transaction.id == id).first()
            db.session.delete(transaction)
            db.session.commit()
            return { "response": "Transaction " +id+" deleted." }
        except Exception as e:
            return { "error": str(e) }

    def show_by_store(store_id):
        transactions = Transaction.query.join(Store, Transaction.id_store == Store.id).filter(Store.id == store_id).all()
        value_sum = 0
        for t in transactions:
            t.transaction_type = TransactionType.query.filter(TransactionType.id == t.transaction_type).first()
            value_sum = value_sum + t.value if(t.transaction_type.type == '+') else value_sum - t.value
            t.transaction_type = t.transaction_type.description + ' - ' + t.transaction_type.nature
        return {"response": serialize(transactions), "value_sum": value_sum}


    def upload():
        if request.method == 'POST':
            try:
                f = request.files['file']
            except:
                raise InvalidUsage("Please provide a CNAB.txt to upload", status_code=400)

            if f.content_type != 'text/plain':
                raise InvalidUsage("Only text files are allowed", status_code=400)
            
            try:
                f.save(secure_filename(f.filename))
                filename = secure_filename(f.filename)
                basedir = os.path.dirname(os.path.abspath(os.path.dirname(__file__)))
                destination_file = os.path.join(basedir, 'uploads', filename)
                f.save(destination_file)
                try:
                    response = TransactionController.read_cnab(f.filename)
                except Exception as e:
                    response = {"message": "Error reading file.", "error": str(e)}
                return response, 201
            except:
                raise
                raise InvalidUsage('Failed to upload text', status_code=500)

    def read_cnab(filename):
        try:
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
                        return { "message": "Error when reading the CNAB file. Error: ", "error": str(e)}

                db.session.commit()
                return { "message": "Succesfully uploaded file." }
        except Exception as e:
            return { "message": "Error when reading the CNAB file. Error: ", "error": str(e)}
