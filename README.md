# BlogProject
This is my school project, that is part of the "Distributed Systems" course from the third year of study at the HFTM in Grenchen.
The goal is to build up a web-api backend, which I can utilise and extend in future courses. 

As for now, the application allows the user to create, view and edit blog entries via http requests.

This project uses the java framework Quarkus.

## Table of Contents
1. [Quarkus](#quarkus)
    1. [Running the application in dev mode](#running-the-application-in-dev-mode)
    2. [Accessing the Webview](#accessing-the-webview)
    3. [Packaging and running the application](#packaging-and-running-the-application)
    4. [Creating a native executable](#creating-a-native-executable)
    5. [Related Guides and Links](#related-guides-and-links)
2. [HTTP Request Examples](#http-request-examples)
3. [Administrative](#administrative)
    1. [Grading](#grading)
    2. [Roadmap](#roadmap)
    
# Quarkus

### Running the application in dev mode
To run the application in dev mode, run the following command in the terminal from the root of the project. Dev mode enables live coding.
```shell script
./mvnw quarkus:dev
```
If the previous command is not working:
```shell script
./mvnw compile quarkus:dev
```

## Accessing the Webview
After the project has been started, the following two links should be accessible:

**Homepage:**       http://localhost:8080/hello

**Dev UI:**         http://localhost:8080/q/dev/

**Swagger UI:**    http://localhost:8080/q/swagger-ui/

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/blogproject-1.0.0-SNAPSHOT-runner`

## Related Guides and Links

- **Quarkus Website:** https://quarkus.io/

- **REST JSON-B serialization support for Quarkus REST:**  https://quarkus.io/guides/rest#json-serialisation

- **Easily start your REST Web Services:** https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources

- **Building native executables:** https://quarkus.io/guides/maven-tooling


# HTTP Request Examples
Once the application is running, following http requests are possible (Examples using httpie):

| Entity | Type | Command | Description |
| --- | --- | --- | --- |
| Blog | GET | ```http GET http://localhost:8080/blogs``` | Lists all blogs, 4 per page |
| Blog | GET | ```http GET http://localhost:8080/blogs?page=2``` | List all blogs, page 2 |
| Blog | GET | ```http GET http://localhost:8080/blogs/1``` | List blog with id 1 |
| Blog | POST | ```http POST http://localhost:8080/blogs title="New Blog" content="This blog is new!"```| Post new blog with title and content (title and content required) |
| Blog | DELETE | ```http DELETE http://localhost:8080/blogs/1``` | Delete blog with the id 1 (id required) |
| Blog | PUT | ```http PUT http://localhost:8080/blogs/1 title="This blog was replaced" content="This content was replaced"``` | Replace blog with id 1 (id, title content required) |
| Blog | PATCH | ```http PATCH http://localhost:8080/blogs/1 content="This content was replaced"```  | Replace attributes of blog with id 1 (id required. Empty "" or null attributesare ignored) |
| Comment | GET | ```http GET http://localhost:8080/blogs/1/comments``` | Lists all comments on blog with id 1, 10 per page |
| Comment | GET | ```http GET http://localhost:8080/blogs/1/comments?page=2``` | Lists all comments on blog with id 1, page 2 |
| Comment | POST | ```http GET http://localhost:8080/blogs/1/comments content="This is a comment"``` | Post new comment on blog with id 1 with content (content required) |

# Administrative
### Grading
The grading of this project will be done by collaborator simeonlin.
This project will be randomly sampled and graded after every tasks.
Each sample will be assessed according to the following scheme with a maximum of 4 points:
* Initial repository status / starting point before the last task (max. 2 points)
    * ✅ 2 points: Suitable project setup and evidence of initiative.
    * ☑️ 1 point: Suitable project setup, but little evidence of initiative. (e.g., Only commits with sample solutions)
    * ❌ 0 points: Starting point is incorrect.
* Last task (max. 2 points)
    * ✅ 2 points: Current task has been implemented or it is evident that the student has engaged appropriately with the current task.
    * ☑️ 1 point: Little effort towards the last task is evident.
    * ❌ 0 points: No effort towards the last task is evident.

### Roadmap
This list keeps track of currently open and completed tasks. 
- [x] PU 01 - Basic Setup
    - [x] Create a new private github Project
    - [x] Add simeonlin as a collaborator
    - [x] Push changes to main
- [x] PU 02 - Database Access
    - [x] Create a new Quarkus project
    - [x] Start and test the project
    - [x] Access the mysql docker database
- [x] PU 03 - HTTP Communication 1
    - [x] Implement http GET and POST
    - [x] Generating JSON files
    - [x] Usage of @Path parameters
- [x] PU 04 - HTTP Communication 4
    - [x] Implement http DELETE, PUT, PATCH
    - [x] Response and Header pareameters
    - [x] Exception-Handling


# Useful subsidiary tools
**Json Web Tokens:**    https://jwt.io/