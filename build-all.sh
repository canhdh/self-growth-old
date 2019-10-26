# switch to discovery-service
cd microservices/support/discovery-services/

# build jar file
mvn clean package

# build eureka-server image
docker build -t service-registry:0.1 .

# switch to edge-service
cd .. && cd edge-service/

#build jar file
mvn clean package

# build api-gateway image
docker build -t api-gateway:0.1 .

# switch to zipkin-server
cd .. && cd zipkin-server-final

# build jar file
mvn clean package

# build zipkin-server image
docker build -t zipkin-server:0.1 .

# cd root directory
cd .. && cd .. && cd ..

docker-compose up