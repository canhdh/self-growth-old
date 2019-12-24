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

    private List<DiaryDto> response2Diary(ResponseEntity<String> responseEntity){
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

    @Autowired
    public DiaryCompositeIntegration(DiaryServiceUtils util, RestOperations restOperations) {
        this.util = util;
        this.restOperations = restOperations;
    }

    // ----------------------- //
    //     CREATE A DIARY      //
    // ----------------------- //
    @HystrixCommand(fallbackMethod = "defaultCreateDiary",
            commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"))
    public ResponseEntity<DiaryDto> createDiary(DiaryDto diaryDto){
        LOG.info("Will call createDiary with hystrix protection");

        String url = "http://diary-service/";
        LOG.debug("createDiary from URL: " + url);

        ResponseEntity<DiaryDto> resultstr = restOperations.postForEntity(url, diaryDto, DiaryDto.class);

        LOG.debug("createDiary http-status: " + resultstr.getStatusCode());
        LOG.debug("createDiary body: " + resultstr.getBody());

        DiaryDto diaryDtoResult = resultstr.getBody();
        LOG.debug("createDiary.cnt " + diaryDtoResult.toString());
        return util.createOkResponse(diaryDtoResult);
    }

    /**
     * Fallback method for createDiary
     *
     * @return
     */
    public ResponseEntity<DiaryDto> defaultCreateDiary(DiaryDto diaryDto){
        LOG.debug("Using fallback method for createDiary with id = " + diaryDto.getDiaryId());
        return util.createResponse(diaryDto, HttpStatus.OK);
    }

    // ----------------------- //
    //       GET A DIARY       //
    // ----------------------- //
    @HystrixCommand(fallbackMethod = "defaultGetDiary",
            commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"))
    public ResponseEntity<DiaryDto> getDiary(int id){
        LOG.info("Will call getDiary with hystrix protection");

        String url = "http://diary-service/";
        LOG.debug("getDiary from URL: " + url);

        ResponseEntity<DiaryDto> resultstr = restOperations.getForEntity(url, DiaryDto.class);

        LOG.debug("getDiary http-status: " + resultstr.getStatusCode());
        LOG.debug("getDiary body: " + resultstr.getBody());

        DiaryDto diaryDtoResult = resultstr.getBody();
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
        return util.createResponse(diaryDto, HttpStatus.OK);
    }

    // ----------------------- //
    //      GET ALL DIARY      //
    // ----------------------- //
    @HystrixCommand(fallbackMethod = "defaultGetAllDiary",
            commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"))
    public ResponseEntity<List<DiaryDto>> getAllDiary(){
        LOG.info("Will call getAllDiary with Hystrix protection");

        String url = "http://diary-service/";
        LOG.debug("getAllDiary from URL: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> resultstr = restOperations.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);

        LOG.debug("getAllDiary http-status: " + resultstr.getStatusCode());
        LOG.debug("getAllDiary body: " + resultstr.getBody());

        List<DiaryDto> diaryDtosResult = response2Diary(resultstr);
        LOG.debug("getAllDiary.cnt " + diaryDtosResult.toString());

        return util.createOkResponse(diaryDtosResult);
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
    @HystrixCommand(fallbackMethod = "defaultUpdateDiary",
            commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"))
    public ResponseEntity<DiaryDto> updateDiary(DiaryDto diaryDto){
        LOG.info("Will call updateDiary with Hystrix protection");

        String url = "http://diary-service/";
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
    @HystrixCommand(fallbackMethod = "defaultDeleteDiary",
            commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"))
    public ResponseEntity<String> deleteDiary(int id){
        LOG.info("Will call deleteDiary with Hystrix protection");

        String url = "http://diary-service/";
        LOG.debug("deleteDiary from URL: " + url);

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
