FROM python:3.8.5-alpine

ENV HOME=/home/app
ENV APP_HOME=/home/app/backend

# Create directory for the app user
RUN mkdir -p $HOME $APP_HOME

# Create the app user
RUN addgroup -S app && adduser -S -G app app

WORKDIR $APP_HOME

COPY Pipfile Pipfile.lock ./

RUN pip install pipenv --no-cache-dir && pipenv install

COPY . $APP_HOME

RUN chown -R app:app $APP_HOME

USER app
