# Take Home Assignment - Timothée Tailleur
## Prerequisites :

- OpenJDK 11
- Maven 3
- Docker

## How to build it :

Either import the project in your favorite IDE and run mvn clean install or (if you have maven in your PATH) simply run mvn clean install from the command line at the root of the repository

## How to run it :

At the root of the repository, run the following commands : 

`docker build -t takehomeassignment_tta . ` 

> This creates the docker image that you will use to create the containter 

`docker run -p 8080:8080 take_home_assignment_tta `

> This runs the container while binding port 8080 of your environment with port 8080 inside the docker container 

Access the homepage by going to http://localhost:8080/homepage.html 