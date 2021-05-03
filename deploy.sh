#!/bin/bash

cd affiliate
mvn spring-boot:build-image -DskipTests
cd ..
cd authentication
mvn spring-boot:build-image -DskipTests
cd ..
cd retargeting_branding
mvn spring-boot:build-image -DskipTests
cd ..
sudo docker-compose -f docker-compose.yml up -d 
