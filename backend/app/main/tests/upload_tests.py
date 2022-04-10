from flask import Flask, Blueprint
import pytest
import io
from app import create_app
from app.main.controllers.TransactionController import InvalidUsage, read_cnab

@pytest.fixture
def client():
    return create_app().test_client()


def test_get_stores(client):
    response = client.get('/stores/')
    assert response.status_code == 200 

def test_upload_not_file(client):
    file_name = "nothing-file.txt"
    data = {
        'image': (io.BytesIO(b"some initial text data"), file_name)
    }
    with pytest.raises(InvalidUsage):
        response = client.post('/transactions/upload', data=data)
        assert response.status_code == 400


def test_upload_CNAB_file(client):
    file = "test_uploads/CNAB.txt"
    data = {
        'file': (open(file, 'rb'), file)
    }
    response = client.post('/transactions/upload', data=data)
    assert response.status_code == 201

def test_upload_image_file(client):
    image = "test_uploads/pizza-cat.jpg"
    data = {
        'file': (open(image, 'rb'), image)
    }
    with pytest.raises(InvalidUsage):
        response = client.post('/transactions/upload', data=data)
        assert response.status_code == 400

def test_upload_wrong_route(client):
    image_name = "fake-image-stream.jpg"
    data = {
        'image': (io.BytesIO(b"some random data"), image_name)
    }
    response = client.post('/uploader', data=data)
    assert response.status_code == 404

def test_read_cnab_file_wrong_charset(client):
    file = "test_uploads/wrong_CNAB.txt"
    data = {
        'file': (open(file, 'rb'), file)
    }
    response = client.post('/transactions/upload', data=data)
    assert response.status_code == 400