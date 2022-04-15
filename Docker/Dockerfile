FROM postgres:latest
ENV POSTGRES_DB=bancoDev
ENV POSTGRES_PASSWORD=admin
ENV POSTGRES_USER=postgres

copy ./scripts/ /docker-entrypoint-initdb.d/

EXPOSE 5432