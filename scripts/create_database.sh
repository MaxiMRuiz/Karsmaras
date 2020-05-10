#/bin/bash

mysqlImage=$(docker ps -aqf name=races-database)

createMsg=$(docker exec -it $mysqlImage /bin/bash -c "mysql -u root -p\${MYSQL_ROOT_PASSWORD:-12345} < /scripts/Create_database.sql")
createCode=$?
if [ $createCode -ne 0 ]; then
        echo "Initialize Database failed. Status Code: $createCode"
        echo "Message: $createMsg"
fi
