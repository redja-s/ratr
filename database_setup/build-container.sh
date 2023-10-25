#!/bin/bash

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
docker run -d --name $PSQL_CONTAINER_NAME -p 5432:5432

echo
echo "Running SQL scripts against database..."
for file in scripts/; do
    echo "Executing $file..."
    docker exec $PSQL_CONTAINER_NAME psql -d postgres -U user -d /sql_scripts/$file
done

echo
echo "Database setup is done."
