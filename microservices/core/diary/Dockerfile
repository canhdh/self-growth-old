FROM openjdk:11

COPY target/diary-0.0.1-SNAPSHOT.jar /home/app.jar

EXPOSE 8008

ENTRYPOINT ["java","-jar","/home/app.jar"]