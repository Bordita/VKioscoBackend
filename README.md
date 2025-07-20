# VKiosco Backend

## Descripción

Backend para una plataforma de kiosco virtual desarrollado con Spring Boot. Esta aplicación permite gestionar productos según su tipo (bebidas y comidas) y facilita que los clientes realicen órdenes de compra de manera eficiente.

## Características Principales

### Gestión de Productos
- **Registro de productos por tipo**: Soporte para bebidas y comidas con campos específicos
  - **Bebidas**: Incluyen volumen (ml/l)
  - **Comidas**: Incluyen fecha de vencimiento
- **Operaciones CRUD completas**: Crear, leer, actualizar y eliminar productos
- **Validación de nombres únicos**: Previene la duplicación de productos
- **Filtrado por tipo**: Búsqueda y listado de productos según categoría
- **Gestión de stock**: Control de inventario en tiempo real

### Sistema de Órdenes
- **Creación de órdenes**: Los clientes pueden crear órdenes con múltiples productos
- **Validación de stock**: Verificación automática de disponibilidad antes de procesar órdenes
- **Cálculo automático**: Subtotales y totales calculados automáticamente
- **Gestión de líneas de orden**: Cada producto en la orden se maneja individualmente


## Tecnologías Utilizadas

- **Spring Boot 3.x**: Framework principal de Java
- **Spring Data JPA**: Para persistencia de datos
- **Hibernate**: ORM para mapeo objeto-relacional
- **MySQL**: Base de datos relacional
- **Bean Validation**: Validación de datos con anotaciones
- **Maven**: Gestión de dependencias y construcción del proyecto

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/techlab/backend/
│   │   ├── controller/          # Controladores REST
│   │   ├── service/             # Lógica de negocio
│   │   ├── repository/          # Acceso a datos
│   │   ├── model/               # Entidades del dominio
│   │   ├── dto/                 # Objetos de transferencia de datos
│   │   ├── exception/           # Manejo de excepciones
│   │   └── config/              # Configuraciones
│   └── resources/
│       ├── application.properties
│       └── data.sql             # Datos de prueba
└── test/                        # Pruebas unitarias
```

## Modelos de Datos

### Producto (Jerarquía)
- **Product** (clase base): ID, nombre, precio, stock, tipo, descripción, imagen
- **Beverage** (bebida): Extiende Product, incluye volumen
- **Food** (comida): Extiende Product, incluye fecha de vencimiento

### Orden
- **Order**: ID, líneas de orden, fecha de creación
- **OrderLine**: ID, producto, cantidad, subtotal

## API Endpoints

### Productos
- `GET /api/products` - Listar todos los productos
- `GET /api/products?type={tipo}` - Filtrar productos por tipo
- `GET /api/products/{id}` - Obtener producto por ID
- `POST /api/products` - Crear nuevo producto
- `PUT /api/products/{id}` - Actualizar producto completo
- `PATCH /api/products/{id}` - Actualizar producto parcialmente
- `DELETE /api/products/{id}` - Eliminar producto

### Órdenes
- `GET /api/orders` - Listar todas las órdenes
- `GET /api/orders/{id}` - Obtener orden por ID
- `POST /api/orders` - Crear nueva orden
- `DELETE /api/orders/{id}` - Eliminar orden

## Instalación y Ejecución

### Prerrequisitos
- Java 21 o superior
- Maven 3.6 o superior
- MySQL Server 8.0 o superior

### Pasos para ejecutar

1. **Configurar MySQL**
   ```sql
   -- Crear la base de datos
   CREATE DATABASE techlabdb;
   
   -- Crear usuario (opcional)
   CREATE USER 'kiosco'@'localhost' IDENTIFIED BY 'kiosco123';
   GRANT ALL PRIVILEGES ON techlabdb.* TO 'kiosco'@'localhost';
   FLUSH PRIVILEGES;
   ```

2. **Clonar el repositorio**
   ```bash
   git clone https://github.com/Bordita/VKioscoBackend.git
   cd VKioscoBackend
   ```

3. **Compilar el proyecto**
   ```bash
   mvn clean compile
   ```

4. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

La aplicación estará disponible en `http://localhost:8080`

## Configuración

### Base de Datos
El proyecto utiliza MySQL como base de datos principal. La configuración se encuentra en `application.properties`:

```properties
# Configuración de la base de datos MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/techlabdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

#### Prerrequisitos de Base de Datos
- MySQL Server 8.0 o superior
- Base de datos `techlabdb` creada
- Usuario con permisos de lectura/escritura

### Datos de Prueba
El archivo `data.sql` contiene datos de ejemplo que se cargan automáticamente al iniciar la aplicación.

## Validaciones y Mensajes de Error

Todas las validaciones y mensajes de error están en español para una mejor experiencia del usuario:

- Validación de campos requeridos
- Validación de tipos de datos
- Validación de reglas de negocio
- Mensajes de error descriptivos



## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.


**VKiosco Backend** - Plataforma de kiosco virtual desarrollada con Spring Boot