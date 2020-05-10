#!/bin/bash

java -jar 'app.jar' --races.services.host=${SERVICES} --server.port=${PORT} --files.upload.basepath=${UPLOAD_PATH} --logging.file=${ROUTELOG}/portal-%d{yyyy-MM-dd}.log --logging.level.root=${LOGS_SERVER_LEVEL} --logging.level.com.races=${LOGS_APP_LEVEL} --logging.level.org.hibernate=${LOGS_DB_LEVEL} 
