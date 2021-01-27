#!/bin/bash

echo create external docker volume
docker volume create --name=db-data

echo build spring project
mvn clean package

docker-compose -f docker-compose_production.yml down
docker-compose -f docker-compose_production.yml up --build -d
