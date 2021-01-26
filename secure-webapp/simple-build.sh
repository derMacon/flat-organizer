#!/bin/bash

mvn clean package
docker-compose -f docker-compose_production.yml down
docker-compose -f docker-compose_production.yml up --build -d

