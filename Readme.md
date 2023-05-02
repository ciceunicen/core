## Configuracion de proyecto
### Disponer version de Java y JDK 17 o mayor
- Descargar proyecto del repositorio
- abrir archivo en IDE con framework Spring
- Levantar base de datos MySQL local
- inicialización del proyecto

### Configurar carga de tablas y datos

En esta etapa de desarrollo todavia estamos usando datos de prueba. El archivo *"loadDatabase.java"* en la carpeta utils  define la carga de los datos en las tablas.  
La propiedad `app.dbload.loaddbonrun` en *"application.properties"* es un booleano que determina si se cargan los datos o no (true para que se carguen, false para que no).  
Si la propiedad no se encuentra en el archivo se asume false. Por defecto Spring va a agregar nuevas columnas que se  definan, pero no puede borrar columnas que se hayan removido de los archivos.  
Para hacer que Spring borre y rehaga las tablas sin las columnas removidas se tiene que modificar la propiedad `spring.jpa.hibernate.ddl-auto=update` a `spring.jpa.hibernate.ddl-auto=create-drop`