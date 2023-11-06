FROM postgres:16.0

COPY scripts /docker-entrypoint-initdb.d/

ENV POSTGRES_USER=user
ENV POSTGRES_PASSWORD=password
ENV POSTGRES_DB=postgres

EXPOSE 5432
