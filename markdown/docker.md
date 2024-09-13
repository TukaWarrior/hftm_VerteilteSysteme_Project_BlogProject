[Back to Main Documentation](../README.md)

Create a jar
```bash
./mvnw package
```

or reate Fat jar file, including all the dependencies. Otherwise they will be taken from  target/quarkus-app/lib/
```bash
./mvnw package -D quarkus.package.type=uber-jar -X
```

Create the docker image (edit docker.jvm if fat jar was used. )
```bash
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/blogproject .
```
Use in dockerile.jvm for fat jar. 
```bash
COPY --chown=185 target/blogproject-1.0.0-SNAPSHOT-runner.jar /deployments/```
```


3. Start image
```bash
docker run --network blogproject-nw -i --rm -p 8080:8080 lucab/ch.hftm/blogproject:1.0.0-SNAPSHOT

```



Create docker network for blogproject
```bash
docker network create blogproject-nw
```

Create keycloack container
```bash
docker run --name keycloak --network blogproject-nw -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -e KC_HTTP_PORT=8180 -e KC_HOSTNAME_URL=http://keycloak:8180 -p 8180:8180 -d quay.io/keycloak/keycloak:25.0.5 start-dev
```

### (Optional) Create keycloack container with db and persistance using mysql
1. Start MySQL-Container with Volume-Mapping starten.
```bash
docker run --name keycloak-mysql --network blogproject-nw -v keycloak-db:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=vs4tw -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbuser -e MYSQL_DATABASE=keycloakdb -d mysql:8.0
```

2. Start keycloack with db configuration
```bash
docker run --name keycloak --network blogproject-nw -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -e KC_HTTP_PORT=8180 -e KC_HOSTNAME_URL=http://keycloak:8180 -p 8180:8180 -e KC_DB=mysql -e KC_DB_URL=jdbc:mysql://keycloak-mysql:3306/keycloakdb -e KC_DB_USERNAME=dbuser -e KC_DB_PASSWORD=dbuser -d quay.io/keycloak/keycloak:25.0.5 start-dev

```

4. Run quarkus application
```bash
docker run --name quarkus-blogproject --network blogproject-nw -i --rm -p 8080:8080 lucab/ch.hftm/blogproject:1.0.0-SNAPSHOT
```



5. Push image
```bash
docker tag lucab/ch.hftm/blogproject:1.0.0-SNAPSHOT ghcr.io/tukawarrior/hftm_verteiltesysteme_project_blogproject:1.0.0-SNAPSHOT
```
```bash
docker push ghcr.io/tukawarrior/hftm_verteiltesysteme_project_blogproject:1.0.0-SNAPSHOT
```