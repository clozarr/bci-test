# API de prueba técnica  BCI
- Autor: Carlos Lozano
- Compañia: Globallogic

## Descripción de la API

### Descripción
Esta API proporciona servicios para gestionar usuarios.

### Endpoints


| Operation                  | Método | Path          |
|----------------------------|--------|---------------|
| Crear usuario              | POST   | /api/v1/users |
| Obtener usuario            | GET    | /api/v1/users/{id} |
| Actualizar usuario         | PATCH  | /api/v1/users/{id} |
| Obtener todos los usuarios | GET    | /api/v1/users |


### Swagger
Para acceder al swagger del microservicio, ejecute la aplicación e ingrese al siguiente enlace: http://localhost:8080/api/swagger-ui/index.html


## Como ejecutar la API

### 1. Crear la base de datos

El microservicio está configurado para trabajar con dos bases de datos: H2 y MySQL.
Para trabajar con la base H2 se debe ejecutar la aplicación usando el profile 'h2'.
Para trabajar con la base MySQL se debe ejecutar la aplicación usando el profile 'mysql'.
En la sección 4 se detalla como ejecutar cada perfil.

Para conectarse a la consola de H2 ejecute la aplicación con el profile activo 'H2' e ingrese a la siguiente URL: http://localhost:8080/api/h2-ui y utilice los siguientes parámetros:

- JDBC URL: jdbc:h2:mem:test
- Username: sa
- Password: (Sin Contraseña)


En el caso de MySQL debe instalar y/o configurar MySQL Server ademas asignarle un usuario y   contraseña; estos datos se utilizará en el profile activo 'mysql' para que la aplicación
se pueda conectar a la base de datos. Una vez finalizada la instalación y configuración debe crear una nueva base de datos llamada "test"

### 2. Crear las tablas
- Gracias a la implementación de Hibernate y JPA, el microservicio puede generar automáticamente las tablas a partir de las entidades definidas en el paquete `com.bci.test.demo.domain.entity`; esto ocurre al momento de ejecutar el proyecto. Además, existe la opción de crear manualmente las tablas utilizando el script `schema.sql`, ubicado en la carpeta `src/main/resources/db` .

### 3. Ejecutar script `data.sql`
- Opcionalmente, puede ejecutar el script `data.sql` ubicado en la carpeta `src/main/resources/db` para insertar datos de prueba.

### 4. Correr la aplicación

#### 4.1) Usando MySQL

- En una terminal bash ubicarse en la carpeta root del proyecto y ejecutar el siguiente comando

```bash
mvn spring-boot:run -DSPRING_PROFILES_ACTIVE=mysql
-DSPRING_DATASOURCE_USERNAME=tu_usuario 
-DSPRING_DATASOURCE_PASSWORD=tu_contraseña
```

- Alternativamente en el IDE de su preferencia puede crear una nueva nueva configuración de
  ejecución y agregar a las opciones de la VM los parámetros descritos

```bash
 -DSPRING_PROFILES_ACTIVE=mysql
-DSPRING_DATASOURCE_USERNAME=tu_usuario 
-DSPRING_DATASOURCE_PASSWORD=tu_contraseña
```
- El campo `tu_usuario` reemplazarlo por el usuario de su base de datos MySQL
- El campo `tu_contraseña` reemplazarlo por la contraseña de su base de datos MySQL


#### 4.2) Usando H2
- En una terminal bash ubicarse en la carpeta root del proyecto y ejecutar el siguiente comando

```bash
mvn spring-boot:run -DSPRING_PROFILES_ACTIVE=h2
-DSPRING_DATASOURCE_USERNAME=tu_usuario 
-DSPRING_DATASOURCE_PASSWORD=tu_contraseña
```

- Alternativamente en el IDE de su preferencia puede crear una nueva nueva configuración de
  ejecución y agregar a las opciones de la VM los parámetros descritos

```bash
 -DSPRING_PROFILES_ACTIVE=h2
-DSPRING_DATASOURCE_USERNAME=tu_usuario 
-DSPRING_DATASOURCE_PASSWORD=tu_contraseña
```
- El campo `tu_usuario` reemplazarlo por el usuario de su base de datos H2
- El campo `tu_contraseña` reemplazarlo por la contraseña de su base de datos H2

## Ejemplos

### 1. Crear usuario
- **Descripción**: Crea un nuevo usuario en la base de datos.
- **Endpoint**: `POST localhost:8080/api/v1/users`
- **Body Request de Ejemplo**:
  ```json
  {
    "name": "Erika Martinez",
    "email": "erika@martinez.org",
    "password": "A*hunter4",
    "phones": [
                {
                "number": "74528",
                "cityCode": "2",
                "countryCode": "58"
                }
        ]   
   }
  ```

- **Body Response de Ejemplo**:
  ```json
  {
    "uuid": "6feca574-56bc-4830-8ebc-cc0bd447940c",
    "name": "Erika Martinez",
    "email": "erika@martinez.org",
    "password": "A*hunter4",
    "phones": [
        {
            "number": "74528",
            "cityCode": "2",
            "countryCode": "58"
        }
    ],
    "created": "2024-07-01T02:40:12.273993",
    "modified": "2024-07-01T02:40:12.274241",
    "lastLogin": "2024-07-01T02:40:12.274245",
    "token": "service-dummy-token",
    "isActive": true
  }
  ```

### 2. Error correo inválido
- **Descripción**: Validación correo inválido.
- **Endpoint**: `POST localhost:8080/api/v1/users`
- **Body Request de Ejemplo**:
  ```json
  {
    "name": "Erika Martinez",
    "email": "erika.org",
    "password": "A*hunter4",
    "phones": [
                {
                "number": "74528",
                "cityCode": "2",
                "countryCode": "58"
                }
        ]   
   }
  ```
- **Body Response de Ejemplo**:


  ```json
  {
    "code": 400,
    "reason": "Bad Request",
    "messages": [
        "email: Invalid email"
    ],
    "date": "2024-07-01 02:45:06",
    "path": "/api/v1/users"
 }
  ```

### 3.  Error contraseña inválida
 - **Descripción**: Validación contraseña no cumple con los requisitos.
 - **Endpoint**: `POST localhost:8080/api/v1/users`
 - **Body Request de Ejemplo**:
 
  ```json
  {
    "name": "Erika Martinez",
    "email": "erika@martinez.org",
    "password": "1234",
    "phones": [
                {
                "number": "74528",
                "cityCode": "2",
                "countryCode": "58"
                }
        ]   
   }
  ```

- **Body Response de Ejemplo**:
```json
  
  {
    "code": 400,
    "reason": "Bad Request",
    "messages": [
        "password: Password must contain at least one lowercase letter",
        "password: The password must contain at least one special character",
        "password: Password must contain at least one uppercase letter",
        "password: password must contain at least 8 characters"
    ],
    "date": "2024-07-01 02:47:10",
    "path": "/api/v1/users"

 }
  ```
### 4.  Obtener usuario por ID
- **Descripción**: Obtener usuario por ID.
- **Endpoint**: `GET localhost:8080/api/v1/users/{id}`

- **Body Response de Ejemplo**:
```json
  
  {
    "uuid": "1e8fa628-d47d-487c-9efe-f9694df517d1",
    "name": "Dummy Martinez",
    "email": "dummy@martinez.org",
    "password": "Hunter34*",
    "phones": [
        {
            "number": "74528",
            "cityCode": "2",
            "countryCode": "58"
        }
    ],
    "created": "2024-07-01T11:13:16",
    "modified": "2024-07-01T11:13:16",
    "lastLogin": "2024-07-01T11:13:16",
    "token": "service-dummy-token",
    "isActive": true
}
```
### 5. Error Obtener usuario por ID, usuario no existe
- **Descripción**: Validación usuario no esxiste en  obtener usuario por ID.
- **Endpoint**: `GET localhost:8080/api/v1/users/{invalidId}`

- **Body Response de Ejemplo**:
```json

{
  "code": 404,
  "reason": "Not Found",
  "messages": [
    "User not found"
  ],
  "date": "2024-07-01 11:17:44",
  "path": "/api/v1/users/1e8fa628-d47d-487c-9efe-f9694df517d2"
}
```
### 6. Actualizar usuario
- **Descripción**: Actualizar usuario.
- **Endpoint**: `PATCH localhost:8080/api/v1/users/{userId}`
- **Body Request de Ejemplo**:

```json
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "Hunter34*",
  "phones": [
    {
      "number": "1234567",
      "cityCode": "1",
      "countryCode": "57"
    }
  ]
}
```
- **Body Response de Ejemplo**:
```json

{
  "uuid": "1e8fa628-d47d-487c-9efe-f9694df517d1",
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "Hunter34*",
  "phones": [
    {
      "number": "74528",
      "cityCode": "2",
      "countryCode": "58"
    }
  ],
  "created": "2024-07-01T11:13:16",
  "modified": "2024-07-01T11:23:13.083572",
  "lastLogin": "2024-07-01T11:23:13.083592",
  "token": "service-dummy-token",
  "isActive": true
}
```

### 7. Validación Actualizar usuario, Usuario No Existe
- **Descripción**: Validación error en actualizar usuario, usuario no existe.
- **Endpoint**: `PATCH localhost:8080/api/v1/users/{invalidId}`
- **Body Request de Ejemplo**:

```json
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "Hunter34*",
  "phones": [
    {
      "number": "1234567",
      "cityCode": "1",
      "countryCode": "57"
    }
  ]
}
```
- **Body Response de Ejemplo**:
```json
{
  "code": 404,
  "reason": "Not Found",
  "messages": [
    "User not found"
  ],
  "date": "2024-07-01 11:24:45",
  "path": "/api/v1/users/{invalidId}"
}

```

### 8. Obtener todos los usuarios
- **Descripción**: obtener todos los usuarios.
- **Endpoint**: `GET localhost:8080/api/v1/users`

- **Body Response de Ejemplo**:
```json
[
  {
    "uuid": "0858ef3c-ac80-49cd-8f89-aa3dd673d4ec",
    "name": "Juan Rodriguez 2",
    "email": "juan2@rodriguez.org",
    "password": "hunter2",
    "phones": [
      {
        "number": "1234567",
        "cityCode": "1",
        "countryCode": "57"
      }
    ],
    "created": "2024-06-30T21:16:24",
    "modified": "2024-07-01T02:16:08",
    "lastLogin": "2024-07-01T02:16:08",
    "token": "service-dummy-token",
    "isActive": true
  },
  {
    "uuid": "1e8fa628-d47d-487c-9efe-f9694df517d1",
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "Hunter34*",
    "phones": [
      {
        "number": "74528",
        "cityCode": "2",
        "countryCode": "58"
      }
    ],
    "created": "2024-07-01T11:13:16",
    "modified": "2024-07-01T11:23:13",
    "lastLogin": "2024-07-01T11:23:13",
    "token": "service-dummy-token",
    "isActive": true
  },
  {
    "uuid": "9f46b954-6aa5-4b87-ace8-f8008e3fd748",
    "name": "Carlos Lozano",
    "email": "carlos@lozano.org",
    "password": "hunter3",
    "phones": [
      {
        "number": "345677",
        "cityCode": "4",
        "countryCode": "57"
      }
    ],
    "created": "2024-07-01T03:25:40",
    "modified": "2024-07-01T03:25:40",
    "lastLogin": "2024-07-01T03:25:40",
    "token": "service-dummy-token",
    "isActive": true
  },
  {
    "uuid": "d837f0e4-b4cb-4e7e-9e5a-238c9a4d8eec",
    "name": "Erika Martinez",
    "email": "erika@martinez.org",
    "password": "hunter4",
    "phones": [
      {
        "number": "74528",
        "cityCode": "2",
        "countryCode": "58"
      }
    ],
    "created": "2024-07-01T03:26:58",
    "modified": "2024-07-01T03:26:58",
    "lastLogin": "2024-07-01T03:26:58",
    "token": "service-dummy-token",
    "isActive": true
  }
]

```