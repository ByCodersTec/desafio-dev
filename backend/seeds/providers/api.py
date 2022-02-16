import os

import requests
from typing import Union

default_headers = {}


class API:

    def __init__(self):
        self.api_url: str = f"http://{os.environ['API_URL']}"

    def post(self, endpoint: str, data: Union[str, dict], headers=None):
        if headers is None:
            headers = {}

        req = requests.post(f"{self.api_url.rstrip('/')}/{endpoint}", data=data, headers=default_headers.update(headers))
        status = req.status_code in [200, 201]

        try:
            return status, req.json()
        except ValueError:
            return status, req.content
        except Exception:
            return False, req.content

    def get(self, endpoint: str, params=None, headers=None):
        if params is None:
            params = {}

        if headers is None:
            headers = {}

        req = requests.get(f"{self.api_url.rstrip('/')}/{endpoint}", params=params, headers=default_headers.update(headers))
        status = req.status_code in [200, 201]

        try:
            return status, req.json()
        except ValueError:
            return status, req.content
        except Exception:
            return False, req.content

