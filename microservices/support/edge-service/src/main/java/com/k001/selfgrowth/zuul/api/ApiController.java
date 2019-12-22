package com.k001.selfgrowth.zuul.api;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@EnableAutoConfiguration

public class ApiController implements SwaggerResourcesProvider {

        @Override

        public List<SwaggerResource> get() {
            List<SwaggerResource> resources =  new ArrayList<>();
            resources.add(swaggerResource("keyresult-service", "/api/keyresult-service/v2/api-docs","2.0"));
            resources.add(swaggerResource("objective-service", "/api/objective-service/v2/api-docs","2.0"));
            resources.add(swaggerResource("diary-service", "/api/diary-service/v2/api-docs","2.0"));
            return resources;
        }
        private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
}
