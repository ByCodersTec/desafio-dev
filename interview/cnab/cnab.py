from flask import request, jsonify, redirect, url_for, current_app as app, render_template

from interview.cnab import cnab_bp as bp
from flask_jwt_extended import (jwt_required)
from interview import db

from interview.models import Cnab


@bp.route('/')
def cnab_home():
    """
    Will route to cnab home
    ---
    description: Will route to cnab home page, to upload and filter entries.
    parameters:
      - name: username
        in: formData
        type: string
        required: true
      - name: password
        in: formData
        type: string
        required: true
    responses:
      200:
        description: User successfully logged in.
      400:
        description: User login failed.
    """
    title = 'Hello World, Bruno Romano'
    return render_template('hello.html', title=title)


