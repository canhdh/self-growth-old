package com.selfgrowth.composite.diary.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.selfgrowth.model.diary.DiaryDto;
import com.selfgrowth.model.util.DiaryServiceUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class DiaryCompositeIntegration {
    private static final Logger LOG = Logger.getLogger(DiaryCompositeIntegration.class);
    private final DiaryServiceUtils util;
    private final RestOperations restOperations;

    @Autowired
    public DiaryCompositeIntegration(
            DiaryServiceUtils util,
            RestOperations restOperations
    ) {
        this.util = util;
        this.restOperations = restOperations;
    }

    private List<DiaryDto> response2Diaries(ResponseEntity<String> responseEntity){
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<DiaryDto> locals = Arrays.asList(mapper.readValue(responseEntity.getBody(), DiaryDto[].class));
            return locals;
        } catch (IOException e){
            LOG.debug("IO-err. Fail to read JSON" + e);
            throw new RuntimeException(e);
        } catch (RuntimeException re){
            LOG.debug("RTE-err. Fail to read JSON" + re);
            throw re;
        }
    }

    // ----------------------- //
    //     CREATE A DIARY      //
    // ----------------------- //
    @HystrixCommand(fallbackMethod = "defaultCreateDiary", commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public ResponseEntity<DiaryDto> createDiary(DiaryDto diaryDto){
        LOG.info("Will call createDiary with hystrix protection");

        String url = "http://diary-service/api/v1/diary";
        LOG.debug("createDiary from URL: " + url);

        ResponseEntity<DiaryDto> result = restOperations.postForEntity(url, diaryDto, DiaryDto.class);

        LOG.debug("createDiary http-status: " + result.getStatusCode());
        LOG.debug("createDiary body: " + result.getBody());

        DiaryDto diaryDtoResult = result.getBody();
        LOG.debug("createDiary.cnt " + diaryDtoResult.toString());
        return util.createOkResponse(diaryDtoResult);
    }

    /**
     * Fallback method for createDiary
     *
     * @return
     */
    public ResponseEntity<DiaryDto> defaultCreateDiary(DiaryDto diaryDto){
        LOG.debug("Using fallback method for createDiary with title = " + diaryDto.getTitle());
        return util.createResponse(diaryDto, HttpStatus.OK);
    }

    // ----------------------- //
    //       GET A DIARY       //
    // ----------------------- //
    @HystrixCommand(fallbackMethod = "defaultGetDiary", commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public ResponseEntity<DiaryDto> getDiary(int id){
        LOG.info("Will call getDiary with hystrix protection");

        String url = "http://diary-service/api/v1/diary/" + id;
        LOG.debug("getDiary from URL: " + url);

        ResponseEntity<DiaryDto> result = restOperations.getForEntity(url, DiaryDto.class);

        LOG.debug("getDiary http-status: " + result.getStatusCode());
        LOG.debug("getDiary body: " + result.getBody());

        DiaryDto diaryDtoResult = result.getBody();
        LOG.debug("getDiary.cnt " + diaryDtoResult.toString());

        return util.createOkResponse(diaryDtoResult);
    }

    /**
     * Fallback method for getDiary
     *
     * @return
     */
    public ResponseEntity<DiaryDto> defaultGetDiary(int id){
        LOG.debug("Using fallback method for getDiary with id = " + id);
        DiaryDto diaryDto = new DiaryDto();
        diaryDto.setTitle("ERROR");
        return util.createResponse(diaryDto, HttpStatus.OK);
    }

    // ----------------------- //
    //      GET ALL DIARY      //
    // ----------------------- //
    @HystrixCommand(fallbackMethod = "defaultGetAllDiary", commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public ResponseEntity<List<DiaryDto>> getAllDiary(){
        LOG.info("Will call getAllDiary with Hystrix protection");

        String url = "http://diary-service/api/v1/diary";
        LOG.debug("getAllDiary from URL: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> resultStr = restOperations.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);

        LOG.debug("getAllDiary http-status: " + resultStr.getStatusCode());
        LOG.debug("getAllDiary body: " + resultStr.getBody());

        List<DiaryDto> diaryDtoResult = response2Diaries(resultStr);
        LOG.debug("getAllDiary.cnt " + diaryDtoResult.toString());
//        List<DiaryDto> diaryDtoResult = (List<DiaryDto>) consumer.receiveMessageFromTopic1();
        LOG.debug(diaryDtoResult.toString());

        return util.createOkResponse(diaryDtoResult);
    }

    /**
     * Fallback method for getAll
     *
     * @return
     */
    public ResponseEntity<List<DiaryDto>> defaultGetAllDiary(){
        LOG.debug("Using fallback method for getAllDiary");
        return util.createResponse(null, HttpStatus.OK);
    }

    // ----------------------- //
    //     UPDATE A DIARY      //
    // ----------------------- //
    @HystrixCommand(fallbackMethod = "defaultUpdateDiary", commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public ResponseEntity<DiaryDto> updateDiary(DiaryDto diaryDto){
        LOG.info("Will call updateDiary with Hystrix protection");

        String url = "http://diary-service/api/v1/diary";
        LOG.debug("updateDiary from UR: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DiaryDto> requestUpdate = new HttpEntity<>(diaryDto, headers);
        ResponseEntity<DiaryDto> resultStr = restOperations.exchange(
                url,
                HttpMethod.PUT,
                requestUpdate,
                DiaryDto.class);

        DiaryDto diaryDtoResult = resultStr.getBody();
        return util.createOkResponse(diaryDtoResult);
    }

    /**
     * Fallback method for updateDiary
     *
     * @return
     */
    public ResponseEntity<DiaryDto> defaultUpdateDiary(DiaryDto diaryDto){
        LOG.debug("Using fallback method for updateDiary with id = " + diaryDto.getDiaryId());
        DiaryDto dto = new DiaryDto();
        dto.setTitle("ERROR");
        return util.createResponse(dto, HttpStatus.OK);
    }

    // ----------------------- //
    //     DELETE A DIARY      //
    // ----------------------- //
    @HystrixCommand(fallbackMethod = "defaultDeleteDiary", commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public ResponseEntity<String> deleteDiary(int id){
        LOG.info("Will call deleteDiary with Hystrix protection");

        String url = "http://diary-service/api/v1/diary/" + id;
        LOG.debug("deleteDiary from URL: " + url)   ;

        restOperations.delete(url);

        return util.createOkResponse(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Fallback method for deleteDiary
     *
     * @return
     */
    public ResponseEntity<String > defaultDeleteDiary(int id){
        LOG.debug("Using fallback method for deleteDiary with id = " + id);
        return util.createResponse("ERROR",HttpStatus.OK);
    }
}
