## Configuración de proyecto
### Disponer de las herramientas necesarias
- [Git](https://git-scm.com) para clonar repositorios, traer cambios del repositorio y subir cambios
- IDE para modificar/visualizar el código y correr el servidor. Algunas opciones son:
   - [IntelliJ](https://www.jetbrains.com/idea/)
   - [VSCode](https://code.visualstudio.com)
   - [Eclipse](https://www.eclipse.org)
   - [Spring Tools 4 for Eclipse](https://spring.io/tools)
- [JDK 17 o mayor](https://www.oracle.com/java/technologies/downloads/#java17) para programar en Java.
- [XAMPP](https://www.apachefriends.org) para levantar un servidor local e interactuar con la base de datos (MySQL).

---

### Configurar IDE para usar Spring Boot
El proyecto está configurado para facilitar la integración con diferentes entornos de desarrollo. Dependiendo del IDE que elijas, la configuración puede variar.

#### Para IntelliJ IDEA, Eclipse o Spring Tool Suite:
IntelliJ IDEA, Eclipse y Spring Tool Suite están diseñados para trabajar con proyectos Spring Boot de manera nativa, lo que facilita la importación y configuración del proyecto. Estos IDEs tienen soporte integrado para Spring Boot y, por lo tanto, se encargan automáticamente de la configuración del proyecto y la resolución de dependencias al importar proyectos Maven.

- Abre tu IDE preferido: IntelliJ IDEA, Eclipse o Spring Tool Suite.
- Selecciona `File` -> `Open` o `Import`.
- Navega hasta el directorio donde has clonado el repositorio y selecciona el proyecto.
- El IDE reconocerá automáticamente el archivo `pom.xml` y descargará las dependencias necesarias para Spring Boot.

#### Para Visual Studio Code (VSCode):
- Abre Visual Studio Code.
- Instala las extensiones de Spring Boot y Java desde la sección de extensiones. Es recomendable reiniciar VSCode luego de terminadas las instalaciones.
- Abre VSCode y selecciona `File` -> `Open Folder`.
- Navega hasta el directorio donde has clonado el repositorio y selecciona el proyecto.

**Nota:** La importación del proyecto y la instalación de dependencias se realizarán automáticamente gracias al archivo `pom.xml`, el cual contiene las dependencias necesarias para compilar y ejecutar.

---

### Primeros pasos
#### Git:
- Clonar el repositorio `git clone https://github.com/ciceunicen/core.git`.
- (Opcional) Ver todas las ramas del proyecto (locales y remotas) `git branch -a`.
- Cambiar a la rama develop (rama con la última versión del proyecto) `git checkout develop`.

#### IDE:
- Abrir la carpeta del proyecto desde el IDE.
- Configurar la carga de tablas y datos de prueba:
   - Abrir el archivo `application.properties` localizado en `src/main/resources`.
   - Cambiar el valor de la propiedad `app.dbload.loaddbonrun` de `false` a `true` para permitir que se carguen los datos cuando se levanta el servidor. Si la propiedad no se encuentra en el archivo se asume `false`.
   - Cambiar el valor de la propiedad `spring.jpa.hibernate.ddl-auto` a `create` para que al ejecutar la aplicación se eliminen las tablas y datos de las mismas para que luego se arme el esquema nuevamente y cargue los datos. Así nos aseguramos de que estamos trabajando con los datos de prueba por defecto implementados en el archivo `loadDatabase.java` localizado en `src/main/java/com/project/Utils`.
- Ejecutar la aplicación desde la clase `SistemaDeGestionDeEmprendedoresApplication.java` ubicada en `src/main/java/com/project`

**Nota:** Si se quieren mantener los cambios realizados en la base de datos durante pruebas o debugging, se debe cambiar el valor de `spring.jpa.hibernate.ddl-auto` a `update` para que sólo se actualice el esquema si hubo algún cambio en las entidades. También es recomendable desactivar la propiedad `app.dbload.loaddbonrun` seteandolo como `false` nuevamente si es que no se construye el esquema cada vez que se ejecuta la aplicación y se tienen datos de prueba cargados.

---

### Configurar mail de donde sera enviado para recuperar la contraseña 
En el archivo `application.properties`, ubicado en `src/main/resources`, se encuentra la configuración del correo electrónico para el servicio de recuperación de contraseña. Sigue estos pasos para configurar el correo desde donde se enviarán los mensajes:

#### Generar app password
Para utilizar los servicios de Google SMTP, necesitarás crear una “app password” para la cuenta de Google que recibirá el mail de cambio de contraseña. Los pasos son los siguientes:
- Loguearse en la cuenta de Google
- Dirigirse a la gestión de cuenta haciendo click en el avatar o foto de perfil en la esquina superior derecha y seleccionar **Gestionar tu cuenta de Google**
- En el panel izquierdo, buscar y seleccionar **Seguridad**
- Dirigirse a la sección llamada **Cómo inicias sesión en Google**
- Seleccionar **Verificación en dos pasos**, elegir la opción de mensaje de texto e ingresar un número de teléfono
- Ingresar el código recibido vía mensaje de texto y activas la verificación en dos pasos
- Una vez hecho esto, al final de la sección de verificación en dos pasos se da la posibilidad de crear una **App password**
- Ingresar un nombre para la app password (puede ser cualquiera, ej: "mi app"), generarla y guardarla para usarla en los pasos siguientes

#### Setear propiedades
- En la propiedad `spring.mail.username`, colocar la dirección de correo electrónico que actuará como remitente para la recuperación de contraseña. Debe ser la cuenta con la que se generó la app password.

    ```properties
    spring.mail.username=tucorreo@alumnos.exa.unicen.edu.ar
    ```

- Usa la "app password" generada en la propiedad `spring.mail.password`.

    ```properties
    spring.mail.password=tuAppPasswordGenerada
    ```
    
**Nota**: Tener en cuenta que el valor de `spring.mail.password` no debe subirse al repositorio remoto ya que por supuesto se trata de un dato sensible.