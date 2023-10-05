# Spring Boot 3.0 és 3.1 újdonságai

* Kiindulási projekt: `jtechlog-employees-sb3`

## Migráláskor előjött problémák

Swagger: `org.springdoc:springdoc-openapi-starter-webmvc-ui`
Csomagnevek:

* `jakarta.persistence`
* `jakarta.validation`

## Docker compose támogatás (3.1)



## Problem Details (3.0)

https://blog.frankel.ch/problem-details-http-apis/

Küldés: `GET http://localhost:8080/api/employees/foo`

`application.properties`:

```properties
spring.mvc.problemdetails.enabled = true
```

`ResponseEntityExceptionHandler`

```java
@ControllerAdvice
public class EmployeesExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handle(EmployeeNotFoundException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

}
```

* Validation

```java
@ExceptionHandler
public ProblemDetail handle(MethodArgumentNotValidException exception) {
	ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Constraint Violation");
	List<Violation> violations = exception.getBindingResult().getFieldErrors().stream()
			.map((FieldError fe) -> new Violation(fe.getField(), fe.getDefaultMessage()))
			.toList();
	problemDetail.setProperty("violations", violations);
	return problemDetail;
}
```

## Tracing (3.0)

```groovy
implementation 'org.springframework.boot:spring-boot-starter-actuator'
implementation 'io.micrometer:micrometer-tracing-bridge-otel'
implementation 'io.opentelemetry:opentelemetry-exporter-zipkin'
```

* `spring-web` behúzza az `io.micrometer:micrometer-observation` függőséget, ebben van az `Observation`, `ObservationRegistry`
* Induláskor: `ObservationRegistry' that could not be found.`


