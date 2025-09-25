# fake store api 

https://fakestoreapi.com/docs


```kotlin

fetch('https://fakestoreapi.com/products')
            .then(res=>res.json())
            .then(json=>console.log(json))
```


### output

```kotlin

            //output
            [
                {
                    id:1,
                    title:'...',
                    price:'...',
                    category:'...',
                    description:'...',
                    image:'...'
                },
                /*...*/
                {
                    id:30,
                    title:'...',
                    price:'...',
                    category:'...',
                    description:'...',
                    image:'...'
                }
            ]
        
```


Nota: la idea es consumir la api y traer todos los  prducts desde la api de `fakestoreapi`