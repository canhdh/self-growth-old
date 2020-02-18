# change to diary-composite-service directory
cd microservices/composite/diary-composite-service
# build diary-composite-service:0.1 image
docker build -t diary-composite-service:0.1 .

# change to diary-service directory
cd ../../core/diary
# build diary-service:0.1 image
docker build -t diary-service:0.1 .

## change to discovery-service directory
#cd ../../support/discovery-services
## build discovery-service:0.1 image
#docker build -t service-registry:0.1 .
#
## change to edge-service directory
#cd ../edge-service
## build api-gateway:0.1 image
#docker build -t api-gateway:0.1 .
