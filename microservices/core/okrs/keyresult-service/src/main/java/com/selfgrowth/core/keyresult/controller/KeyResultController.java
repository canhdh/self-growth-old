package com.selfgrowth.core.keyresult.controller;

import com.selfgrowth.model.keyResult.KeyResult;
import com.selfgrowth.model.keyResult.KeyResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.selfgrowth.core.keyresult.service.KeyResultService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KeyResultController {
    private final KeyResultService mkeyResultService;

    @Autowired
    public KeyResultController(KeyResultService mkeyResultService) {
        this.mkeyResultService = mkeyResultService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> create(@RequestBody KeyResultDto keyResultDto){
        KeyResultDto saved = mkeyResultService.create(keyResultDto);
        if (saved != null){
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(),HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> update(@RequestBody KeyResultDto keyResultDto){
        keyResultDto = mkeyResultService.update(keyResultDto);
        if (keyResultDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(keyResultDto,HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "{/id}",produces = "application/json")
    public void  delete(@PathVariable int id){mkeyResultService.delete(id);}

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findByKeyResultID(@RequestParam("KeyResult_ID") int keyResultID){
        Page<KeyResult> keyResults = (Page<KeyResult>)mkeyResultService.findKeyResultByID(keyResultID);
        if (keyResults != null){
            return new ResponseEntity<>(keyResults,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> findAll(){
        List<KeyResultDto> keyResultDtoList = mkeyResultService.findAll();
        List<Integer> integers = new ArrayList<>();
        for(KeyResultDto keyResultDto : keyResultDtoList){
            integers.add(keyResultDto.getKeyResultID());
        }
        return new ResponseEntity<>(integers,HttpStatus.OK);
    }

}
