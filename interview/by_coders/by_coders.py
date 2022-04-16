from flask import request, jsonify, redirect, url_for, current_app as app, render_template

from interview.by_coders import coders_bp as bp
from flask_jwt_extended import (jwt_required)
from interview import db

from interview.models import Cnab

from datetime import datetime
from pytz import timezone, utc
from collections import namedtuple

sao_paulo = timezone('Brazil/East')

CNAB = namedtuple('CNAB', 'start end')
CNAB_TIPO = CNAB(0, 1)
CNAB_DATA = CNAB(1, 9)
CNAB_VALOR = CNAB(9, 19)
CNAB_CPF = CNAB(19, 30)
CNAB_CARTAO = CNAB(30, 42)
CNAB_HORA = CNAB(42, 48)
CNAB_LOJA_DONO = CNAB(48, 62)
CNAB_LOJA_NOME = CNAB(62, 81)


def _import_entry(line: bytes):
    if len(line) < 1:
        raise Exception('No data on line')

    _cnab_entry = Cnab()

    _cnab_entry.transaction_type = int(line[CNAB_TIPO.start:CNAB_TIPO.end])
    _cnab_entry.value = float(line[CNAB_VALOR.start:CNAB_VALOR.end]) / 100
    _cnab_entry.cpf = line[CNAB_CPF.start:CNAB_CPF.end].decode("utf-8")
    _cnab_entry.card = line[CNAB_CARTAO.start:CNAB_CARTAO.end].decode("utf-8")
    _cnab_entry.store_owner = line[CNAB_LOJA_DONO.start:CNAB_LOJA_DONO.end].decode("utf-8").strip()
    _cnab_entry.store_name = line[CNAB_LOJA_NOME.start:CNAB_LOJA_NOME.end].decode("utf-8").strip()

    date = line[CNAB_DATA.start:CNAB_DATA.end].decode("utf-8")
    hour = line[CNAB_HORA.start:CNAB_HORA.end].decode("utf-8")
    str_date = f'{date}{hour}'

    date_format = datetime.strptime(str_date, '%Y%m%d%H%M%S')
    loc_sp = sao_paulo.localize(date_format)
    _cnab_entry.occurrence_at = loc_sp.astimezone(utc)
    db.session.add(_cnab_entry)


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


@bp.route('/upload', methods=['POST'])
def upload_file():
    """
    Upload a cnab file
    ---
    description: Will handle a CNAB file treat data and insert on database.
    parameters:
      - name: file
        in: formData
        type: file
        required: true
        description: The file to be imported
    responses:
      200:
        description: File successfully uploaded and imported.
      400:
        description: Error on importing the whole file.
        content:
          - application/json:
            schema:
              message: message error

    :return:
    """
    if len(request.files) != 1:
        return jsonify({
            'message': 'There is no files on request content'
        }), 400

    file = request.files['file']
    contents = file.read()

    lines = contents.split(b'\n')
    i = 0
    for _line in lines:
        try:
            i += 1
            _import_entry(_line)
        except Exception as e:
            app.logger.info(f'Error {str(e)} on line {i}')

    db.session.commit()
    return jsonify({}), 200
