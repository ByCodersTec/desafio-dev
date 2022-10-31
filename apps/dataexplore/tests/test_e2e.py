"""Here the end-to-end test data explore endpoint."""
import io
from django.test import TestCase
from rest_framework.test import APIClient


class TestDataExplore(TestCase):
    """In this class the data explore endpoints are tested."""
    def setUp(self):
        """This function customize the setup test."""
        self.default_url = '/dataexplore/'
        self.client = APIClient()

    def test_success_upload_file(self):
        """This function test the success of upload files."""
        url = f'{self.default_url}upload-files'
        data = {
            'filename': (io.BytesIO(b"Some data"), "fake-text-file.txt"),
            'description':  'This is a file text.'
        }

        response = self.client.post(url, data=data, format='multipart')
        assert response.status_code == 200
        assert response.context['data'] == 'Your file has been saved!'

    def test_fail_upload_file(self):
        """This function test the success of upload files."""
        url = f'{self.default_url}upload-files'
        data = {
            'description':  'This is a file text.'
        }

        response = self.client.post(url, data=data, format='multipart')
        assert response.status_code == 200
        assert response.context['data'] == 'Error in to save file.'
