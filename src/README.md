# 📑 Sistema de Agenda de Contactos - Spring Boot

Proyecto de gestión de contactos desarrollado con fines educativos bajo estándares de calidad empresarial, aplicando principios **SOLID**, inyección de dependencias y pruebas unitarias automatizadas.

## 🚀 Características del Proyecto
* **Arquitectura Multicapa:** Separación estricta entre capa web de presentación (Thymeleaf) y servicios de datos de la API.
* **API REST Full CRUD:** Endpoints para operaciones GET, POST, PUT y DELETE.
* **Documentación Interactiva:** Integración automática con **Swagger (OpenAPI 3)**.
* **Persistencia:** Conexión relacional robusta con base de datos **MySQL**.
* **Pruebas de Calidad:** Suite de pruebas unitarias implementadas mediante **JUnit 5** y simulación con **MockitoBean** y **MockMvc**.

## 🛠️ Tecnologías Utilizadas
* Java 17
* Spring Boot 3.4.3
* Spring Data JPA / Hibernate
* Thymeleaf (Motor de plantillas HTML)
* MySQL (Base de datos relacional)
* Maven (Gestor de dependencias)
* Swagger / Springdoc OpenAPI UI

## 📋 Requisitos Previos
Antes de ejecutar la aplicación, asegúrate de tener configurado tu entorno:
1. Tener instalado el **JDK 17** o superior.
2. Contar con un servidor local de **MySQL** activo.
3. Crear un esquema o base de datos en tu gestor SQL con el nombre que definiste en tus propiedades.

## ⚙️ Configuración del Entorno (`application.properties`)
Edita el archivo `src/main/resources/application.properties` con tus credenciales de MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/TU_BASE_DE_DATOS
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
```

## 🏃 Acceso a los Módulos de la Aplicación
Una vez que el proyecto se ejecute de forma exitosa en el IDE, puedes ingresar a las siguientes direcciones locales desde tu navegador:

* **Interfaz Web Tradicional (Vistas Thymeleaf):**
  👉 `http://localhost:8081/`
* **Documentación Interactiva de la API (Swagger UI):**
  👉 `http://localhost:8081/swagger-ui/index.html`

---
Desarrollado con dedicación por **Jonathan Yafete** 💻