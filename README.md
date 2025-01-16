Sintad Management - Prueba Sintad Backend
=========================================

Este proyecto corresponde a un backend para la gestión de **Entidades**, **Tipos de Documentos** y **Tipos de Contribuyentes**, implementado siguiendo principios como **Arquitectura Limpia**, **DDD (Domain-Driven Design)** y **CQRS**. Proporciona una solución robusta para gestionar datos relacionados con empresas y usuarios, integrando autenticación basada en **JWT**.

🛠 Tecnologías utilizadas
-------------------------

*   **Java** 17

*   **Spring Boot** 3

*   **Hibernate/JPA**

*   **MySQL**

*   **JWT** (Json Web Token) para autenticación

*   **Maven** para gestión de dependencias


📂 Estructura del Proyecto
--------------------------

El proyecto está organizado en base a principios de arquitectura limpia y contiene los siguientes módulos principales:

1.  **administration**: Gestión de entidades, tipos de documentos y tipos de contribuyentes.

2.  **iam**: Módulo de autenticación y autorización.

3.  **shared**: Componentes compartidos y utilitarios.


### Principales carpetas y funcionalidades:

*   domain: Representación del dominio del negocio y modelos.

*   application/internal: Servicios de aplicación y manejo de casos de uso (commands y queries).

*   interfaces/rest: Controladores para manejar las API REST.

*   infrastructure/persistence/jpa: Repositorios para interactuar con la base de datos.


🚀 Cómo ejecutar el proyecto
----------------------------

### Prerrequisitos

*   **Java 17**

*   **Maven** instalado en tu sistema.

*   **MySQL** corriendo y configurado.


### Pasos para la ejecución

1.  Clonar el repositorio:
    ```bash
    git clone https://github.com/Sintad-Management/prueba-sintad-backend.git
    cd sintad-management-prueba-sintad-backend
    ```

2.  Configurar las propiedades de la aplicación:
    ```css
    src/main/resources/application.properties
    ```

3.  Instalar las dependencias y compilar el proyecto:
    ```bash
    ./mvnw clean install
    ```

4.  Ejecutar la aplicación:
    ```bash
    ./mvnw spring-boot:run
    ```

5.  Acceder a la aplicación en el navegador:
    ```arduino
    http://localhost:8080
    ```


📋 Principales Endpoints
------------------------

### Autenticación (IAM)

*   **POST** /api/v1/authentication/signup - Registro de usuarios.

*   **POST** /api/v1/authentication/signin - Inicio de sesión.


### Gestión de Entidades

*   **POST** /api/v1/entidades - Crear una nueva entidad.

*   **GET** /api/v1/entidades/{id} - Obtener detalles de una entidad por ID.

*   **PUT** /api/v1/entidades/{id} - Actualizar una entidad.

*   **DELETE** /api/v1/entidades/{id} - Eliminar una entidad.

Para más detalles, consulta la [documentación oficial de Spring Boot](https://spring.io/projects/spring-boot) y la [documentación de Maven](https://maven.apache.org/guides/index.html).

![Swagger](https://i.imgur.com/1NCOyyw.png)

✅ Características principales
-----------------------------

*   Implementación de **DDD (Domain-Driven Design)**.

*   Separación clara de responsabilidades mediante **CQRS**.

*   Uso de JWT para autenticación segura.

*   Integración de repositorios **JPA** con **Hibernate** para persistencia.


📄 License
----------

Este proyecto está bajo la licencia **MIT** - ver el archivo LICENSE para más detalles.
