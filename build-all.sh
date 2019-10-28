# build jar file
mvn clean package

# switch to discovery-service
cd microservices/support/discovery-services/

# build eureka-server image
docker build -t service-registry:0.1 .

# switch to edge-service
cd .. && cd edge-service/

# build api-gateway image
docker build -t api-gateway:0.1 .

# switch to zipkin-server
cd .. && cd zipkin-server-final

# build zipkin-server image
docker build -t zipkin-server:0.1 .

# switch to keyResultService
cd ../.. && cd core/okrs/keyresult-service

# build key result service
docker build -t keyresult-service:1.0 .

cd ../../../