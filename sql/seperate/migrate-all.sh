#!/bin/sh
read -p "Database host: " dbhost
read -p "Database name: " dbname
read -p "Database username: " dbusername
read -p "Database password: " dbpassword
echo

for file in *.sql; do
    echo "Executing $file.."
    mysql --user="$dbusername" --password="$dbpassword" --host="$dbhost" "$dbname" < "$file" >> migration-log.txt 2>&1
done
