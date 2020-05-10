#!/bin/bash

java -jar 'app.jar' --spring.datasource.url=jdbc:mysql://${DATABASE}/race_management_db?useSSL=false --spring.datasource.username=${DB_USER} --spring.datasource.password=${DB_PASS} --server.port=${PORT} --race.files.upload.basepath=${UPLOAD_PATH} --logging.file=${ROUTELOG}/races-%d{yyyy-MM-dd}.log --logging.level.root=${LOGS_SERVER_LEVEL} --logging.level.com.races=${LOGS_APP_LEVEL} --logging.level.org.hibernate=${LOGS_DB_LEVEL} 
