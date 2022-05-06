FROM python:3.8-slim-buster

ENV PYTHONUNBUFFERED=1

WORKDIR /src

RUN apt-get update && \
    apt-get -y install gcc libpq-dev

COPY requirements/ requirements/

RUN python3 -m pip install -r requirements/default.txt --no-cache-dir

COPY . /src/
