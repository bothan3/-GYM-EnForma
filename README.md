# -GYM-EnForma

## TEMÁTICA


Página web de un gimnasio donde cualquier usuario puede entrar para tener información sobre el gimnasio. Además de realizar las siguientes funciones.


### Públicas


* **Alta online socio**: usuario puede darse de alta a través de un formulario.
* **Centros**: Datos de contacto y ubicación de cada gimnsio
* **Clases**: Listado de todas las clases, profesor que la imparte, tipo y nivel.
* **Actualidad:** Tablón donde se mostrará toda la información relacionada con el gimansio.
* **Contacta:** Zona donde se podra dejar un mensaje para ser atendido por el gimnasio.



### PRIVADA
* **Gestiones socio**: Modificar datos, eliminar de la base de datos y asignar un profesor.
* **Gestiones profesor**: Modificar datos, dar de alta un nuevo profesor, eliminarlo y asignar socios.
* **Gestiones clases**: Crear clases, eliminar clases, asignar profesor que la va a impartiry gestionar socios participantess.
* **Reservar clases colectivas**: Espacio para miembros del gimnasio donde prodan reservar las clases de la semana.


***

### ENTIDADES PRINCIPALES

* **Socio:** Entidad encargada de todas los datos y operaciones de los socios del gimnasio.
* **Profesor:** Entidad encargada de todas los datos y operaciones de los profesores del gimnasio.
* **Clase:** Entidad encargada de todas los datos y operaciones de las clases del gimnasio.
* **Noticia:** Entidad encargada de todas los datos y operaciones de las noticias del gimnasio.
* **Comentario:** Entidad encargada de la gestión los comentarios de las noticias de los socios.



***

## INTEGRANTES 

* **Nombre:** Iván Murillo Expósito
* **Correo:**<i.murillo@alumnos.urjc.es>
* **GitHub:** <https://github.com/bothan3/-GYM-EnForma.git>

***

## DIAGRAMA DE NAVEGACIÓN

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/mapaNavegacion.jpg)

***

## DIAGRAMAS MODELO-DATOS

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/modeDatos.png)

## CAPTURAS DE PANTALLA APLICACIÓN
### Portada:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/portada.png)

Página principal de la aplicación web, donde se muestran las últimas noticias, información y el acceso para loguearte en caso de ser administrador.

### Login:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/login.png)

Login web, que aparece cuando pulsamos la opción de logearte o cuando se intenta acceder a web privada.

### Tablón de noticias:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/tablonNoticias.png)

Página donde se muestra todas las noticias de manera paginada.

### Intranet (zona administración):

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/intranetAdmin.png)

Menú intranet, donde se muestra todas las opciones para administrar el gimnasio. Separado por las instancias de socio, profesores y clases.

### Espacio socio:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/espacioSocio.png)

Espacio socio, donde puedo buscar a los socios según distintos filtros.

### Operaciones socio:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/listadoSocio.png)

Muestra el listado de socios, según el filtro seleccionado y nos permite hacer las distintas operaciones (modificar, eliminar, apuntar clase y desapuntar clase).

### Alta socio:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/altaSocio.png)

Formulario donde poder dar de alta un nuevo socio.

### Espacio profesor:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/espacioProfesor.png)

Espacio profesor, donde puedo buscar a los profesores según distintos filtros.

### Operaciones profesor:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/listadoProfesor.png)

Muestra el listado de profesores, según el filtro seleccionado y nos permite hacer las distintas operaciones (modificar, eliminar, asignar socio y eliminar un socio asignado).

### Alta profesor:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/altaProfesor.png)

Formulario donde poder dar de alta un nuevo profesor.

### Espacio clase:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/espacioClase.png)

Espacio clase, donde puedo buscar las distintas clases según distintos filtros.

### Operaciones clase:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/listadoClase.png)

Muestra el listado de las clases, según el filtro seleccionado y nos permite hacer las distintas operaciones (modificar, eliminar, asignar profesor y eliminar profesor asignado).

### Crear clase:

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/crearClase.png)

Formulario donde poder crear una nueva clase.

## FASE 2 - Instrucciones de despliegue

### Requisitos:
* Java JRE version 8 o superior,
* MySql server.

### Configuración mysql
* database name: gym
* user: admin@localthost
* password: administrator

## Ejecución en local
* ejecutar el jar con el comando.
>java -jar gymPonteEnForma-0.0.1-SNAPSHOT.jar

