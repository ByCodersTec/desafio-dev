from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from config import config
from flask_cors import CORS, cross_origin


db = SQLAlchemy(session_options={"autoflush": False})

def create_app(app_config='development'):
    app = Flask(__name__)
    app.config.from_object(config[app_config])
    cors = CORS(app)
    migrate = Migrate(app, db)

    #from app.main.routes import main
    #Blueprints
    from app.main.routes.store_bp import store_bp
    from app.main.routes.transaction_bp import transaction_bp


    app.register_blueprint(store_bp, url_prefix='/stores')
    app.register_blueprint(transaction_bp, url_prefix='/transactions')
    #app.register_blueprint(main)

    db.init_app(app)
    return app
