FROM openjdk:11

COPY target/objective-service-0.1-SNAPSHOT.jar /home/app.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","/home/app.jar"]