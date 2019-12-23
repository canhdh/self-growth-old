# openjdk 11
FROM openjdk:11

# copy application
COPY target/keyresult-service-0.1-SNAPSHOT.jar /home/app.jar

# expose service port
EXPOSE 8082

# run service
ENTRYPOINT ["java","-jar","/home/app.jar"]