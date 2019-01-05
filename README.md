SIN example project
===

Deployment
---

- Run WildFly server
- Navigate to this project's root folder and open a command line
- Run the following command to deploy the application
```mvn clean package wildfly:deploy```
- Open your browser and navigate to `localhost:8080/sin-example-project`

Running tests
---

```
mvn clean test -Parq-wildfly-remote
```
