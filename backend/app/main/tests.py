from app import create_app
import pytest
import io


@pytest.fixture
def client():
    return create_app().test_client()


def test_get_stores(client):
    response = client.get('/stores')
    assert response.status_code == 200 

def test_upload_not_file(client):
    file_name = "nothing-file.txt"
    data = {
        'image': (io.BytesIO(b"some initial text data"), file_name)
    }
    response = client.post('/uploader', data=data)
    assert response.status_code == 400


def test_upload_CNAB_file(client):
    file = "uploads/CNAB.txt"
    data = {
        'file': (open(file, 'rb'), file)
    }
    response = client.post('/uploader', data=data)
    assert response.status_code == 201

def test_upload_image_file(client):
    image = "uploads/pizza-cat.jpg"
    data = {
        'file': (open(image, 'rb'), image)
    }
    response = client.post('/uploader', data=data)
    assert response.status_code == 400

def test_upload_image_stream(client):
    image_name = "fake-image-stream.jpg"
    data = {
        'image': (io.BytesIO(b"some random data"), image_name)
    }
    response = client.post('/uploader', data=data)
    assert response.status_code == 400
