# openjdk 11
FROM openjdk:11

# copy zuul application
COPY /target/demo-0.0.1-SNAPSHOT.jar /home/app.jar

# copy libs
COPY /target/libs /home/libs

# expose zuul port
EXPOSE 8762

# run zuul API gateway
ENTRYPOINT ["java","-jar","/home/app.jar"]