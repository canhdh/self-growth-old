# build jar file
mvn clean package

# change to diary-composite-service directory
cd ~/IdeaProjects/selfgrowth/microservices/composite/diary-composite-service/
# build diary-composite-service:0.1 image
docker build -t diary-composite-service:0.1 .

# change to keyresult-composite-service directory
cd ~/IdeaProjects/selfgrowth/microservices/composite/keyresult-composite-service/
# build keyresult-composite-service:0.1 image
docker build -t keyresult-composite-service:0.1 .

# change to diary-service directory
cd ~/IdeaProjects/selfgrowth/microservices/core/diary/
# build diary-service:0.1 image
docker build -t diary-service:0.1 .

# change to keyresult-service directory
cd ~/IdeaProjects/selfgrowth/microservices/core/okrs/keyresult-service/
# build keyresult-service:0.1 image
docker build -t keyresult-service:0.1 .

# change to objective-service directory
cd ~/IdeaProjects/selfgrowth/microservices/core/okrs/objective-service/
# build  objective-service:0.1 image
docker build -t objective-service:0.1 .

# change to discovery-service directory
cd ~/IdeaProjects/selfgrowth/microservices/support/discovery-services/
# build discovery-service:0.1 image
docker build -t discovery-services:0.1 .

# change to edge-service directory
cd ~/IdeaProjects/selfgrowth/microservices/support/edge-service/
# build api-gateway:0.1 image
docker build -t api-gateway:0.1 .

# change to zipkin-service directory
cd ~/IdeaProjects/selfgrowth/microservices/support/zipkin-server-final/
# build zipkin-service:0.1 image
docker build -t zipkin-service:0.1 .