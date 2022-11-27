# Learn Spring Boot 3

### Java 17/18/19 required

## actuator 
http://localhost:9999/

## REST
```bash
http -v POST :8080/api/role name=user
http -v POST :8080/api/role name=admin

http -v GET :8080/api/role
```

## GraphQL
query roles support only


## QueryDSL support
see commandLineRunner in com.example.sb3.App

tested both with java and kotlin