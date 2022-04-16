from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from flasgger import Swagger

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
    swagger_config = {
        "headers": [
        ],
        "specs": [
            {
                "endpoint": 'apispec_1',
                "route": '/apispec_1.json',
                "rule_filter": lambda rule: True,  # all in
                "model_filter": lambda tag: True,  # all in
            }
        ],
        "static_url_path": "/flasgger_static",
        # "static_folder": "static",  # must be set by user
        "swagger_ui": True,
        "specs_route": "/apidocs/"
    }

    swag = Swagger(app, config=swagger_config)

    init_routes(app)

    migrate.init_app(app, db)

    return app
