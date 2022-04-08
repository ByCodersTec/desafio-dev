from flask import Flask, render_template, request
from werkzeug.utils import secure_filename
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from config import config
from flask_cors import CORS, cross_origin


db = SQLAlchemy(session_options={"autoflush": False})

def create_app(app_config='development'):
    app = Flask(__name__)
    migrate = Migrate(app, db)
    cors = CORS(app)
    app.config.from_object(config[app_config])

    from app.main.routes import main

    app.register_blueprint(main)
    db.init_app(app)
    return app


