#!/bin/bash
docker exec -it database /bin/bash -c "mysqldump -u racemgmt -pRaceManagement race_management_db > /scripts/dump_races.sql"
