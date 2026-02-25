# 03. REST API Architecture Module Practical Task Solution

Task description : Practical task - REST API Architecture.docx

## Prerequisites
| Artifact  | Version  | Comment  |
|---|---|---|
| Apache Maven  | 3.6.+ | [Apache Maven Download](https://maven.apache.org/download.cgi) |
| Java | 8.0.x | [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) |

## Install
Execute following command-line:
```
  cd <event-service>
  mvn clean install
```

## Usage
Execute following command-line:
```
java -jar event-service-rest\target\event-service-rest-2.0.0-SNAPSHOT.jar
```

## Debug (port 8000)
```
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar event-service-rest\target\event-service-rest-2.0.0-SNAPSHOT.jar
```

## Swagger access
Navigate:
```
http://localhost:8080/swagger-ui.html
```

Note: clean cache and cookies in case if some issues related to access

## References
- [Swagger UI](https://swagger.io/tools/swagger-ui)
- [Swagger Docs](https://swagger.io/docs)
- [Dependency Injection](https://springframework.guru/dependency-injection-example-using-spring/)
- [Acessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)