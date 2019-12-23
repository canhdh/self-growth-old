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

# switch to keyresult service
cd ../.. && cd core/okrs/keyresult-service

# build keyresult service image
docker build -t keyresult-service:0.1 .

# switch to ojective service
cd ../ && cd objective-service

# build ojective service image
docker build -t objective-service:0.1 .

cd ../../../