#!/usr/bin/env bash

if [[ "$#" -eq 0 ]]; then
  echo "Please supply an argument"
  echo "e.g. ./query_container.sh \"SELECT * FROM films\""
  exit

fi
CONTAINER_NAME=ratr-psql

docker exec $CONTAINER_NAME psql -U user -d postgres -c "$1"
