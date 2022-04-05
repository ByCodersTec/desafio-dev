from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
#from flask_migrate import Migrate

app = Flask(__name__)
app.config.from_object('config')


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


