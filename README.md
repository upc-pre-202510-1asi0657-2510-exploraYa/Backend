## AventuraPe Backend

Bienvenido al repositorio del **Backend** de AventuraPe, una aplicación desarrollada con **Spring Boot**, diseñada inicialmente en arquitectura monolítica, pero modularizada por bounded contexts con miras a una futura migración a microservicios.

Este servicio expone múltiples endpoints RESTful para gestionar usuarios, perfiles, publicaciones, favoritos y más. Toda la documentación está disponible mediante Swagger.


### Tecnologías utilizadas

* Java 17
* Spring Boot 3
* Spring Data JPA
* MySQL
* SpringDoc OpenAPI (Swagger UI)
* Maven
* IntelliJ IDEA
* Git + GitHub


###  Estructura del proyecto

El proyecto está organizado por módulos (bounded contexts):

* `users` → Gestión de usuarios
* `profiles` → Perfiles de aventureros y emprendedores
* `publications` → Publicaciones y comentarios
* `favorites` → Publicaciones favoritas


### Configuración local

1. Clona este repositorio:

```bash
git clone https://github.com/upc-pre-202510-1asi0657-2510-exploraYa/Backend.git
```

2. Crea una base de datos en MySQL:

```sql
CREATE DATABASE aventurape_db;
```

3. Configura tu archivo `application.yml` (o usa `application-dev.yml`):

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/aventurape_db
    username: tu_usuario
    password: tu_contraseña
  jpa:
    hibernate:
      ddl-auto: update
```

4. Levanta el proyecto desde IntelliJ IDEA (opción "Run Application").

### Documentación Swagger
Una vez levantado el backend, accede a:

http://localhost:8080/swagger-ui.html

### Endpoints principales
Algunos de los endpoints disponibles:

* `GET /api/v1/users`
* `POST /api/v1/profiles/entrepreneur`
* `GET /api/v1/publication/all-publications`
* `POST /api/v1/favorite-publications/create-favorite-publication`

### Equipo

* Jair Castillo (Desarrollador principal Backend)
* Jimena Cama.
* Paolo Guillen
* Barbara Quezada
