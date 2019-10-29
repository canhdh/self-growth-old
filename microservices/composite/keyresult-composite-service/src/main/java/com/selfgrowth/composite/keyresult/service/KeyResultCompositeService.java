package com.selfgrowth.composite.keyresult.service;

import com.selfgrowth.model.keyResult.KeyResultDto;
import com.selfgrowth.model.util.DebugLog;
import com.selfgrowth.model.util.ServiceUtils;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RestController

public class KeyResultCompositeService {
    private final Logger LOG = (Logger) LoggerFactory.getLogger(KeyResultCompositeService.class);

    private final KeyResultCompositeIntegration integration;
    private final ServiceUtils util;

    @Inject
    public KeyResultCompositeService(KeyResultCompositeIntegration integration, ServiceUtils util) {
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
    public ResponseEntity<List<KeyResultDto>> getAllOKR(@RequestParam("okr_id") int id,
                                                        @RequestParam("page") int page) {
        ResponseEntity<List<KeyResultDto>> okr = getAllOKR(id, page);
        return util.createResponse(okr);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<KeyResultDto> updateOKR(@RequestBody KeyResultDto KeyResultDto) {
        KeyResultDto okr = updateBasicKeyResult(KeyResultDto);
        return util.createOkResponse(okr);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> deteteOKR(@PathVariable  int id) {
        String okr = deteteBasicKeyResult(id);
        return util.createOkResponse(okr);
    }
    private KeyResultDto createBasicKeyReusult(@RequestBody KeyResultDto KeyResultDto) {
        ResponseEntity<KeyResultDto> responseEntity = integration.createKeyResultDto(KeyResultDto);
        KeyResultDto okr = null;
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            DebugLog.logMessage("Call to CreateBasicOKR failed: " + responseEntity.getStatusCode());
        } else {
            okr = responseEntity.getBody();
        }
        return okr;
    }

    private KeyResultDto getBasicKeyResult(@PathVariable int id) {
        ResponseEntity<KeyResultDto> okrResult = integration.getKeyResult(id);
        KeyResultDto KeyResultDto = null;
        if (!okrResult.getStatusCode().is2xxSuccessful()) {
            DebugLog.logMessage("Call to OKR result failed: " + okrResult.getStatusCode());
        } else {
            KeyResultDto = okrResult.getBody();
        }
        return KeyResultDto;
    }

    private KeyResultDto updateBasicKeyResult(@RequestBody KeyResultDto KeyResultDto) {
        ResponseEntity<KeyResultDto> keyResult = integration.updateKeyResult(KeyResultDto);
        KeyResultDto KeyResultDtoResult = null;
        if (!keyResult.getStatusCode().is2xxSuccessful()) {
            DebugLog.logMessage("Call to OKR result failed: " + keyResult.getStatusCode());
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
            DebugLog.logMessage("Call to OKR result failed: " + okrResult.getStatusCode());
        } else {
            KeyResultDtoList = okrResult.getBody();
        }
        return KeyResultDtoList;
    }

    private String deteteBasicKeyResult(@PathVariable int id) {
        ResponseEntity<String> okrResult = integration.deleteKeyResult(id);
        String KeyResultDtoResult = null;
        if (!okrResult.getStatusCode().is2xxSuccessful()) {
            DebugLog.logMessage("Call to OKR result failed: " + okrResult.getStatusCode());
        } else {
            KeyResultDtoResult = okrResult.getBody();
        }
        return KeyResultDtoResult;
    }

}
