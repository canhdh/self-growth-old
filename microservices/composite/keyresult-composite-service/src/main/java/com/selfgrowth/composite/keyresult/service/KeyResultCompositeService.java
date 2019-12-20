package com.selfgrowth.composite.keyresult.service;

import com.selfgrowth.model.keyResult.KeyResultDto;
import com.selfgrowth.model.util.DebugLog;
import com.selfgrowth.model.util.KeyResultServiceUtils;
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

public class KeyResultCompositeService {

    private final KeyResultCompositeIntegration integration;
    private final KeyResultServiceUtils util;

    @Autowired
    public KeyResultCompositeService(KeyResultCompositeIntegration integration, KeyResultServiceUtils util) {
        this.integration = integration;
        this.util = util;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<KeyResultDto> createKeyResult(@RequestBody KeyResultDto keyResultDto) {
        KeyResultDto keyresult = createBasicKeyReusult(keyResultDto);
        return util.createOkResponse(keyresult);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<KeyResultDto> getKeyResult(@PathVariable int id) {
        KeyResultDto KeyResultDto = getBasicKeyResult(id);
        return util.createOkResponse(KeyResultDto);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<KeyResultDto>> getAllKeyRessult(@RequestParam("okr_id") int id,
                                                               @RequestParam("page") int page) {
        List<KeyResultDto> keyResult = getAllBasicKeyResult(id, page);
        return util.createOkResponse(keyResult);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<KeyResultDto> updateKeyRessult(@RequestBody KeyResultDto KeyResultDto) {
        KeyResultDto okr = updateBasicKeyResult(KeyResultDto);
        return util.createOkResponse(okr);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> deteteKeyResult(@PathVariable  int id) {
        String okr = deteteBasicKeyResult(id);
        return util.createOkResponse(okr);
    }
    private KeyResultDto createBasicKeyReusult(@RequestBody KeyResultDto KeyResultDto) {
        ResponseEntity<KeyResultDto> responseEntity = integration.createKeyResult(KeyResultDto);
        KeyResultDto okr = null;
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            DebugLog.logMessage("Call to CreateBasicKeyResult failed: " + responseEntity.getStatusCode());
        } else {
            okr = responseEntity.getBody();
        }
        return okr;
    }

    private KeyResultDto getBasicKeyResult(@PathVariable int id) {
        ResponseEntity<KeyResultDto> okrResult = integration.getKeyResult(id);
        KeyResultDto KeyResultDto = null;
        if (!okrResult.getStatusCode().is2xxSuccessful()) {
            DebugLog.logMessage("Call to getBasicKeyResult failed: " + okrResult.getStatusCode());
        } else {
            KeyResultDto = okrResult.getBody();
        }
        return KeyResultDto;
    }

    private KeyResultDto updateBasicKeyResult(@RequestBody KeyResultDto KeyResultDto) {
        ResponseEntity<KeyResultDto> keyResult = integration.updateKeyResult(KeyResultDto);
        KeyResultDto KeyResultDtoResult = null;
        if (!keyResult.getStatusCode().is2xxSuccessful()) {
            DebugLog.logMessage("Call to updateKeyResult failed: " + keyResult.getStatusCode());
        } else {
            KeyResultDtoResult = keyResult.getBody();
        }
        return KeyResultDtoResult;
    }

    private List<KeyResultDto> getAllBasicKeyResult(@RequestParam("okr_id") int keyResultId,
                                                    @RequestParam("page") int page) {
        ResponseEntity<List<KeyResultDto>> okrResult = integration.getAllKeyResult(keyResultId, page);
        List<KeyResultDto> KeyResultDtoList = null;
        if (!okrResult.getStatusCode().is2xxSuccessful()) {
            DebugLog.logMessage("Call to getAllKeyResult failed: " + okrResult.getStatusCode());
        } else {
            KeyResultDtoList = okrResult.getBody();
        }
        return KeyResultDtoList;
    }

    private String deteteBasicKeyResult(@PathVariable int id) {
        ResponseEntity<String> okrResult = integration.deleteKeyResult(id);
        String KeyResultDtoResult = null;
        if (!okrResult.getStatusCode().is2xxSuccessful()) {
            DebugLog.logMessage("Call to deleteKeyResult failed: " + okrResult.getStatusCode());
        } else {
            KeyResultDtoResult = okrResult.getBody();
        }
        return KeyResultDtoResult;
    }
}