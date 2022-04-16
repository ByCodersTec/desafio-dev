

def init_routes(app):
    from interview.auth import auth_bp
    app.register_blueprint(auth_bp, url_prefix='/api/auth')

    from interview.by_coders import coders_bp
    app.register_blueprint(coders_bp, url_prefix='/api/byCoders')

