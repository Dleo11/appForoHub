
# API - Foro Hub

## Descripción
**Foro Hub** es una API RESTful diseñada para replicar el backend de un foro donde los participantes pueden crear y responder preguntas sobre diversos temas. Inspirado en la plataforma de Alura, este proyecto fomenta la colaboración entre estudiantes, profesores y moderadores.

La API permite gestionar tópicos, usuarios y respuestas mediante operaciones CRUD (Create, Read, Update, Delete), implementando las mejores prácticas de desarrollo y utilizando herramientas modernas como **Spring Boot**.

---

## Características principales
- **CRUD completo** para manejar tópicos.
- **Validaciones** basadas en reglas de negocio.
- **Persistencia** con base de datos MySQL.
- **Autenticación** mediante JWT para proteger el acceso.
- **Documentación interactiva** con Swagger.

---

## Requisitos previos
- **JDK**: Java 17
- **Maven**: Administrador de dependencias.
- **Base de datos**: MySQL.

---

## Instalación y configuración

### Clonar el repositorio
```bash
$ git clone https://github.com/Dleo11/appForoHub.git
$ cd appForoHub
```

### Configurar la base de datos
Asegúrate de que MySQL esté instalado y funcionando. Configura la base de datos según lo definido en `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

Crea una base de datos vacía llamada `foro_hub`:
```sql
CREATE DATABASE foro_hub;
```
La configuración de las tablas y esquemas se manejará automáticamente mediante Flyway al iniciar la aplicación.

### Instalar dependencias
Ejecuta el siguiente comando para instalar las dependencias necesarias:
```bash
$ mvn clean install
```

### Ejecutar la aplicación
Inicia la aplicación con:
```bash
$ mvn spring-boot:run
```
La aplicación estará disponible en: `http://localhost:8080`

---

## Endpoints principales
- **Autenticación**:
  - `POST /auth/login`: Inicia sesión y obtiene un token JWT.
- **Tópicos**:
  - `GET /topics`: Lista todos los tópicos.
  - `GET /topics/{id}`: Muestra un tópico específico.
  - `POST /topics`: Crea un nuevo tópico.
  - `PUT /topics/{id}`: Actualiza un tópico existente.
  - `DELETE /topics/{id}`: Elimina un tópico.

---

## Documentación con Swagger
Swagger está habilitado para facilitar la exploración de los endpoints:
- **API Docs**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

---

## Tecnologías utilizadas
- **Spring Boot**: Desarrollo rápido y robusto de aplicaciones.
- **JWT**: Autenticación segura.
- **Flyway**: Migración y gestión de bases de datos.
- **MySQL**: Sistema de gestión de bases de datos relacional.
- **Swagger**: Documentación de API interactiva.
- **Lombok**: Reducción de código boilerplate.

---

## Autor
Proyecto desarrollado por **merinodleo** con apoyo de **Alura Latam**.

- **GitHub**: [Dleo11](https://github.com/Dleo11)
- **LinkedIn**: [Leodan Merino](https://www.linkedin.com/in/mdleodan/)

---

## Licencia
Este proyecto está bajo la licencia MIT. Consulta el archivo `LICENSE` para más detalles.
