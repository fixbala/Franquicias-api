***API de Gestión de Franquicias***
**Versión**: 1.0.0
**Autor**: Santiago Martinez Ayala
**Fecha**: 27/06/2025


***Descripción***
API desarrollada en Spring Boot (Java) con enfoque reactivo para gestionar franquicias, sucursales y productos, cumpliendo con todos los requisitos técnicos solicitados.

***Criterios de Aceptación Cumplidos***

Requisito	                     Estado	             Detalle
1. Spring Boot + Java	          ✔️	        Proyecto generado con spring.io
2. Add Franquicia	              ✔️	         POST /api/franquicias
3. Add Sucursal	                  ✔️	         POST /api/franquicias/{id}/sucursales
4. Add Producto	                  ✔️	         POST /api/franquicias/{id}/sucursales/{nombre}/productos
5. Delete Producto	              ✔️	         DELETE /api/franquicias/{id}/sucursales/{nombre}/productos/{producto}
6. Update Stock	                  ✔️	        PUT /api/franquicias/{id}/sucursales/{nombre}/productos/{producto}/stock
7. Producto con más stock	      ✔️	        GET /api/franquicias/{id}/productos/max-stock
8. Persistencia (MongoDB)	      ✔️	         Docker + MongoDB Atlas (nube)
9. Docker	                      ✔️	         Imagen optimizada y docker-compose.yml
10. Programación Reactiva	      ✔️	         Uso de Flux/Mono
11. Update nombre franquicia	  ✔️	         PUT /api/franquicias/{id}/nombre
12. Update nombre sucursal	      ✔️	         PUT /api/franquicias/{id}/sucursales/{nombre}/nombre
13. Update nombre producto	      ✔️	        PUT /api/franquicias/{id}/sucursales/{nombre}/productos/{producto}/nombre
14. Infraestructura como Código	  ✔️	         Terraform (AWS) / Docker Compose (local)
15. Despliegue en Nube	          ⚠️	         Configurado (requiere permisos AWS)

***Requisitos Previos***
!Docker + Docker Compose (Instalar)
!Java 17 (JDK)
!Maven (opcional, para builds manuales)

***Instalación y Ejecución***
1. Clonar el Repositorio

'git clone' 
'cd franquicias-api'


2. Iniciar Servicios con Docker

'docker-compose up -d --build'

3. Verificar Contenedores

'docker-compose ps'

Salida esperada:
franquicias-api   Running
mongodb           Running (healthy)


***Documentación de Endpoints***

**Método	        **Endpoint	                    **Descripción**	 
POST	        /api/franquicias	                Crear franquicia	
PUT	            /{id}/nombre	                    Actualizar nombre franquicia	
GET	            /{id}/productos/max-stock      	    Productos con más stock


***Despliegue en la Nube***

1. MongoDB Atlas: Reemplazar URI en docker-compose.yml:

environment:
  SPRING_DATA_MONGODB_URI: "mongodb+srv://user:pass@cluster.mongodb.net/franquicias?retryWrites=true&w=majority"

2. AWS ECS: Ejecutar Terraform (requiere permisos): 

terraform init
terraform apply


***Notas Adicionales***
Persistencia: Los datos se guardan en un volumen de Docker (mongodb_data).

Pruebas: Todos los endpoints fueron validados con curl y Postman.

Logs: Ver con docker-compose logs franquicias-api.

 ***Contacto***
 Correo: ipfixbala@gmail.com
 Numero(+57): 3043990600

 ***¡Proyecto listo para producción!***
