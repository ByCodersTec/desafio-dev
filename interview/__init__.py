from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate

from werkzeug.utils import import_string

from interview.init_routes import init_routes


db = SQLAlchemy(session_options={"autoflush": False, 'expire_on_commit': False})
migrate = Migrate()


def create_app(config_class='DevConfig'):
    app = Flask(__name__)

    cfg = import_string('config.{0}'.format(config_class))()
    app.config.from_object(cfg)
    app.config['CONFIG_CLASS'] = config_class

    db.init_app(app)
    init_routes(app)

    migrate.init_app(app, db)

    return app
