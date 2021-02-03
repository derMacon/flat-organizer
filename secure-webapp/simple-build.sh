#!/bin/bash

echo build spring project
mvn clean package

echo start docker compose
docker-compose -f docker-compose_production.yml down
docker-compose -f docker-compose_production.yml up --build -d
