# Fake Store API - Arquitectura hexagonal

API REST desarrollada en Kotlin con Spring Boot utilizando arquitectura hexagonal.

## Estructura del Proyecto

```
src/main/kotlin/com/arielZarate/fake_api_hexagonal/
├── Application.kt                    # Clase principal Spring Boot
├── domain/
│   ├── model/
│   │   └── Product.kt               # Modelo de dominio
│   └── ports/
│       ├── in/
│       │   └── ProductService.kt   # Puerto de entrada (use case)
│       └── out/
│           └── ProductProvider.kt   # Puerto de salida (interfaz adapter)
├── application/
│   └── ProductUseCase.kt          # Caso de uso
├── infraestructure/
│   ├── adapters/
│   │   └── ProductAdapter.kt     # Adapter que conecta con la persistence
│   ├── persistence/
│   │   ├── model/
│   │   │   ├── ProductEntity.kt  # Entidad JPA
│   │   │   └── RatingEntity.kt
│   │   ├── mapper/
│   │   │   ├── ProductEntityMapper.kt
│   │   │   └── RatingMapper.kt
│   │   └── repositories/
│   │       └── ProductRepository.kt
│   └── rest/
│       ├── config/
│       │   └── WebClientConfig.kt       # Configuración WebClient
│       ├── provider/
│       │   ├── WebClientProvider.kt   # Proveedor HTTP genérico
│       │   └── WebClientMethod.kt     # Enum métodos HTTP
│       ├── mapper/
│       │   └── ProductMapper.kt      # Mapper DTO -> Domain
│       ├── model/
│       │   ├── ProductDto.kt
│       │   └── RatingDto.kt
│       └── ApiFakeStoreClient.kt     # Cliente API Fake Store
└── interfaces/
    ├── ProductController.kt       # Controlador REST
    ├── model/
    │   ├── ProductRequest.kt
    │   └── ProductResponse.kt
    ├── mappers/
    │   └── ProductMapper.kt
    └── error/
        ├── ApiErrorResponse.kt
        ├── exception/
        │   └── CustomException.kt
        └── GlobalExceptionHandler.kt
```

## Tecnologías

- **Kotlin** 1.9.10
- **Spring Boot** 3.4.0
- **Spring Data JPA** - Persistencia en PostgreSQL
- **Spring WebFlux** - Cliente HTTP reactivo (WebClient)
- **PostgreSQL** - Base de datos
- **Gradle** - Build tool

## API Endpoints

| Método | Endpoint | Descripción |
|--------|----------|------------|
| GET | `/api/v1/products` | Listar todos los productos |
| GET | `/api/v1/products/{id}` | Obtener producto por ID |
| POST | `/api/v1/products` | Crear nuevo producto |

### Ejemplos

```bash
# Obtener todos los productos
curl http://localhost:8080/api/v1/products

# Obtener producto por ID
curl http://localhost:8080/api/v1/products/1

# Crear producto
curl -X POST http://localhost:8080/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Producto test",
    "price": 99.99,
    "description": "Descripcion del producto",
    "category": "electronics",
    "image": "https://example.com/image.jpg",
    "rating": { "rate": 4.5, "count": 100 }
  }'
```

## Funcionamiento

1. La primera vez que se llama a `/api/v1/products`, consume la API externa `https://fakestoreapi.com/products`
2. Los productos se guardan en la base de datos PostgreSQL
3. Las siguientes llamadas retornan los datos desde la DB (más rápido)
4. Si un producto no existe en la DB, lo busca en la API externa y lo guarda

## Docker

### Iniciar contenedor

```bash
docker-compose up --build -d
```

### Detener contenedor

```bash
docker-compose down
```

### Reiniciar contenedor

```bash
docker-compose down -v
```

## Configuración

### application.yml

```yaml
server:
  port: 8080
  servlet:
    context-path: /api/v1

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fake_store
    username: postgres
    password: 1111
  jpa:
    hibernate:
      ddl-auto: update
```

## Compilación

### Compilar

```bash
./gradlew compileKotlin
```

### Build completo

```bash
./gradlew clean build
```

### Ejecutar tests

```bash
./gradlew test
```

## Errores comunes

### 403 FORBIDDEN

Si la API externa retorna 403, significa que Cloudflare está bloquando las requests. 
Soluciones:
- Verificar que no haya una VPN activa
- Desde el servidor donde corre la app, probar: `curl https://fakestoreapi.com/products`

### SQL Error: value too long

El campo descripción es muy largo (> 700 caracteres). Se trunca automáticamente.

## Colección Postman

Encontrás la colección en: `Postman/Fake store api.postman_collection.json`

## Documentación publicada

https://documenter.getpostman.com/view/12679400/2sAYHxo4kp