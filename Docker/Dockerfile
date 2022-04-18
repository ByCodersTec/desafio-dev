FROM postgres:11.2-alpine
ENV POSTGRES_DB=bancoDev
ENV POSTGRES_PASSWORD=admin
ENV POSTGRES_USER=postgres

copy initial.sql /docker-entrypoint-initdb.d/

EXPOSE 5432