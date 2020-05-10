#!/bin/bash

mysqlImage=$(docker ps -aqf name=races-database)

dumpMsg=$(docker exec -t $mysqlImage /bin/bash -c "mysqldump -u root -p\${MYSQL_ROOT_PASSWORD:-12345} race_management_db --databases > /dumps/dump_races.sql")
ret=$?
if [ $ret -ne 0 ]; then
        echo "Races Dump failed. Status Code: $ret"
        echo "Message: $dumpMsg"
fi

