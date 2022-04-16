

def init_routes(app):
    from interview.auth import auth_bp
    app.register_blueprint(auth_bp, url_prefix='/api/auth')

    from interview.cnab import cnab_bp
    app.register_blueprint(cnab_bp, url_prefix='/api/cnab')

