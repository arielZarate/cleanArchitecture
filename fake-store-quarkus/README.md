
# 🏗️ Quarkus Hexagonal Architecture - Fake Store API Integration

Este proyecto implementa una **Arquitectura Hexagonal (Ports & Adapters)** utilizando **Quarkus 3** y **Java 21**, basado en el diseño de mi proyecto previo `api-fake-hexagonal`. Demuestra un desacoplamiento total entre la lógica de negocio y la infraestructura (PostgreSQL y APIs externas).


Consume la API de **FakeStoreAPI** y guarda los productos en **PostgreSQL**.

## 📖 Related Guides & Extensions

Este proyecto utiliza las siguientes capacidades de **Quarkus**:

* **Mutiny 🔄**: Programación reactiva con la librería moderna Mutiny para flujos de datos asíncronos.
* **REST (Jackson) 📦**: Implementación de **Jakarta REST** con soporte completo para serialización y deserialización de JSON mediante Jackson.
* **REST Client 📞**: Cliente especializado para invocar servicios REST externos de forma sencilla y tipada.
* **Hibernate ORM with Panache 💾**: Simplificación de la capa de persistencia mediante el patrón **Repository**, reduciendo el código repetitivo (*boilerplate*).
* **JDBC Driver - PostgreSQL 🐘**: Conector oficial y optimizado para bases de datos PostgreSQL.
* **OpenID Connect (OIDC) 🔐**: Configuración preparada para la seguridad del sistema mediante la verificación de **Bearer tokens**.



## Arquitectura Hexagonal

```
src/main/java/org/arielzarate/
├── domain/
│   ├── model/Product.java              # Entidad del dominio
│   └── ports/
│       ├── in/ProductService.java      # Puerto IN (casos de uso)
│       └── out/ProductProvider.java    # Puerto OUT
├── application/
│   └── ProductUseCase.java            # Implementa ProductService
├── interfaces/
│   └── rest/
│       ├── ProductController.java     # REST controller
│       ├── dto/
│       │   ├── ProductRequest.java
│       │   └── ProductResponse.java
│       ├── mapper/ProductMapper.java
│       └── error/
│           ├── GlobalExceptionHandler.java
│           ├── ApiErrorResponse.java
│           └── exception/
│               ├── CustomException.java
│               └── WebClientException.java
└── infrastructure/
    ├── adapters/
    │   ├── ProductAdapter.java         # Adapter (BD + API)
    │   └── mappers/ProductEntityMapper.java
    ├── persistence/
    │   ├── entity/ProductEntity.java
    │   └── repository/ProductRepository.java
    └── rest/
        ├── client/ApiFakeStoreClient.java
        ├── dto/ProductDto.java
        ├── mapper/ProductRestMapper.java
        └── provider/
            ├── WebClientMethod.java
            └── WebClientProvider.java
```



### application.properties

```properties
# Database
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=1111
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/fake_store_quarkus
# Hibernate
quarkus.hibernate-orm.database.generation=drop-and-create
# Disable Dev Services
quarkus.oidc.devservices.enabled=false
```

## API Endpoints

| Método | Endpoint         | Descripción                                      |
|--------|------------------|--------------------------------------------------|
| GET    | `/products`      | Listar todos (si BD vacía, consume API y guarda) |
| GET    | `/products/{id}` | Buscar por ID (solo BD)                          |
| POST   | `/products`      | Crear producto (guarda en BD)                    |

## Ejemplos de uso

```bash
# Listar todos los productos
curl http://localhost:8080/products

# Buscar producto por ID
curl http://localhost:8080/products/1

# Crear producto
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test Product",
    "price": 99.99,
    "description": "Test description",
    "category": "test",
    "image": "https://example.com/image.jpg",
    "rating": {
      "rate": 4.5,
      "count": 100
    }
  }'
```

## Ejecutar

```bash
./mvnw quarkus:dev
```

Accede a **Dev UI:** http://localhost:8080/q/dev/

#####  [️ariel-zarate@gitHub](https://github.com/arielZarate?tab=repositories)





----
----


This project uses Quarkus, the Supersonic Subatomic Java Framework.

```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/fake-store-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- Mutiny ([guide](https://quarkus.io/guides/mutiny-primer)): Write reactive applications with the modern Reactive
  Programming library Mutiny
- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and
  Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on
  it.
- REST Client ([guide](https://quarkus.io/guides/rest-client)): Call REST services
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus
  REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- OpenID Connect ([guide](https://quarkus.io/guides/security-openid-connect)): Verify Bearer access tokens and
  authenticate users with Authorization Code Flow
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code
  for Hibernate ORM via the active record or the repository pattern
- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing Jakarta REST and
  more
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)

[Related Hibernate with Panache section...](https://quarkus.io/guides/hibernate-orm-panache)

### REST Client

Invoke different services through REST with JSON

[Related guide section...](https://quarkus.io/guides/rest-client)

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

---
---

---
> ### 👤 Proyecto desarrollado por **[Ariel Zarate](https://github.com/arielZarate)** | 2026 🚀



