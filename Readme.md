[School Management System (1).pdf](https://github.com/dhanushka365/SchoolEase-backend-microservices/files/11142803/School.Management.System.1.pdf)





# see the docker logs
# docker logs zipkin
* ##### Jar files created without any errors and using following commands we can execute them.Please execute those command in the root directory.
1. [x] java -jar student/target/student-0.0.1-SNAPSHOT.jar
2. [x] java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar
3. [x] java -jar fraud/target/fraud-0.0.1-SNAPSHOT.jar
4. [x] java -jar notification/target/notification-0.0.1-SNAPSHOT.jar
5. [x] java -jar api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar
6. [x] java -jar amqp/target/amqp-0.0.1-SNAPSHOT.jar

## Docker commands
- docker compose -d up
- mvn clean package -P build-docker-image
- docker compose pull
- docker compose up -d
- docker ps --format=$FORMAT

## Check Docker Logs
- docker logs eureka-server
- docker logs student
- docker logs fraud
- docker logs notification
- docker compose stop
- docker compose start
- docker compose down
# k8s commands
- minikube start --memory=4g
- minikube ip
- kubectl get pods
- kubectl apply -f bootstrap/postgres --> [RUN this command inside minikube folder]
- kubectl describe pod postgres-0 
- kubectl logs postgres-0

# postgres k8s configuration
### SSH in to the databse POD and create all the database inside it
- kubectl exec -it postgres-0 -- psql -U uduwela 
- [then type "\l" & press enter]
- create database customer;
- create database fraud;
- create database student;
- create database notification;
- [then press "CTRL+d"]

# Rabbitmq k8s configuration
- kubectl apply -f bootstrap/rabbitmq --> [RUN this command inside minikube folder]
- [password and user name both are "guest"]

# Zipkin k8s configuration
- kubectl apply -f bootstrap/zipkin --> [RUN this command inside minikube folder]


## Get all the resources
- kubectl get all

- minikube service --url rabbitmq

## Check services are working
- kubectl get services

## To acess the LOAD BALANCER in minikue
- minikube tunnel
- After that we can acess the zipkin using localhost:9411/zpkin/ url also
- 