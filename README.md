# Operacion Fuego Quasar
Servicio que permite triangular la posicion de una nave tomando como referencia las coordenadas de 3 satelites y la distancia que hay entre la nave y cada uno de los satelites, recibiendo un mensaje en cada uno de ellos, de esta manera poder conocer la posicion de la nave y el mensaje enviado.

# Desarrollo

Para la construccion del servicio se utilizó lo siguiente:
    
    - Lenguaje -> Java
    - Framework -> Springboot
    - IDE -> Springtools
    - Version JDK -> 11
    - Gestion de dependencias -> Maven
    - Cloud -> Google Cloud Plataform
    - Motor de BD en memoria -> Redis
    - Librerias -> Redis
                   Trilateration
                   Junit
                   Mockito
                   

# Servicios

El servicio se encentra hospedado en GCP y posee los siguientes endpoints de cada uno de sus servicios:

### **POST**  [https://ttl-18h-xufmqzwvgq-uc.a.run.app/topsecret/]

### *Descripción*

Recibe en formato JSON la distancia en un arreglo el mensaje, el nombre del satelite y la distancia a la que se encuentra la nave, para 3 satelites previamente posicionados.

### *Ejemplo del request*

           {
              "satellites": [
                  {
                      "name": "kenobii",
                      "distance": 110.0,
                      "message": ["este", "", "", "mensaje", ""]
                  },
                  {
                      "name": "skywalker",
                      "distance": 115.5,
                      "message": ["", "es", "", "", "secreto"]
                  },
                  {
                      "name": "sato",
                      "distance": 142.7,
                      "message": ["este", "", "un", "", ""]
                  }
              ]
          }


### *Ejemplo del response*

:two::zero::zero:	



          {
              "position": {
                  "x": -42.95642775426958,
                  "y": -64.39708806171568
              },
              "message": "este es un mensaje secreto "
          }

### **POST**  [https://ttl-18h-xufmqzwvgq-uc.a.run.app/topsecret_split/{NombreSatelite}]

### *Descripción*

Recibe por parametro el nombre del satelite de manera individual y en formato JSON la distancia y el mensaje para dicho satelite

### *Ejemplo del request*

           {
              "distance": 110.0,
              "message": ["este", "", "", "mensaje", ""]
           }
           

### *Ejemplo del response*

:two::zero::zero:	



### **GET**  [https://ttl-18h-xufmqzwvgq-uc.a.run.app/topsecret_split]

### *Descripción*

Devuelve la posicion y mensaje de la nave, de acuerdo a las distancias y mensajes enviadas previamente de manera individual

### *Ejemplo del response*

:two::zero::zero:	


          {
              "position": {
                  "x": -42.95642775426958,
                  "y": -64.39708806171568
              },
              "message": "este es un mensaje secreto "
          }
          
# Pruebas unitarias

Se realizaron pruebas unitarias para las clases controller y service, para esto se uso Junit, Mockito y Jacoco(para coverage)

![Alt text](https://github.com/MichaelAnyelo/OperacionFuegoQuasar/blob/main/img/UnitTest.png "Test Unitarios")


