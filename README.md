# API-gestor-de-tareas

Diseño de una API REST para la gestión de tareas.

Los recursos académicos que he utilizado para llevar a cabo han sido los siguientes:
- Curso de Udemy centrado en fundamentos de Spring y buenas prácticas (sección 12: Spring Web API RESTful CRUD): https://www.udemy.com/course/spring-framework-5/
- Documentación oficial de Spring: https://spring.io/
- Resolución de errores y dudas: StackOverFlow y Chat-GPT. He buscado minimizar el uso de Chat-GPT a las dudas que me han ido surgiendo durante el desarrollo. 
- Experiencia previa en JAVA y otros lenguajes: Desarrollé APIs y aplicaciones en la universidad que complementan todos estos recursos.

Los recursos de software empleados son los siguientes: 
	- Visual Studio Code con extensiones para JAVA (version 1.109.2): para desarrollo local
	- Docker Desktop para Windows (versión Docker Personal - 4.60.1): gestión y motor de contenedores Docker
	- MySQL Workbench (8.0.30): gestión y motor de bases de datos local
	- Git y GitHub: Control de versiones y publicación de repositorios desarrollados.
	- Hecho con JAVA 17 utilizando el framework Spring (4.0.2) con las siguientes dependencias:
		- SpringWeb: Herramientas para el diseño de APIs: Spring MVC, Jackson parser, servidor Tomcat embebido, etc.
		- Spring Boot - Initializr : Configuración automática de proyectos JAVA desde cero siguiendo los prompts dentro del propio IDE
		- Spring Boot - Devtools: Para desarrollo local permite cargas al servidor en caliente, por lo que puede desplegarse la API 
		y ver cambios sobre la misma sin necesidad de reiniciar el proyecto (no incluido en la versión final)
		- Spring MySQL-Connector (8.0.30): Necesitamos un motor de base de datos para guardar las tareas y sus atributos asociados. Este conector funciona como enlace entre el
		motor MySQL y JAVA.
		- OpenApi Docs/Swagger UI (3.0.0): Para tener una interfaz personalizable y visualmente agradable a la hora de hacer consultas a la API.
		
Todo el código desarrollado, la imágen de Docker generada y el jar del proyecto JAVA estarán subidos a GitHub.
	
Para la exportación de la API se ha integrado todo en contenedores Docker para una mayor portabilidad y facilidad a la hora de desplegarlo.
El despliegue consta de un contenedor tareas_mysql que funciona como base de datos y un contenedor tareas_api que contiene la API. 

Para el despliegue de la API es necesario seguir los siguientes pasos:
1 - Descargar un motor de Docker (recomiendo la versión "Docker Personal" de Docker-Desktop: https://www.docker.com/pricing/)
2 - Descargar los siguientes archivos: .jar del proyecto, el Dockerfile y el docker-compose.yml
3 - Crear una carpeta que contenga los 3 archivos
4 - Desde una CLI situarnos sobre la carpeta que contiene los archivos y ejecutar el comando "docker compose up".
5 - Una vez levantados los contenedores, acceder a la API desde un navegador introduciendo el siguiente path: "http://localhost:8080/swagger-ui/index.html"
6 - Ya puedes testear la API creando, modificando, listando y borrando tareas.

Trabajo futuro y posibles mejoras:
1 - Mejorar los features existentes y añadir tests unitarios más amplios.  
2 - Desarrollo de una app que consuma la API y que tenga un cliente web usando HTML, CSS y algun framework de JavaScript como React o Vue.
3 - Crear nuevas funcionalidades para la aplicación: añadir fechas, tiempos límite para tareas, nuevos estados para las tareas(CANCELADO/ATRASADO), 
un sistema de notificaciones/alertas...
4 - Subirlo a un entorno cloud como AWS o Azure para mayor accesibilidad. 

