from flask import request, jsonify, redirect, url_for, current_app as app, render_template

from interview.by_coders import coders_bp as bp
from flask_jwt_extended import (jwt_required)
from interview import db

from interview.models import Cnab


@bp.route('/list')
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

    cnab_list = Cnab.query.all()
    serialized_object = [_c.serialize_cnab_item for _c in cnab_list]
    return jsonify(serialized_object), 200


