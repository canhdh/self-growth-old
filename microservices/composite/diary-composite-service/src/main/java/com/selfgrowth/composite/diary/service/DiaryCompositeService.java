package com.selfgrowth.composite.diary.service;

import com.selfgrowth.model.diary.DiaryDto;
import com.selfgrowth.model.util.DebugLog;
import com.selfgrowth.model.util.DiaryServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RestController
public class DiaryCompositeService {

    private static final Logger LOG = LoggerFactory.getLogger(DiaryCompositeService.class);

    private DiaryCompositeIntegration integration;
    private DiaryServiceUtils utils;

    @Autowired
    public DiaryCompositeService(DiaryCompositeIntegration integration, DiaryServiceUtils utils) {
        this.integration = integration;
        this.utils = utils;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<DiaryDto> createDiary(@RequestBody DiaryDto diaryDto){
        DiaryDto createDiaryDtoResult = createBasicDiary(diaryDto);
        return utils.createOkResponse(createDiaryDtoResult);
    }

    @GetMapping(value = "/{diaryId}", produces = "application/json")
    public ResponseEntity<DiaryDto> getDiary(@PathVariable int diaryId){
        DiaryDto getDiaryDtoResult = getBasicDiary(diaryId);
        return utils.createOkResponse(getDiaryDtoResult);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<DiaryDto>> getAllDiary(){
        List<DiaryDto> getDiaryDtoListResult = getAllBasicDiary();
        return utils.createOkResponse(getDiaryDtoListResult);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<DiaryDto> updateDiary(@RequestBody DiaryDto diaryDto){
        DiaryDto UpdateDiaryDtoResult = updateBasicDiary(diaryDto);
        return utils.createOkResponse(UpdateDiaryDtoResult);
    }

    @DeleteMapping(value = "/{diaryId}", produces = "application/json")
    public ResponseEntity<String> deleteDiary(@PathVariable int diaryId){
        String DeleteDiaryDtoResult = deleteBasicDiary(diaryId);
        return utils.createOkResponse(DeleteDiaryDtoResult);
    }


    private DiaryDto createBasicDiary(DiaryDto diaryDto){
        ResponseEntity<DiaryDto> responseEntity = integration.createDiary(diaryDto);
        DiaryDto createDiaryResult = null;
        if (!responseEntity.getStatusCode().is2xxSuccessful()){
            DebugLog.logMessage("Call to createBasicDiary failed: " + responseEntity.getStatusCode());
        } else {
            createDiaryResult = responseEntity.getBody();
        }
        return createDiaryResult;
    }

    private DiaryDto getBasicDiary(int diaryId){
        ResponseEntity<DiaryDto> responseEntity = integration.getDiary(diaryId);
        DiaryDto getDiaryResult = null;
        if (!responseEntity.getStatusCode().is2xxSuccessful()){
            DebugLog.logMessage("Call to getBasicDiary failed: " + responseEntity.getStatusCode());
        } else {
            getDiaryResult = responseEntity.getBody();
        }
        return getDiaryResult;
    }

    private List<DiaryDto> getAllBasicDiary(){
        ResponseEntity<List<DiaryDto>> responseEntity = integration.getAllDiary();
        List<DiaryDto> getAllDiaryResult = null;
        if (!responseEntity.getStatusCode().is2xxSuccessful()){
            DebugLog.logMessage("Call to getAllBasicDiary failed: " + responseEntity.getStatusCode());
        } else {
            getAllDiaryResult = responseEntity.getBody();
        }
        return getAllDiaryResult;
    }

    private DiaryDto updateBasicDiary(DiaryDto diaryDto){
        ResponseEntity<DiaryDto> responseEntity = integration.updateDiary(diaryDto);
        DiaryDto updateDiaryResult = null;
        if (!responseEntity.getStatusCode().is2xxSuccessful()){
            DebugLog.logMessage("Call to updateBasicDiary failed: " + responseEntity.getStatusCode());
        } else {
            updateDiaryResult = responseEntity.getBody();
        }
        return updateDiaryResult;
    }

    private String deleteBasicDiary(int diaryId){
        ResponseEntity<String> responseEntity = integration.deleteDiary(diaryId);
        String deleteDiaryResult = null;
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            DebugLog.logMessage("Call to deleteBasicDiary failed: " + responseEntity.getStatusCode());
        } else {
            deleteDiaryResult = responseEntity.getBody();
        }
        return deleteDiaryResult;
    }
}
