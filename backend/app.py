from flask import Flask
#from flask_migrate import Migrate

app = Flask(__name__)
app.config.from_object('config')


@app.route("/")
def home():
    return "Hello, Flask! First Commit!"
