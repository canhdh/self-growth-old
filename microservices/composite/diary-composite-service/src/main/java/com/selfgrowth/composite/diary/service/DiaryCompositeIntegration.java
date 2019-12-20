package com.selfgrowth.composite.diary.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.selfgrowth.model.diary.DiaryDto;
import com.selfgrowth.model.util.DiaryServiceUtils;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

public class DiaryCompositeIntegration {
    private static final Logger LOG = Logger.getLogger(DiaryCompositeIntegration.class);
    private final DiaryServiceUtils util;
    private final RestOperations restTemplate;

    public DiaryCompositeIntegration(DiaryServiceUtils util, RestOperations restTemplate) {
        this.util = util;
        this.restTemplate = restTemplate;
    }

    // ---------------------- //
    //     CREATE A DIARY     //
    // ---------------------- //
    @HystrixCommand(fallbackMethod = )
    public ResponseEntity<DiaryDto> createDiary(DiaryDto diaryDto) {
        LOG.info("Will call createDiary with hystrix protection");

        String url = "http://diary-service/";
        LOG.debug("createDiary from URL: " + url);

        ResponseEntity<DiaryDto> resultstr = restTemplate.postForEntity(url, diaryDto, DiaryDto.class);

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

}
