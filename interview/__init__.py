from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from flasgger import Swagger
from flask_cors import CORS
from flask_caching import Cache

from werkzeug.utils import import_string

from interview.init_routes import init_routes


db = SQLAlchemy(session_options={"autoflush": True, 'expire_on_commit': False})
migrate = Migrate()
cache = Cache()


def create_app(config_class='DevConfig'):
    app = Flask(__name__)

    cfg = import_string('config.{0}'.format(config_class))()
    app.config.from_object(cfg)
    app.config['CONFIG_CLASS'] = config_class

    db.init_app(app)
    cors = CORS(app, origins='{0}'.format(app.config.get('CORS_SUPPORTS_ORIGIN'))
                , allow_headers=['Content-Type', 'Authorization', 'Access-Control-Allow-Credentials']
                , methods=['GET', 'POST', 'DELETE']
                , supports_credentials=True)

    cache.init_app(app)
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
        "specs_route": "/api/apidocs/"
    }

    swag = Swagger(app, config=swagger_config)

    init_routes(app)

    migrate.init_app(app, db)

    return app
