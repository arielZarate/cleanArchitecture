# api fake store api arquitectura hexagonal




## arquitecturas
```kotlin

src
├── domain
│   ├── model
│   │   └── Product.kt
│   └── ports
│       ├── in
│       │   └── GetProductService.kt
│       └── out
│           └── ProductOut.kt
├── application
│   └── services
│       └── GetProductUseCase.kt
├── infrastructure
│   ├── adapters
│   │   ├── ProductOutAdapter.kt           # Adaptador para la salida de datos del producto
│   │   └── ProductApiAdapter.kt           # Adaptador para interactuar con APIs externas
│   ├── rest
│   │   └── apiFakeStore
│   │       └── model                     # Modelos relacionados con FakeStore (otra API o fuente de datos)
│   ├── persistence
│   │   ├── mapper
│   │   │   ├── ProductEntityMapper.kt    # Mapeo entre la entidad Product y su representación en la base de datos
│   │   │   └── RatingEntityMapper.kt     # Mapeo entre la entidad Rating y su representación en la base de datos
│   │   ├── model
│   │   │   ├── ProductEntity.kt          # Entidad para la persistencia de datos de productos
│   │   │   └── RatingEntity.kt           # Entidad para la persistencia de datos de valoraciones
│   │   └── repositories
│   │       └── ProductRepository.kt      # Repositorio para interactuar con la base de datos
├── interfaces
│   ├── rest
│   │   ├── ProductController.kt           # Controlador REST para manejar las solicitudes de productos
│   │   ├── models
│   │   │   ├── ProductRequest.kt          # Modelo de solicitud para entrada de datos
│   │   │   ├── ProductResponse.kt         # Modelo de respuesta para salida de datos
│   │   └── mappers
│   │       ├── ProductRequestMapper.kt    # Mapper para convertir ProductRequest en modelo de dominio
│   │       └── ProductResponseMapper.kt   # Mapper para convertir Product en ProductResponse
│   ├── error
│   │   ├── exception
│   │   │   └── CustomException.kt         # Excepción personalizada
│   │   └── GlobalExceptionHandler.kt      # Manejador global de excepciones
└── utils
    └── MockProducts.kt

```


## Public Api

- URL FOR PUBLISHED DOCUMENTACTION

### https://documenter.getpostman.com/view/12679400/2sAYHxo4kp

###     Colection name
Fake store api

#### /api/products
![all](/images/all.png)


#### /api/productos/2
![all](/images/getId.png)

#### /api/products
![all](/images/create.png)




# Testing


## java 
- En Java con JUnit, los métodos de prueba tienen una anotación explícita (@Test), que el IDE detecta para mostrar el botón de ejecución. Por ejemplo:

```java

@Test
public void testMethod() {
    // Test logic
}

```



## kotlin

- En Kotest, las pruebas se declaran como bloques dentro de una clase de especificación (StringSpec, FunSpec, etc.), y no hay anotaciones explícitas en cada prueba


```kotlin

class GetProductUseCaseTest:StringSpec({


    val productOut = mockk<ProductOut>()  //mockk de salida
    val getProductUseCase = GetProductUseCase(productOut)


    //antes de iniciar
    // beforeEach{
    // clearAllMocks()
    // }


     "should get produdct By Id" {
        //GIVEN

        val product = Instancio.of(Product::class.java).create()

        every { product.id?.let { productOut.findProductById(it) } } returns product

        // WHEN
        val result = getProductUseCase.getProductById(product.id ?: 1)

        result shouldBe product

        verify { product.id?.let { productOut.findProductById(it) } }

    }





})

```

## Docker

Esta app se puede levantar con Docker Compose.
Incluye PostgreSQL para persistencia de datos.

```bash
docker-compose up --build -d 
```
##### 1 START CONTAINER
```bash
docker-compose up --build
```
##### 2 STOP CONTAINER 
```bash
docker-compose down
```

##### 3 RESTART CONTAINER
```bash
docker-compose down -v
```

## Volumen persistente 
```yaml
volumes:
  - db_data:/var/lib/postgresql/data

```
- Guarda los datos fuera del contenedor para que persistan aunque lo borres.
- db_data es el nombre del volumen definido abajo:

```yaml
volumes:
  db_data:

```
