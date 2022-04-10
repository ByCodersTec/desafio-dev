from flask import Flask, redirect, url_for, session, request
from authlib.integrations.flask_client import OAuth
import requests
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
import os
from app.auth_decorator import login_required
from config import config
from flask_cors import CORS, cross_origin
from flask_swagger_ui import get_swaggerui_blueprint


db = SQLAlchemy(session_options={"autoflush": False})

def create_app(app_config='development'):
    app = Flask(__name__)
    app.config.from_object(config[app_config])
    cors = CORS(app)
    migrate = Migrate(app, db)

    #Blueprints
    from app.main.routes.store_bp import store_bp
    from app.main.routes.transaction_bp import transaction_bp


    app.register_blueprint(store_bp, url_prefix='/stores')
    app.register_blueprint(transaction_bp, url_prefix='/transactions')

    

    ### swagger specific ###
    SWAGGER_URL = '/apidocs'
    API_URL = '/static/swagger.json'
    SWAGGERUI_BLUEPRINT = get_swaggerui_blueprint(
        SWAGGER_URL,
        API_URL,
        config={
            'app_name': "Flaks-Bycoders-Challenge"
        }
    )
    app.register_blueprint(SWAGGERUI_BLUEPRINT, url_prefix=SWAGGER_URL)
    ### end swagger specific ###
    


    # oAuth Setup - UNUSED
    app.secret_key = 'random secret key'
    oauth = OAuth(app)
    google = oauth.register(
        name='google',
        client_id='149775317225-laslcu0s7575et41cbnijh0bodsapkp5.apps.googleusercontent.com',
        client_secret='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',
        access_token_url='https://accounts.google.com/o/oauth2/token',
        access_token_params=None,
        authorize_url='https://accounts.google.com/o/oauth2/auth',
        authorize_params=None,
        api_base_url='https://www.googleapis.com/oauth2/v1/',
        userinfo_endpoint='https://openidconnect.googleapis.com/v1/userinfo',  # This is only needed if using openId to fetch user info
        client_kwargs={'scope': 'openid email profile'},
    )

    @app.route('/')

    @login_required
    def hello_world():
        email = dict(session)['profile']['email']
        return f'Hello, you are logge in as {email}!'


    @app.route('/login')
    def login():
        google = oauth.create_client('google')  # create the google oauth client
        redirect_uri = url_for('authorize', _external=True)
        return google.authorize_redirect(redirect_uri)


    @app.route('/authorize')
    def authorize():
        google = oauth.create_client('google')  # create the google oauth client
        token = google.authorize_access_token()  # Access token from google (needed to get user info)
        resp = google.get('userinfo')  # userinfo contains stuff u specificed in the scrope
        user_info = resp.json()
        user = oauth.google.userinfo()  # uses openid endpoint to fetch user info
        # Here you use the profile/user data that you got and query your database find/register the user
        # and set ur own data in the session not the profile from google
        session['profile'] = user_info
        session.permanent = True  # make the session permanant so it keeps existing after broweser gets closed
        return redirect('/')


    @app.route('/logout')
    def logout():
        for key in list(session.keys()):
            session.pop(key)
        return redirect('/')
    
    db.init_app(app)
    return app
