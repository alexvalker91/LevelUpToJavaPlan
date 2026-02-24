# REST API Architecture Module Practical Task

## Necessary Tools

Java Development Kit 11+

Apache Maven 3.6.0+

Git 2.24+

## Necessary Knowledge

Spring Boot 2 / Spring Boot 3.X 

##Task

(40 points)

##### 1.	Create maven project with 5 modules :
    - jmp-domain
    - jmp-dto
    - jmp-service-api
    - jmp-cloud-service-impl
    - jmp-service-rest

##### 2.	Create the fallowing classes under jmp-domain module :
    - [User]
        Long id;
        String name;
        String surname;
        LocalDate birthday;

    - [Subscription]
        Long id;
        User user;
        LocalDate startDate;

##### 3.	Create the fallowing classes under jmp-dto module :
    - [UserRequestDto]
        Long id;
        String name;
        String surname;
        String birthday;

    - [SubscriptionRequestDto]
        Long id;
        Long userId;

    - [UserResponseDto]
        Long id;
        String name;
        String surname;
        String birthday;

    - [SubscriptionResponseDto]
        Long id;
        Long userId;
        String startDate;

##### 4.	Create UserController under jmp-service-rest module with following methods:
    - createUser(UserRequestDto);
    - updateUser(UserRequestDto);
    - deleteUser(Long);
    - getUser(Long);
    - getAllUser();

##### 5.	Use id filed into UserRequestDto only for updating data.
##### 6.	Must be returned UserResponseDto from the following methods :
    - createUser(UserRequestDto);
    - updateUser(UserRequestDto);
    - getUser(Long);
    - getAllUser();

##### 7.	Create ServiceController under jmp-service-rest module with following methods :
    - createSubscription(SubscriptionRequestDto);
    - updateSubscription(SubscriptionRequestDto);
    - deleteSubscription(Long);
    - getSubscription(Long);
    - getAllSubscription();

##### 8.	Use id filed into SubscriptionRequestDto only for updating data.
##### 9.	Must be returned SubscriptionResponseDto from the following methods :
    - createSubscription(SubscriptionRequestDto);
    - updateSubscription(SubscriptionRequestDto);
    - getSubscription(Long);
    - getAllSubscription(); (as list)

##### 10.	Add necessary interfaces/methods to jmp-service-api.
##### 11.	Implement interfaces/methods under jmp-service-api into jmp-cloud-service-impl.
##### 12.	UserController and ServiceController must provide REST API interfaces according to 2nd of REST API maturity.
##### 13.	You can use memory structures to store the data (List, Map..).
##### 14.	Use jmp-cloud-service-impl to implement UserController and ServiceController.
##### 15.   Implement Application class with @SpringBootApplication annotation and main method.
##### 16.	Use lambdas and Java 8 features wherever it’s applicable.
##### 17.   Make sample requests to UserController and ServiceController, provide screenshots/responses.

(40 points)
##### 18.	Use a DB to store the data (List, Map..).
##### 19.	Use spring boot data jpa module to access data.
##### 20.	Use spring converters to convert :
    - UserRequestDto to User
    - User to UserResponseDto
    - SubscriptionRequestDto to Subscription
    - Subscription to SubscriptionResponseDto

##### 21.	Document methods in UserController and ServiceController using Swagger 2 / OpenAPI annotations.
##### 22.   Make sample requests to UserController and ServiceController via Swagger UI, provide screenshots.

(20 points)
##### 23.	Use Java 9 modules wherever it’s applicable.
##### 24.   Implement REST APIs according to all Richardson Maturity Level (0 - 3).
##### 25.   Make sample requests to UserController and ServiceController, provide screenshots/responses.

## References

- [Apache Maven Jar Plugin](https://maven.apache.org/plugins/maven-jar-plugin/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot/)
- [Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)
- [Spring Boot JPA](https://spring.io/projects/spring-data-jpa)
- [Swagger](https://swagger.io/docs/)
- [Dependency Injection](https://springframework.guru/dependency-injection-example-using-spring/)
