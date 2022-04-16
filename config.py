import os
from dotenv import load_dotenv
from sqlalchemy.pool import QueuePool

basedir = os.path.abspath(os.path.dirname(__file__))


def _str_to_bool(s):
    if s in ['True', 'true', '1', True]:
        return True
    elif s in ['False', 'false', '0', False]:
        return False
    else:
        raise ValueError


def _get_bool_env(key, default_value):
    return _str_to_bool(os.getenv(key, default_value))


def _get_arr_env(key, default_value='[]'):
    ret_value = os.getenv(key, default_value)
    return eval(ret_value)


class BaseConfig(object):
    def __init__(self):
        pass

    SECRET_KEY = os.getenv('SECRET_KEY') or 'SECRET_KEY'


class DevConfig(BaseConfig):
    def __init__(self):
        super(DevConfig, self).__init__()

    load_dotenv(os.path.join(basedir, '.env'))
    # Global COnfigurations
    DEBUG = _str_to_bool(os.getenv('DEBUG', False))
    APP_NAME = os.getenv("Interview")
    APP_LONG_NAME = os.getenv('APP_LONG_NAME')
    # SERVER_NAME = os.getenv('SERVER_NAME', "localhost:5000")

    SECRET_KEY = os.getenv('SECRET_KEY') or 'SECRET_KEY'

    # Database
    SQLALCHEMY_DATABASE_URI = os.getenv('DATABASE_URL') or 'sqlite:///' + os.path.join(basedir, 'app.db')
    SQLALCHEMY_ECHO = _str_to_bool(os.getenv('SQLALCHEMY_ECHO', os.getenv('DEBUG', False)))
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    SQLALCHEMY_ENGINE_OPTIONS = {
        'poolclass': QueuePool,
        'pool_size': 10,
        'pool_recycle': 3600,
        'pool_pre_ping': True
    }

    #Open API
    SWAGGER = {
        "title": "Interview bycoders",
        "uiversion": 3,
    }

    #CORS
    CORS_SUPPORTS_ORIGIN = os.getenv('CORS_SUPPORTS_ORIGIN', '*')

    #Cache
    CACHE_TYPE = os.getenv('CACHE_TYPE') or 'SimpleCache'
    CACHE_DEFAULT_TIMEOUT = int(os.getenv('CACHE_DEFAULT_TIMEOUT', 360))
    CACHE_SOURCE_CHECK = True
    if CACHE_TYPE == 'RedisCache':
        CACHE_REDIS_HOST = os.getenv('CACHE_REDIS_HOST')
        CACHE_REDIS_PORT = os.getenv('CACHE_REDIS_PORT')
        CACHE_REDIS_PASSWORD = os.getenv('CACHE_REDIS_PASSWORD')
