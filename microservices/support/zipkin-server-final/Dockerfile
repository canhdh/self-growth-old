FROM openjdk:11

COPY target/zipkin-server-final-0.1-SNAPSHOT.jar /home/app.jar

EXPOSE 9411

ENTRYPOINT ["java","-jar","/home/app.jar"]