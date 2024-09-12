[Back to Main Documentation](../README.md)

Create a jar
```
./mvnw package
```

or reate Fat jar file, including all the dependencies. Otherwise they will be taken from  target/quarkus-app/lib/
```
./mvnw package -D quarkus.package.type=uber-jar -X
```

Create the docker image (edit docker.jvm if fat jar was used. )
```
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/blogproject .
```
Use in dockerile.jvm for fat jar. 
```
COPY --chown=185 target/blogproject-1.0.0-SNAPSHOT-runner.jar /deployments/```
```


3. Start image
```
docker run --network blogproject -i --rm -p 8080:8080 quarkus/blogproject
```