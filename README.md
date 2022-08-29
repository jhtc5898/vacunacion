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

## ROLES PREDEFINIDOS

| user             | pwd                                       |
|------------------|-------------------------------------------|
| ADMIN          | 123456789        |
| empleado1           | 987654321         |
| empleado2    | 987654321    |
| empleado3         | 987654321       |
| ADMIN1           | 123456789          |



![Diagrama BD](https://github.com/jhtc5898/vacunacion/blob/main/krugger%20-%20vacunacion.png)

## Documentacion Publicada Postman
* POSTAMN TEST DESACTIVADO JWT
https://documenter.getpostman.com/view/13910567/VUxLvTzG

* POSTAMN TEST CON JWT
https://documenter.getpostman.com/view/13910567/VUxLvnnU

## JWT
En el caso de querer desactivar el JWT para consultar directamente comentar

Las lineas de la clase SECURITYCONFIG (Esta confoguracion permite el consumo directo sin JWT)
```sh
 @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

        http.csrf().disable().authorizeRequests()
                //.antMatchers(HttpMethod.POST, "/admin/*").hasRole("ADMIN")
                //.antMatchers(HttpMethod.POST, "/admin/addEmployee").hasRole("ADMIN")
                //.antMatchers("/cliente/*").hasAnyRole("ADMIN", "OPERATOR")
                //.antMatchers(HttpMethod.PUT, "/curso").authenticated()
                //.antMatchers("/listarP").authenticated()
                .antMatchers("/listarTv/*").authenticated()
                .and().apply(MyCustomDsl.customDsl()); // forma de solicitar los credenciales
        return http.build();
    }
```

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
