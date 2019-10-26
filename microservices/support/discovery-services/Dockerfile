# openjdk 11
FROM openjdk:11

# copy application
COPY /target/discovery-services-0.0.1-SNAPSHOT.jar /home/app.jar

# copy libs
COPY /target/libs /home/libs

# expose service port
EXPOSE 8761

# run service
ENTRYPOINT ["java","-jar","/home/app.jar"]