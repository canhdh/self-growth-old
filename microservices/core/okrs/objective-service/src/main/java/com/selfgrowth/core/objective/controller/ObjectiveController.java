package com.selfgrowth.core.objective.controller;

import com.selfgrowth.model.objective.Objective;
import com.selfgrowth.model.objective.ObjectiveDto;
import com.selfgrowth.core.objective.service.ObjectiveServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/objectives")
public class ObjectiveController {
    private final ObjectiveServiceIml objectiveServiceIml;

    @Autowired
    public ObjectiveController(ObjectiveServiceIml objectiveServiceIml) {
        this.objectiveServiceIml = objectiveServiceIml;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> create(@RequestBody ObjectiveDto objectiveDto){
        ObjectiveDto saved = objectiveServiceIml.create(objectiveDto);
        if (saved != null){
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(),HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> update(@RequestBody ObjectiveDto ObjectiveDto){
        ObjectiveDto = objectiveServiceIml.update(ObjectiveDto);
        if (ObjectiveDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(ObjectiveDto, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?>  delete(@PathVariable("id") int ObjectiveID){
        ObjectiveDto objectiveDto = objectiveServiceIml.delete(ObjectiveID);
        if (objectiveDto !=null) {
            return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> findByObjectiveID(@PathVariable("id") int ObjectiveID){
        Objective objectives = objectiveServiceIml.findByObjectiveID(ObjectiveID);
        if (objectives != null){
            return new ResponseEntity<>(objectives,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAll(){
        List<ObjectiveDto> objectiveDtoList = objectiveServiceIml.findAll();
        return new ResponseEntity<>(objectiveDtoList,HttpStatus.OK);
    }
}
