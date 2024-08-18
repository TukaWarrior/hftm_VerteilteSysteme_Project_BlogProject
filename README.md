# Blog Project - Java Backend
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
    - [x] Blog object class
    - [x] Usage of @Path parameters
- [x] PU 03 - HTTP Communication 2
    - [x] Generating Responses
    - [x] Exception Handling
- [x] PU 04 - HTTP Communication 3
    - [x] Implement http DELETE, PUT, PATCH
    - [x] Query and Header pareameters
- [x] PU 05 - HTTP Communication 4
    - [x] Validation
    - [x] Mapping with schematics
    - [x] Testing of API

# Useful subsidiary tools
- **Json Web Tokens:**    https://jwt.io/
- **Bruno API Client:**    https://www.usebruno.com/


# Version Controll
This project currently uses the folowing versions of various dependencies.
maven: 3.9.9: apache-maven-3.9.9
Java version: java 21.0.1 2023-10-17 LTS: jdk-21


# Troubleshooting Guide
This troubleshooting guide provides step-by-step instructions to resolve common issues when building or running this Quarkus project.

## If you are unsure what the problem is:
1. **Check for Code Errors:** Ensure that the issue is not caused by faulty code that leads to a compile error.

2. If the problem persists, try cleaning the project using the Maven wrapper:

```shell script
./mvnw clean
```
If ./mvnw clean fails, there may be a problem with the maven wrapper. 
If the problem persists, clean install the project using the maven wrapper. See chapter "Problems with the maven wrapper".

Try clean installing the project using the maven wrapper. 
```shell script
./mvnw clean install
```
If the ./mvnw clean install command fails but ./mvnw clean succeeds, there may be a problem with the dependencies. See chapter "Problems with dependencies".

Try running the application using the maven wrapper:
```shell script
./mvnw quarkus:dev
```
If the ./mvnw clean and ./mvnw clean install commands succeeded, but the ./mvnw quarkus:dev command fails, there may be a problem with the quarkus configuration. See chapter "Problems with quarkus".



Additionally, you can check if the programm starts using the local maven installation. Clean install and run the project using your local maven installation:

```shell script
mvn clean
```
If mvn clean fails, there may be a problem with the local maven installation. See chapter "Problems with Java and Maven"

Try clean installing the project using the local maven installation.
```shell script
mvn clean install
```
If the mvn clean install command fails but mvn clean succeeds, there may be a problem with the dependencies. See chapter "Problems with dependencies".

Try running the application using the local maven installation:
```shell script
mvn quarkus:dev
```
If the mvn clean and mvn clean install commands succeeded, but the .mvn quarkus:dev command fails, there may be a problem with the quarkus configuration. See chapter "Problems with quarkus".
If the command succeeds and the application starts, there may be a problem with the maven wrapper. See chapter "Problem with the maven wrapper".


## Problems with dependencies
If dependencies are the cause of problems, reinstall them. To do so, first delete the current dependencies from the lcoal maven repository. 

Delete the local dependency repository:
C://User/*name*/.m2/repository
```shell script
rmdir /s /q C:\Users\<YourUsername>\.m2\repository\
```

Then reinstall the dependencies:
```shell script
mvn clean install
```
If the command succeeds, the dependencies are successfully reinstalled. 
If the mvn clean install fails, dependencies may be incompatible. Update the dependencies to their newest release. Caution! The newest releases of dependencies may also not be fully compatible. 
```shell script
mvn versions:use-latest-releases
```
Then reinstall the dependencies again:
```shell script
mvn clean install
```
If the commands succeeded, the dependencies are now updated to the newest release. If the mvn clean install command failed, the updated dependencies may be incompatible Solve the incompatibilities or roll back to a previously working commit. If any mvn command fails, there may be a problem with the local maven installation. See chapter "Problems with Java and Maven.


If the mvn clean install command succeeds, run the application using the local maven installation:
```
mvn quarkus:dev
```
If mvn quarkus:dev fails, there may be a problem with the quarkus configuration. See chapter "Problems with quarkus".
If the command succeeds and the application starts, the local maven installation and the dependencies are functional. 

## Problems with the Maven Wrapper
If you have issues with the maven wrapper:
The Maven Wrapper is a script included in a project that allows you to run Maven without needing to have Maven installed globally on your system.
It ensures that a specific version of Maven, as defined in the project, is used, regardless of what version is installed globally.

Check the version of the maven version provided by the wrapper inside the project. Make sure the maven version displayed corresponds with the minimum required version listed in the chapter "Required Dependencies.
```shell script
./mvnw --version
```

If the version doesen't match the required maven version or if the command fails, delete the wrapper and then reinstall it. To do so, delete the .mvn folder inside the project root.
```
Remove-Item -Recurse -Force .mvn
```

Then rebuild the maven wrapper. 
```shell script
mvn wrapper:wrapper
```
If rebuilding the maven wrapper fails, there may be a problem with the local maven installation. See chapter "Problems with java and maven".



## Problems with java and maven
If any of the mvn commands is not functioning correctly, your local java or maven installation may be broken or incompatible. 

1. Check the java version
```shell script
java -version
```
For the minimum required version, check chapter "Version Controll"
If the --version command shows a lower or different version than the minimum required version, update the Java jdk installation. If it is not working after the installation, check the JAVA_HOME system environment variable. See below.
If the command fails with a message containing "is not recognized as the name of a cmdlet", the system didn't detect the java installation. Install java or check the JAVA_HOME system environment variable. See below.


2. Check the maven version
```shell script
mvn -version
```
For the minimum required version, check chapter "Version Controll"
If the --version command shows a lower or different version than the minimum required version, update the Maven installation. If it is not working after the installation, check the PATH system environment variable. See below.
If the command fails with a message containing "is not recognized as the name of a cmdlet", the system didn't detect the maven installation. Install maven or check the PATH system environment variable. See below.

**System Environment Variable JAVA_HOME**
Make sure that your JAVA_HOME environment variable is pointing to a jdk installation that fullfills the minimum required version. 
E.g. JAVA_HOME = C:\Program Files\Java\jdk-21

**System Environment Variable JAVA_HOME**
Make sure that the PATH system environment variable lists a maven installation of at least the minimum requried version.
E.g. Path = c:\program files\apache-maven-3.9.9\bin








## Authentication
Currently, because of time constraints, the authentication using keycload is not fully implemented. But in the future, I will implement the following roles.

- Admin: 
Has access to everything. Can get, post, patch or delete blogs, comments and other entities I may add. 
- User
Users require a login. They can get and post blogs and comments. They can patch and put the blogs and comments they created.
- Guest
A guest is a role that doesen't require an loging. They can only get blogs and comments. 
- Moderator
If the moderator role will exists depends on the time I have avaiable to test out and implement some features. If I have the time to also add user profile entities, I may also add an moderator. Unlike the administrator, who has no restrictions at all, a moderator can get and push all blogs and comments, but he can not alter them. Altering them is prohibited because if the moderator is malicious, he could make it looklike as a user wrote something that they did not. The moderator has also no access to modifying user profiles. 

##### My current mental state when working on this:
![Help me](https://media.tenor.com/10Zdx_RXqgcAAAAM/programming-crazy.gif)