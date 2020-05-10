#!/bin/bash

OLD_VERSION=1.0.0
VERSION=1.0.0
CONTAINER_NAME=races-services

docker kill raceServices
docker rm raceServices

mvn clean install

docker rmi $CONTAINER_NAME:$OLD_VERSION
docker build -t $CONTAINER_NAME:$VERSION .

docker save -o $CONTAINER_NAME.tar $CONTAINER_NAME:$VERSION
docker rmi $CONTAINER_NAME:$VERSION

docker load -i $CONTAINER_NAME.tar
rm -R target
rm $CONTAINER_NAME.tar

