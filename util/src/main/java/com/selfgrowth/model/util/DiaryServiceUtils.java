package com.selfgrowth.model.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DiaryServiceUtils {
    private static final Logger LOG = LoggerFactory.getLogger(DiaryServiceUtils.class);

    private final LoadBalancerClient loadBalancer;
    private final String port;

    private String serviceAddress = null;

    @Autowired
    public DiaryServiceUtils(@Value("${server.port}") String port, LoadBalancerClient loadBalancer) {

        this.port = port;
        this.loadBalancer = loadBalancer;
    }


    public <T> ResponseEntity<T> createOkResponse(T body) {
        return createResponse(body, HttpStatus.OK);
    }
    /**
     * Clone an existing result as a new one, filtering out http headers that not should be moved on and so on...
     *
     * @param result
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> createResponse(ResponseEntity<T> result) {

        // TODO: How to relay the transfer encoding??? The code below makes the fallback method to kick in...
        ResponseEntity<T> response = createResponse(result.getBody(), result.getStatusCode());
        return response;
    }

    public <T> ResponseEntity<T> createResponse(T body, HttpStatus httpStatus) {
        return new ResponseEntity<>(body, httpStatus);
    }
}