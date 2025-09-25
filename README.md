# 🧩 CLEAN & HEXAGONAL ARCHITECTURE


Este repositorio reúne **ejemplos prácticos** de proyectos diseñados con los principios de  
**Arquitectura Limpia (Clean Architecture)** y **Arquitectura Hexagonal (Ports & Adapters)**.

El objetivo es mostrar diferentes enfoques y buenas prácticas para organizar aplicaciones,  
manteniendo un código desacoplado, mantenible y fácil de testear.
> ⚠️ Todos los proyectos aquí presentes son **demos sanitizadas**.  
> No contienen datos ni configuraciones reales de empresas o clientes.

---

## 📂 Contenido

Cada carpeta contiene un proyecto independiente, con su propio `README.md` y ejemplos:

- `01-basic-hexagonal-api` → Ejemplo mínimo de API REST con puertos y adaptadores.

- *(más proyectos próximamente...)*

---

## 🏗️ Tecnologías utilizadas

- **Java 17+** y **Kotlin** (según el proyecto)
- **Spring Boot**
- **JUnit / Mockito / MockK**
- **Mapstruct** 
- **Lombok**
- **H2 Database** para pruebas
- **Postgres SQL** para pruebas
- **OpenAPI/Swagger** para documentación de endpoints
- **Docker Compose** *(en algunos proyectos, para entornos simulados)*

---

## 🚀 Cómo ejecutar un proyecto

Ejemplo con el proyecto `Fake-api`:

```bash
cd fake-api
./mvnw spring-boot:run
