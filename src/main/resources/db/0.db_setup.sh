## Use to run psql db docker image

##################################
#Create docker container - execute it once
##################################
#Create docker volume; it's better to use volumes instead of bind mounts - https://docs.docker.com/storage/volumes/
docker volume create pgdata
docker run --name localpostgres -e POSTGRES_PASSWORD=<pass> -d -p 54320:5432 -v pgdata:/var/lib/postgresql/data postgres
docker exec -i localpostgres psql -U postgres -c "CREATE DATABASE menu WITH ENCODING='UTF8' OWNER=postgres;"

##################################
#Start existing docker container
##################################
sudo docker start localpostgres

##################################
#Enter the container
##################################
docker exec -it localpostgres bash

##################################
#Open database - Inside container
##################################
psql -U postgres menu
