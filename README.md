Techforb challenge https://techforb-f.netlify.app

Sistema de plantas y sensores desarrollado con Angular 17 para el frontend y Springboot + JWT para el backend.
(El programa puede tardar en ejecutar debido al time sleeping de Render)

Librerias para el frontend
- Angular Material

Dependencias para el backend
- Lombok
- JWT
- MySQL Driver

BACKEND:
Para este desafio se creó una API mediante springboot usando JWT (Json Web Token) tanto para la autenticacion 
como para el acceso a rutas protegidas al solicitar dicho token para el acceso a los distintos endpoints.

- Proteccion de rutas
![image](https://github.com/user-attachments/assets/c4a48806-cdc1-420f-be9d-266bef5c7c10)
![image](https://github.com/user-attachments/assets/4afa2a88-f08c-4891-b9a7-a9d1258bc425)
- Login
![image](https://github.com/user-attachments/assets/041fef97-2abe-4def-8b92-64e5df3d0543)
- Accesos con Bearer Token
![image](https://github.com/user-attachments/assets/3736c900-020f-4d7e-88bd-2e2e9821d131)
![image](https://github.com/user-attachments/assets/098b7c4b-1618-4518-9a15-ef229765b4c4)

Se implementó el manejo de errores personalizados a de nivel controlador y a nivel de filtro para garantizar la seguridad de los accesos.
Los tipos mas comunes de errores (BAD_GATEWAY, FORBIDDEN, UNAUTHORIZED) devuelven errores facilmente legibles para el usuario.

![image](https://github.com/user-attachments/assets/5d4dc373-e388-4c29-b289-abc3460daf4f)
![image](https://github.com/user-attachments/assets/f9d5068f-4ca1-4233-8c42-9a2100525b17)

Para las entidades se manejaron relaciones OneToMany y ManyToOne entendiendo que "una planta puede tener muchos sensores y muchos sensoren pueden pertenecer a una planta".
Se usaron anotaciones para indicar las diferentes restricciones al insertar datos en las creaciones de las entidad.
Con ese criterio se armaron las bases de datos teniendo en cuenta sus claves primarias y foraneas.

![image](https://github.com/user-attachments/assets/0c30f527-247b-46c9-84f6-7cac339431aa)
![image](https://github.com/user-attachments/assets/be997644-944a-4af5-aa97-437c85258241)


FRONTEND:
En el frontend se buscó seguir un orden entre componentes y paginas segun la Clean Architecture. Se tomó enfasis en la comunicación de componentes
tanto padres a hijos como hijos a padres mediante inputs y outputs.

El componente principal es la tabla contenida por el dashboard, en ella se conectan los demas componentes que muestran la cantidad de información de
todos los sensores a nivel general, tambien se pueden visualizar los sensores por planta, agregar planta y modificar sensores.

![image](https://github.com/user-attachments/assets/e5618d85-205a-4e47-a16f-055a38393ca3)
![image](https://github.com/user-attachments/assets/9a19827a-b94c-4cbf-967e-bc6fef914e67)
![image](https://github.com/user-attachments/assets/0f1a0ddf-3b55-4981-8fcf-fdb0c4ed6b83)
![image](https://github.com/user-attachments/assets/d42b080a-cd95-46f5-9d76-a86e5688ee94)

Para los servicios se usa un interceptor de token para incluirlos en las cabeceras de los endpoints protegidos, y para la renderización de las paginas
se utiliza la carga perezosa (Lazy Loading) para mejorar la performance de la aplicación. Para finalizar, se implementa el servicio de guardias (Guards)
para evitar el acceso desde la aplicación a sectores que no tengan el permiso sin una autenticación previa.


