package com.selfgrowth.composite.keyresult.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.selfgrowth.model.keyResult.KeyResultDto;
import com.selfgrowth.model.util.DebugLog;
import com.selfgrowth.model.util.ServiceUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class KeyResultCompositeIntegration {
    private final ServiceUtils util;
    private final RestOperations restTemplate;

    @Inject
    public KeyResultCompositeIntegration(ServiceUtils util, RestOperations restTemplate) {
        this.util = util;
        this.restTemplate = restTemplate;
    }

    // ----------------------- //
    // CREATE A KEYRÉULT       //
    // ----------------------- //
    @HystrixCommand(fallbackMethod = "defaultKeyResult", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")})
    public ResponseEntity<KeyResultDto> createKeyResult(KeyResultDto keyResultDto) {
        DebugLog.logMessage("Will call createKeyResult with hystrix protection");

        String url = "http://keyresult-service";
        DebugLog.logMessage("createKeyResult from URL " + url);

        ResponseEntity<KeyResultDto> resultstr = restTemplate.postForEntity(url, keyResultDto, KeyResultDto.class);

        DebugLog.logMessage("createKeyResult http-status: " + resultstr.getStatusCode());
        DebugLog.logMessage("createKeyResult body: " + resultstr.getBody());

        KeyResultDto keyResultDtoResult = resultstr.getBody();
        DebugLog.logMessage("createKeyResult.cnt " + keyResultDtoResult.toString());

        return util.createOkResponse(keyResultDtoResult);
    }

    /**
     * Fallback method for createKeyResult()
     *
     * @return
     */
    public ResponseEntity<KeyResultDto> defaultKeyResult(KeyResultDto keyResultDto) {
        DebugLog.logMessage("Using fallback method for keyresult-service with id = " + keyResultDto.getTitle());
        return util.createResponse(
                keyResultDto,
                HttpStatus.OK);
    }

    // ---------------- //
    // GET A KEYRESULT  //
    // ---------------- //
    @HystrixCommand(fallbackMethod = "defaultKeyResult", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")})
    public ResponseEntity<KeyResultDto> getKeyResult (int id) {
        DebugLog.logMessage("Will call createKeyResult with Hystrix protection ");

        String url = "http://keyresult-service/" + id;
        DebugLog.logMessage("get KeyResult from URL: " + url);

        ResponseEntity<KeyResultDto> resultStr = restTemplate.getForEntity(url, KeyResultDto.class);

        DebugLog.logMessage("getKeyResult http-status: " + resultStr.getStatusCode());
        DebugLog.logMessage("getKeyResult body: " + resultStr.getBody());

        KeyResultDto keyResultDtoResult = resultStr.getBody();
        DebugLog.logMessage("get KeyResult.cnt: " + resultStr.toString());

        return util.createOkResponse(keyResultDtoResult);
    }
    /**
     * Fallback method for GetKeyResult()
     *
     * @return
     */
    public ResponseEntity<KeyResultDto> defaultKeyResult(int id) {
        DebugLog.logMessage("Using fallback method for KeyResult-service with id = " + id);
        KeyResultDto keyResultDto = new KeyResultDto();
        keyResultDto.setTitle("ERROR");
        return util.createResponse(
                keyResultDto,
                HttpStatus.OK);
    }

    // ---------------- //
    //GET ALL KEYRESULT //
    // ---------------- //
    @HystrixCommand(fallbackMethod = "defaultAllKeyResult", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")})
    public ResponseEntity<List<KeyResultDto>> getAllKeyResult(int keyResultId, int page) {
        DebugLog.logMessage("Will call getAllKeyResult with Hystrix protection");

        String url = "http://keyresult-service";
        DebugLog.logMessage("getAllKeyResult from URL: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("keyresult_id", keyResultId)
                .queryParam("page", page);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> resultStr = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);

        DebugLog.logMessage("getAllKeyResult http-status: " + resultStr.getStatusCode());
        DebugLog.logMessage("getAllKeyResult body: " + resultStr.getBody());

        List<KeyResultDto> keyResultDtoResult = response2KeyResult(resultStr);
        DebugLog.logMessage("getAllKeyResult.cnt: " + keyResultDtoResult.toString());

        return util.createOkResponse(keyResultDtoResult);
    }

    /**
     * Fallback method for getAllKeyResult()
     *
     * @return
     */
    public ResponseEntity<List<KeyResultDto>> defaultAllKeyResult(int keyResultId, int page) {
        DebugLog.logMessage("Using fallback method for KeyResultDto-service with keyResultId = " + keyResultId);
        return util.createResponse(
                null,
                HttpStatus.OK);
    }

    private List<KeyResultDto> response2KeyResult(ResponseEntity<String> response) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<KeyResultDto> locals =   Arrays.asList(mapper.readValue(response.getBody(), KeyResultDto[].class));

            return locals;
        } catch (IOException e) {
            DebugLog.logMessage("IO-err. Failed to read JSON" + e);
            throw new RuntimeException(e);
        } catch (RuntimeException re) {
            DebugLog.logMessage("RTE-err. Failed to read JSON" + re);
            throw re;
        }
    }

    // ---------------- //
    //UPDATE A KEYRESULT//
    // ---------------- //

    @HystrixCommand(fallbackMethod = "defaultUpdateKeyResult", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")})
    public ResponseEntity<KeyResultDto> updateKeyResult(KeyResultDto keyResultDto) {
        DebugLog.logMessage("Will call updateKeyResultDto with Hystrix protection");

        String url = "http://keyresult-service";
        DebugLog.logMessage("updateKeyResult from URL: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<KeyResultDto> requestUpdate = new HttpEntity<>(keyResultDto, headers);
        ResponseEntity<KeyResultDto> resultStr = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestUpdate,
                KeyResultDto.class);

        KeyResultDto keyResultDtoResult = resultStr.getBody();
        return util.createOkResponse(keyResultDtoResult);
    }

    /**
     * Fallback method for update KeyResult()
     *
     * @return
     */
    public ResponseEntity<KeyResultDto> defaultUpdateKeyResult(KeyResultDto keyResultDto) {
        DebugLog.logMessage("Using fallback method for KeyResultDto-service with id = " + keyResultDto.getKeyResultID());
        KeyResultDto dto = new KeyResultDto();
        dto.setTitle("ERROR");
        return util.createResponse(
                dto,
                HttpStatus.OK);
    }

    // ----------------------- //
    //   DELETE A KEYRESULT    //
    // ----------------------- //
    @HystrixCommand(fallbackMethod = "defaultDeleteKeyResult", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")})
    public ResponseEntity<String> deleteKeyResult(int id) {
        DebugLog.logMessage("Will call deleteKeyResult with Hystrix protection");

        String url = "http://keyresult-service/" + id;
        DebugLog.logMessage("deleteKeyResultDto from URL: " + url);

        restTemplate.delete(url);

        return util.createOkResponse(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Fallback method for deleteCustomer()
     *
     * @return
     */
    public ResponseEntity<String> defaultDeleteKeyResult(int id) {
        DebugLog.logMessage("Using fallback method for KeyResultDto-service with id = " + id);
        return util.createResponse(
                "ERROR",
                HttpStatus.OK);
    }
}
