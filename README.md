# [School-Management](https://github.com/Marc0Franc0/School-Management#school-management)

El proyecto tiene la posibilidad de gestionar una institución educativa, con el objetivo de facilitar la administración de estudiantes, profesores, materias y notas.

## Características
- Registro de usuario e inicio de sesión con autenticación JWT 
- Cifrado de contraseña usando BCrypt 
- Autorización basada en roles con Spring Security
- CRUD para las entidades "Student", "Teacher", "Subject" y "Note"

## Tecnologías
- Spring Boot 3.0 
- Spring Security 
- JSON Web Tokens (JWT)
- BCrypt 
- Maven

## Ejecución
1. Clonar repositorio: git clone https://github.com/Marc0Franc0/School-Management.git
2. Ir al directorio del proyecto: cd School-Management
3. Seguir pasos para ejecución con Docker o Maven

## Requerimientos para ejecutar con Docker

Para construir y ejecutar la aplicación necesita:
- [Docker](https://www.docker.com/products/docker-desktop/)

Ejecutar localmente

```shell
docker compose up
```

Dirigirse a: [http://localhost:9090/](http://localhost:9090/)

## Requerimientos para ejecutar con Maven

Para construir y ejecutar la aplicación necesita:

- [JDK 17+](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3+](https://maven.apache.org)

Configurar datos de la base de datos MySQL: [application.properties](https://github.com/Marc0Franc0/School-Management/blob/main/src/main/resources/application.properties)

Configurar JWT: [application.properties](https://github.com/Marc0Franc0/School-Management/blob/main/src/main/resources/application.properties)
- jwt.secret.key = 76397924423F4528482B4D6251655468576D5A7134743777217A25432A46294A 
(Se utiliza para firmar los tokens)
- jwt.time.expiration = 86400000 (equivalente a un día)

Ejecutar localmente

```shell
mvn clean install
```
```shell
mvn spring-boot:run
```

Dirigirse a: [http://localhost:8080/](http://localhost:8080/)
