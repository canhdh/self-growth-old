package com.selfgrowth.core.diary.controller;

import com.selfgrowth.core.diary.service.DiaryServiceIml;
import com.selfgrowth.model.diary.Diary;
import com.selfgrowth.model.diary.DiaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/diary")
public class DiaryController {
    private final DiaryServiceIml diaryServiceIml;

    @Autowired
    public DiaryController(DiaryServiceIml diaryServiceIml) {
        this.diaryServiceIml = diaryServiceIml;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> create(@RequestBody DiaryDto diaryDto){
        try {
            DiaryDto saved = diaryServiceIml.create(diaryDto);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (NullPointerException exception){
            System.out.println(exception);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(),HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> update(@RequestBody DiaryDto dto){
        DiaryDto diaryDto = diaryServiceIml.update(dto);
        if (diaryDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(diaryDto,HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{diary_id}",produces = "application/json")
    public ResponseEntity<?>  delete(@PathVariable("diary_id") int diaryId){
        DiaryDto diaryDto = diaryServiceIml.delete(diaryId);
        if (diaryDto !=null) {
            return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{diary_id}",produces = "application/json")
    public ResponseEntity<?> findByDiaryId(@PathVariable("diary_id") int diaryId){
        DiaryDto diaryDto = diaryServiceIml.findByDiaryIdConvertToDto(diaryId);
        if (diaryDto != null){
            return new ResponseEntity<>(diaryDto,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAll(){
        List<DiaryDto> diaryDtoList = diaryServiceIml.findAll();
        return new ResponseEntity<>(diaryDtoList,HttpStatus.OK);
    }

}
