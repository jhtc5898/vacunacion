# Vacunacion


## Installation
Realizar un git clone del repositorio actual con la rama MAIN en la ruta de su preferencia


```sh
git clone https://github.com/jhtc5898/vacunacion.git
```
Abri el proyecto en su IDE de preferencia (Desarrollado en IntelliJ IDEA)

Levantar la base de datos existe 2 opciones
## Primera Opcion

Levantar la base de datos en un contenedor:
En la ubicacion donde esta el fichero encontrara el documento 
```sh
postgres.yml
```
Levante el contenedor con el siguiente comando:
```sh
docker-compose -f postgres.yml up
```
El contenedor esta configurado para que inicie ejecutando el init.sql que esta en la carpeta [sql]  de esta manera ya tendra generado los roles y empleados de prueba.

POR LO CUAL SOLO DEBE LEVANTAR EL PROYECTO Y UTILIZAR CON NORMALIDAD

## Segunda Opcion
Generar una base de datos con las siguientes caracteristicas 
```sh
      POSTGRES_DB: krugger
      POSTGRES_USER: usuario
      POSTGRES_PASSWORD: password
```
a continuacion:

Mandamos a correr el script init.sql que se encuentra en la carpeta [sql] 
Ahora ya podemos iniciar el proyecto:
LEVANTAR EL PROYECTO Y UTILIZAR CON NORMALIDAD



![Diagrama BD](https://github.com/jhtc5898/vacunacion/blob/main/krugger%20-%20vacunacion.png)

## Documentacion Publicada Postman
* POSTAMN TEST DESACTIVADO JWT
https://documenter.getpostman.com/view/13910567/VUxLvTzG

* POSTAMN TEST CON JWT 

## Recomendaciones
En el caso de tener alguna error con las dependencias ejecutar:
```sh
mvn clean install
```

En el caso que no se levante el contenedor Docker puede que tenga levantado otro PostGres en su maquina por lo cual seria recomendable cambiar el puerto en el archivo [postgres.ymlv]
Actual
```sh
version: '3.3'

volumes:
  postgres_data:
    driver: local

services:
  postgres:
    image: postgres
    volumes:
      - postgres_data:/var/lib/postgresql/data
      - ./sql/init.sql:/docker-entrypoint-initdb.d/init.sql
    environment:
      POSTGRES_DB: krugger
      POSTGRES_USER: usuario
      POSTGRES_PASSWORD: password
    ports:
      - 5432:5432
```
## Cambio de puerto Contenedor 
```sh
version: '3.3'

volumes:
  postgres_data:
    driver: local

services:
  postgres:
    image: postgres
    volumes:
      - postgres_data:/var/lib/postgresql/data
      - ./sql/init.sql:/docker-entrypoint-initdb.d/init.sql
    environment:
      POSTGRES_DB: krugger
      POSTGRES_USER: usuario
      POSTGRES_PASSWORD: password
    ports:
      - 5433:5432
```



## Plugins

Dillinger is currently extended with the following plugins.
Instructions on how to use them in your own application are linked below.

| Plugin | README |
| ------ | ------ |
| Dropbox | [plugins/dropbox/README.md][PlDb] |
| GitHub | [plugins/github/README.md][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |
| OneDrive | [plugins/onedrive/README.md][PlOd] |
| Medium | [plugins/medium/README.md][PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md][PlGa] |

## Development

Want to contribute? Great!

Dillinger uses Gulp + Webpack for fast developing.
Make a change in your file and instantaneously see your updates!

Open your favorite Terminal and run these commands.

First Tab:

```sh
node app
```

Second Tab:

```sh
gulp watch
```

(optional) Third:

```sh
karma test
```

#### Building for source

For production release:

```sh
gulp build --prod
```

Generating pre-built zip archives for distribution:

```sh
gulp build dist --prod
```

## Docker

Dillinger is very easy to install and deploy in a Docker container.

By default, the Docker will expose port 8080, so change this within the
Dockerfile if necessary. When ready, simply use the Dockerfile to
build the image.

```sh
cd dillinger
docker build -t <youruser>/dillinger:${package.json.version} .
```

This will create the dillinger image and pull in the necessary dependencies.
Be sure to swap out `${package.json.version}` with the actual
version of Dillinger.

Once done, run the Docker image and map the port to whatever you wish on
your host. In this example, we simply map port 8000 of the host to
port 8080 of the Docker (or whatever port was exposed in the Dockerfile):

```sh
docker run -d -p 8000:8080 --restart=always --cap-add=SYS_ADMIN --name=dillinger <youruser>/dillinger:${package.json.version}
```

> Note: `--capt-add=SYS-ADMIN` is required for PDF rendering.

Verify the deployment by navigating to your server address in
your preferred browser.

```sh
127.0.0.1:8000
```

## License

MIT

**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
