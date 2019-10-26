package com.selfgrowth.core.keyresult.controller;

import com.selfgrowth.core.keyresult.service.KeyResultServiceIml;
import com.selfgrowth.model.keyResult.KeyResult;
import com.selfgrowth.model.keyResult.KeyResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/keyResults")
public class KeyResultController {
    private final KeyResultServiceIml keyResultServiceIml;

    @Autowired
    public KeyResultController(KeyResultServiceIml keyResultServiceIml) {
        this.keyResultServiceIml = keyResultServiceIml;
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<?> create(@RequestBody KeyResultDto keyResultDto){
        KeyResultDto saved = keyResultServiceIml.create(keyResultDto);
        if (saved != null){
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(),HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(path = "/update", produces = "application/json")
    public ResponseEntity<?> update(@RequestBody KeyResultDto keyResultDto){
        keyResultDto = keyResultServiceIml.update(keyResultDto);
        if (keyResultDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(keyResultDto,HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "{/keyResultID}",produces = "application/json")
    public ResponseEntity<?>  delete(@RequestParam int keyResultID){
        KeyResultDto keyResultDto = keyResultServiceIml.delete(keyResultID);
        if (keyResultDto !=null) {
            return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/findByID",produces = "application/json")
    public ResponseEntity<?> findByKeyResultID(@RequestParam("KeyResult_ID") int keyResultID){
        KeyResult keyResults = keyResultServiceIml.findKeyResultByID(keyResultID);
        if (keyResults != null){
            return new ResponseEntity<>(keyResults,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<?> findAll(){
        List<KeyResultDto> keyResultDtoList = keyResultServiceIml.findAll();
        List<Integer> integers = new ArrayList<>();
        for(KeyResultDto keyResultDto : keyResultDtoList){
            integers.add(keyResultDto.getKeyResultID());
        }
        return new ResponseEntity<>(keyResultDtoList,HttpStatus.OK);
    }

}
