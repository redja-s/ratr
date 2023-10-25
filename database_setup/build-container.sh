#!/bin/bash

set -e

if [[ !(-f "Dockerfile") ]]; then
    echo "No Dockerfile found in working directory"
    exit
fi

if [[ !(-d "scripts/") ]]; then
    echo "No 'scripts/' directory found in working directory"
    exit
fi

PSQL_IMAGE_NAME=ratr-psql-local
PSQL_CONTAINER_NAME=ratr-psql

echo "Building container image $PSQL_IMAGE_NAME..."
docker build -t $PSQL_IMAGE_NAME .

echo
echo "Starting container $PSQL_CONTAINER_NAME with previously built image..."
docker run -d --name $PSQL_CONTAINER_NAME -p 5432:5432 $PSQL_IMAGE_NAME

sleep 4

echo
echo "Running SQL scripts against database..."
for file in scripts/*.sql; do
    echo "Executing '$file'..."
    docker exec $PSQL_CONTAINER_NAME psql -d postgres -U user -f /sql_scripts/$(basename "$file")
done

echo
echo "Database setup is done."
