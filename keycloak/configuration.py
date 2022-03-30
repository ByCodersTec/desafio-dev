import logging

import requests
from requests.models import Response

# User login at http://localhost:8082/auth/realms/YandehLocal/account

DEFAULT_REALM = 'DevChallenge'
DEFAULT_CLIENT_ID = 'dev-challenge'
KEYCLOAK_HOST = 'http://localhost:8081'
MASTER_USER = 'keycloak'
MASTER_PASSWORD = 'keycloak'
USERS = 'admin', 'operator'

logging.basicConfig(level=logging.INFO)
log = logging.getLogger()

# Global
ACCESS_TOKEN = None


def get_headers():
    return {"Authorization": "Bearer %s" % ACCESS_TOKEN}


def get_master_token():
    log.info("Get master access token")

    url = "%s/auth/realms/master/protocol/openid-connect/token" % KEYCLOAK_HOST
    data = {"grant_type": "password", "username": MASTER_USER,
            "password": MASTER_PASSWORD, "client_id": "admin-cli"}
    response = requests.post(url, data=data)

    status_code = response.status_code
    if status_code != 200:
        raise Exception("Invalid response code: %s" % status_code)

    global ACCESS_TOKEN
    ACCESS_TOKEN = response.json()["access_token"]


def analyze_response(response: Response):
    status_code = response.status_code

    if status_code < 200 or status_code > 299:
        raise Exception("Invalid response code: %s" % status_code)


def analyze_creation_response(response: Response, entity):
    status_code = response.status_code

    if 200 <= status_code <= 299:
        if entity:
            log.info("%s created" % entity)
        else:
            log.info("Success")
    elif status_code == 409:
        log.info("%s already exists" % entity)
    else:
        raise Exception("Invalid response code: %s" % status_code)


def create(url, data, entity):
    headers = get_headers()
    response = requests.post(url, json=data, headers=headers)
    analyze_creation_response(response, entity)


def create_realm():
    log.info("Create realm %s" % DEFAULT_REALM)

    url = "%s/auth/admin/realms" % KEYCLOAK_HOST
    data = {"enabled": True, "id": DEFAULT_REALM,
            "realm": DEFAULT_REALM}
    create(url, data, "Realm")


def create_user(user):
    log.info("Create user %s" % user)

    url = "%s/auth/admin/realms/%s/users" % (KEYCLOAK_HOST, DEFAULT_REALM)
    data = {"enabled": True, "attributes": [],
            "username": user, "emailVerified": ""}

    create(url, data, "User")


def create_users():
    for user in USERS:
        create_user(user)


def default_get(url):
    log.info("url = %s" % url)
    headers = get_headers()

    response = requests.get(url, headers=headers)
    analyze_response(response)

    return response.json()


def default_put(url, data):
    headers = get_headers()
    response = requests.put(url, json=data, headers=headers)

    analyze_response(response)


def get_users():
    url = "%s/auth/admin/realms/%s/users" % (
        KEYCLOAK_HOST, DEFAULT_REALM)
    return default_get(url)


def change_password(user):
    username = user["username"]
    user_id = user["id"]
    log.info("Change password of user %s" % username)

    url = "%s/auth/admin/realms/%s/users/%s/reset-password" % (
        KEYCLOAK_HOST, DEFAULT_REALM, user_id)
    data = {"type": "password", "value": username.upper(), "temporary": False}

    default_put(url, data)


def change_passwords(users):
    for user in users:
        change_password(user)


def create_client():
    log.info("Create client")

    url = "%s/auth/admin/realms/%s/clients" % (KEYCLOAK_HOST, DEFAULT_REALM)
    data = {"enabled": True, "attributes": {}, "redirectUris": [],
            "clientId": DEFAULT_CLIENT_ID, "protocol": "openid-connect"}
    create(url, data, "Client")


def get_clients():
    url = "%s/auth/admin/realms/%s/clients?clientId=%s&viewableOnly=true" % (
        KEYCLOAK_HOST, DEFAULT_REALM, DEFAULT_CLIENT_ID)
    return default_get(url)


def user_to_role(user):
    return user.replace("_", "-")


def create_role(url, role):
    log.info("Create role %s" % role)
    data = {"name": role}
    create(url, data, "Role")


def create_roles(client):
    url = "%s/auth/admin/realms/%s/clients/%s/roles" % (
        KEYCLOAK_HOST, DEFAULT_REALM, client["id"])
    for user in USERS:
        create_role(url, user_to_role(user))


def assign_role(user, client, role):
    log.info("Assign %s to %s" % (role['name'], user['username']))

    user_id = user["id"]
    client_id = client["id"]
    url = "%s/auth/admin/realms/%s/users/%s/role-mappings/clients/%s" % (
        KEYCLOAK_HOST, DEFAULT_REALM, user_id, client_id)

    data = [{"id": role['id'], "name": role['name'], "composite": False,
             "clientRole": True, "containerId": client_id}]

    create(url, data, None)


def get_role(roles, username):
    return next(
        (x for x in roles if x["name"] == username), None)


def assign_roles(users, client, roles):
    for user in users:
        role = get_role(roles, user_to_role(user["username"]))
        assign_role(user, client, role)


def get_roles(client):
    client_id = client["id"]
    url = "%s/auth/admin/realms/%s/clients/%s/roles" % (
        KEYCLOAK_HOST, DEFAULT_REALM, client_id)
    return default_get(url)


def change_token_timeout():
    log.info("Change token duration")
    url = "%s/auth/admin/realms/%s" % (KEYCLOAK_HOST, DEFAULT_REALM)

    data = {
        "id": DEFAULT_REALM,
        "realm": DEFAULT_REALM,
        "accessTokenLifespan": 3600,
    }

    default_put(url, data)


def main():
    get_master_token()

    create_realm()

    create_users()
    users = get_users()
    change_passwords(users)

    create_client()
    clients = get_clients()
    pix_client = next(
        (x for x in clients if x["clientId"] == DEFAULT_CLIENT_ID), None)

    create_roles(pix_client)
    roles = get_roles(pix_client)
    assign_roles(users, pix_client, roles)

    change_token_timeout()


if __name__ == "__main__":
    main()
