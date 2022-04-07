import os
from tkinter import FALSE


class Config(object):
    TESTING= False
    DEBUG= False

class Development(Config):
    SECRET_KEY = os.urandom(32)
    # Grabs the folder where the script runs.
    basedir = os.path.abspath(os.path.dirname(__file__))
    # Enable debug mode.
    DEBUG = True
    DB_USER = 'root'
    DB_PASS = 'root'
    DB_HOST = 'localhost'
    DB_PORT = '3306'
    DB_NAME = 'cnab_bycoders'

    # Connect to the database
    SQLALCHEMY_DATABASE_URI = f"mysql+pymysql://{DB_USER}:{DB_PASS}@{DB_HOST}:{DB_PORT}/{DB_NAME}"
    # Turn off the Flask-SQLAlchemy event system and warning
    SQLALCHEMY_TRACK_MODIFICATIONS = False

class Testing(Config):
    TESTING = True

config = {
    'development' : Development,
    'testing': Testing
}