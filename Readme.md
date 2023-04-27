[School Management System (1).pdf](https://github.com/dhanushka365/SchoolEase-backend-microservices/files/11142803/School.Management.System.1.pdf)




#docker compose -d up
# see the docker logs
# docker logs zipkin
* ##### Jar files created without any errors and using following commands we can execute them.Please execute those command in the root directory.
1. [x] java -jar student/target/student-0.0.1-SNAPSHOT.jar
2. [x] java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar
3. [x] java -jar fraud/target/fraud-0.0.1-SNAPSHOT.jar
4. [x] java -jar notification/target/notification-0.0.1-SNAPSHOT.jar
5. [x] java -jar api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar
6. [x] java -jar amqp/target/amqp-0.0.1-SNAPSHOT.jar

mvn clean package -P build-docker-image
docker compose pull
docker compose up -d
docker ps --format=$FORMAT

#Check Docker Logs
docker logs eureka-server
docker logs student
docker logs fraud
docker logs notification

docker compose stop
docker compose start
docker compose down



# k8s commands
minikube start --memory=4g