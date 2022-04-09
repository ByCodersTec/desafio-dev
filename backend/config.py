import os
from tkinter import FALSE
from datetime import timedelta


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

    SESSION_COOKIE_NAME = 'google-login-session'
    PERMANENT_SESSION_LIFETIME = timedelta(minutes=5)


    # Connect to the database
    SQLALCHEMY_DATABASE_URI = f"mysql+pymysql://{DB_USER}:{DB_PASS}@{DB_HOST}:{DB_PORT}/{DB_NAME}"
    CORS_HEADERS = 'Content-Type'
    UPLOAD_FOLDER = "uploads"

    # Turn off the Flask-SQLAlchemy event system and warning
    SQLALCHEMY_TRACK_MODIFICATIONS = False

class Testing(Config):
    TESTING = True

config = {
    'development' : Development,
    'testing': Testing
}