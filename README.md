## Proyecto

Este proyecto está configurado para utilizar como IDE vscode con el paquete de extensiones de Java de Microsoft.

### Requisitos

- Java 17

### Configuración

Antes de poder ejecutarlo es necesario configurar el archivo de `.vscode/launch.json` con los parámetros del agent a correr. actualmente inicia con -gui, remover de ser necesario.


### Compilación y ejecución

Para compilar el proyecto, se utiliza el compiler de la extensión (Ctrl + Shift + B por default)

Y su ejecución con F5 (Esta tecla también compilar)

### Leyenda

- `src/`: Contiene el código fuente del proyecto
- `src/handson*/agents`: Contiene los agentes de los respectivos hands-on

### Datasets utilizados

(En caso de que se quiera utilizar el dataset de los hands-on - name en archivo: h3)
Startup - Multiple Linear Regression - https://www.kaggle.com/datasets/karthickveerakumar/startup-logistic-regression


## Agentes

#### hands-on4

- `src/handson4/agents/SLR.java`: Agente que implementa el algoritmo SLR
