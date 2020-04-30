# -GYM-EnForma

## TEMÁTICA


Página web de un gimnasio donde cualquier usuario puede entrar para tener información sobre el gimnasio. Además de realizar las siguientes funciones.


### Públicas


* **Alta online socio**: usuario puede darse de alta a través de un formulario.
* **Centros**: Datos de contacto y ubicación de cada gimnsio
* **Clases**: Listado de todas las clases, profesor que la imparte, tipo y nivel.
* **Noticias:** Tablón donde se mostrará toda la información relacionada con el gimansio.

### PRIVADA
* **Gestiones socio**: Modificar datos, eliminar de la base de datos y asignar un profesor.
* **Gestiones profesor**: Modificar datos, dar de alta un nuevo profesor, eliminarlo y asignar socios.
* **Gestiones clases**: Crear clases, eliminar clases, asignar profesor que la va a impartiry gestionar socios participantess.
* **Reservar clases colectivas**: Espacio para miembros del gimnasio donde prodan reservar las clases de la semana (socios).


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

***

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

***

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

## FASE 3
## Arquitectura

Aplicación montada en azure con la sigueinte estructura.

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/arquitectura.png)


* Balanceador de carga.
Basado en la técnología de haproxy con la siguiente configuracion 

haproxy.cfg
~~~
frontend gymPEF
        bind *:80
        bind *:443 ssl crt /etc/ssl/gym/gym.pem
        redirect scheme https if !{ ssl_fc }
        mode http
        default_backend gymPEF
        
backend gymPEF
        mode http
        stats enable
        stats uri /haproxy?stats
        balance roundrobin
        cookie SERVERID insert indirect nocache
        option httpclose
        option forwardfor
        server web1 10.0.0.6:8080  check cookie s1
        server web2 10.0.0.7:8080  check cookie s2
~~~

Tenemos dos servidores web a los que se redirigiran las peticiones a través de IP interna de la subred

* Servidor BBDD.
Configurado con MySql server, escuchando peticiones de los dos servidores web, configurado a traves de los usuarios y sus privilegios.

USER:
~~~ 
+-------+-------------------------------------------+-----------------------+----------+
| user  | authentication_string                     | plugin                | host     |
+-------+-------------------------------------------+-----------------------+----------+
| admin | *9F880DA1329B4B497F247AA25727CCDD5F4DD2E0 | mysql_native_password | 10.0.0.6 |
| admin | *9F880DA1329B4B497F247AA25727CCDD5F4DD2E0 | mysql_native_password | 10.0.0.7 |
+-------+-------------------------------------------+-----------------------+----------+
~~~

DATABASE:
~~~
+-----------------+
| Tables_in_gym   |
+-----------------+
| clase           |
| clase_socios    |
| noticia         |
| profesor        |
| profesor_clases |
| profesor_socios |
| socio           |
| socio_clases    |
| user            |
| user_roles      |
+-----------------+
~~~

* Servidores web
He creado una imagen de las máquinas virtuales con el objetivo de tener automatizado el proceso de levantamiento en caso de necesidad.

![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/snapshoot.png)

Creo un bash para ejecutar la instacion java con las opciones incluidas de spring.
~~~
#!/bin/bash
echo Desplegando instancia web.
cd /home/azureuser
java -jar gym.jar --spring.datasource.url="jdbc:mysql://10.0.0.5:3306/gym?autoReconnect=true&useSSL=false&serverTimezone = UTC" --spring.jpa.hibernate.ddl-auto=none --logging.file=gym_web01.log --loging.level.org.springframework=DEBUG

~~~
- Inserto la ip interna del servidor MySql 
- Uso la estategia none en la actualización de la base de datos
- Genero un fichero log en modo DEBUG, el cual extraeré con un script.

* Configuracion Ansible
Aunque no son muchas maquinas, he instalado un gestión de configuración (ansible) en el servidor que tiene alojado el balanceador

Inserto los host en el fichero de configuracion /etc/ansible/hosts
~~~
[web]
10.0.0.6
10.0.0.7
~~~~

Copia la clave privada y modifico los permisos para poder  conectarme por ssh al resto de maquinas.
> scp -i ~/.ssh/id_rsa /home/bothan/.ssh/id_rsa azureuser@balanceadorgym.eastus.cloudapp.azure.com:/home/azureuser/.ssh/

Realiza una comprobación de que todas las instancias web tiene la misma versión de java 
> ansible all -m shell -a "java -version" --private-key ~/.ssh/id_rsa
![Fotos en imagenes](https://github.com/bothan3/-GYM-EnForma/blob/master/imagenes/versionJava.png)



