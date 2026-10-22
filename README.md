# Usuarios con JDBC (usuarioLogueo)

Proyecto personal de práctica de **JDBC** con **MySQL**. Es una aplicación de consola que muestra un menú para cargar usuarios en una base de datos y consultarlos, como primer paso hacia un sistema de logueo.

## Tecnologías

- Java (proyecto de IntelliJ IDEA, sin Maven ni Gradle)
- JDBC (`java.sql`)
- MySQL

## Estructura del proyecto

```
usuarioLogueo/
├── usuarioLogueo.iml
└── src/
    ├── Main.java        # Punto de entrada: crea el menú y lo ejecuta
    ├── MenuBD.java      # Menú de consola (Scanner) con las opciones
    ├── ConectorDB.java  # Conexión, consultas e inserción vía JDBC
    ├── Coneccion.java   # Enum con URL, usuario y contraseña de la base
    └── Usuario.java     # Modelo: id, nombre y contraseña
```

## Funcionalidades

El menú (`MenuBD`) ofrece tres opciones:

1. **Cargar Usuario**: pide nombre y contraseña por consola y lo inserta en la tabla `usuario`.
2. **Obtener Usuario**: pide nombre y contraseña y ejecuta un `SELECT` filtrando por ambos (base del login).
3. **Obtener Usuarios**: lista todos los registros de la tabla `usuario`.

## Conceptos aplicados

- Conexión a una base de datos con `DriverManager.getConnection`.
- Ejecución de consultas con `Statement` (`executeQuery` / `executeUpdate`) y lectura de resultados con `ResultSet`.
- Uso de un `enum` con atributos para centralizar los datos de conexión.
- Modelo con `equals`, `hashCode` y `toString` sobrescritos.
- Menú interactivo con `Scanner`, `switch` y bloques de texto (text blocks) de Java.
- Manejo de `SQLException`.

## Cómo ejecutarlo

Requisitos:

- JDK 15 o superior (se usan text blocks `"""`).
- Servidor MySQL local con una base `login_usuario` y una tabla `usuario` con las columnas `id`, `username` y `password`.
- El driver **MySQL Connector/J** agregado como librería del módulo (no está incluido en el repositorio).

Pasos:

1. Ajustar los datos de conexión (URL, usuario y contraseña) en `src/Coneccion.java`.
2. Abrir el proyecto en IntelliJ IDEA, agregar el conector de MySQL como dependencia y ejecutar `Main`.

Desde la terminal (con el `.jar` del conector descargado):

```bash
javac -d out -cp mysql-connector-j.jar src/*.java
java -cp "out:mysql-connector-j.jar" Main      # en Windows usar ";" en lugar de ":"
```

## Estado

Proyecto en desarrollo / práctica. Algunos puntos pendientes que se ven en el código:

- `ConectorDB` usa `Coneccion.X.toString()` (que devuelve el nombre de la constante) en lugar de `getDescription()` para armar la conexión.
- Las consultas se arman concatenando strings; lo recomendable es pasar a `PreparedStatement` para evitar inyección SQL.
- La opción 2 consulta la tabla `usuarios` con columnas `nombre`/`contraseña`, mientras que el resto usa `usuario` con `username`/`password`.
