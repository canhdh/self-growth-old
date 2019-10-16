package com.selfgrowth.core.okrs.controller;

import com.selfgrowth.core.okrs.service.OKRService;
import com.selfgrowth.model.okr.OKR;
import com.selfgrowth.model.okr.OKRDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OKRController {
    private final OKRService mOKRService;

    @Autowired
    OKRController(OKRService okrService){
        this.mOKRService = okrService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create (@RequestBody OKRDto okrDto) {
        OKRDto saved = mOKRService.create(okrDto);
        if (saved != null){
            return new ResponseEntity<>(saved, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(), HttpStatus.SEE_OTHER);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update (@RequestBody OKRDto okrDto) {
        okrDto = mOKRService.update(okrDto);
        if(okrDto != null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE);
        }else {
            return new ResponseEntity<>(okrDto, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE, produces = "applicaton/json")
    public void delete (@PathVariable int Id) {
        mOKRService.delete(Id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> findOKRById (@RequestParam("OKR_Id") int okrId) {
        Page<OKR> okrs = (Page<OKR>) mOKRService.findOKRById(okrId);
        if(okrs != null){
            return new ResponseEntity<>(okrs, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> findAll() {
        List<OKRDto> okrDtoList = mOKRService.findAll();
        List<Integer> integers = new ArrayList<>();
        for (OKRDto okrDto : okrDtoList){
            integers.add(okrDto.getOkrID());
        }
        return new ResponseEntity<>(integers, HttpStatus.OK);
    }
}
