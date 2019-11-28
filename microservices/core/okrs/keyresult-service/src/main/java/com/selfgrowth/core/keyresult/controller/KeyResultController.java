package com.selfgrowth.core.keyresult.controller;

import com.selfgrowth.core.keyresult.service.KeyResultServiceIml;
import com.selfgrowth.model.keyResult.KeyResult;
import com.selfgrowth.model.keyResult.KeyResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/keyResults")
public class KeyResultController {
    private final KeyResultServiceIml keyResultServiceIml;

    @Autowired
    public KeyResultController(KeyResultServiceIml keyResultServiceIml) {
        this.keyResultServiceIml = keyResultServiceIml;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> create(@RequestBody KeyResultDto keyResultDto){
        try {
            KeyResultDto saved = keyResultServiceIml.create(keyResultDto);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (NullPointerException e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(),HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> update(@RequestBody KeyResultDto keyResultDto){
        keyResultDto = keyResultServiceIml.update(keyResultDto);
        if (keyResultDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(keyResultDto,HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?>  delete(@PathVariable("id") int keyResultID){
        KeyResultDto keyResultDto = keyResultServiceIml.delete(keyResultID);
        if (keyResultDto !=null) {
            return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> findByKeyResultID(@PathVariable("id") int keyResultID){
        KeyResultDto keyResultDto = keyResultServiceIml.findByKeyResultIDConvertToDto(keyResultID);
        if (keyResultDto != null){
            return new ResponseEntity<>(keyResultDto,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAll(){
        List<KeyResultDto> keyResultDtoList = keyResultServiceIml.findAll();
        return new ResponseEntity<>(keyResultDtoList,HttpStatus.OK);
    }

}
