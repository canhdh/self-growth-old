# create image from base image openjdk:11
FROM openjdk:11

# copy package .jar of application and dependency to woking directory on container
COPY target/diary-composite-service-0.0.1-SNAPSHOT.jar /home/app.jar

# expose port for diary-composite-service
EXPOSE 8090

# run application on entrypoint
ENTRYPOINT ["java","-jar","/home/app.jar"]