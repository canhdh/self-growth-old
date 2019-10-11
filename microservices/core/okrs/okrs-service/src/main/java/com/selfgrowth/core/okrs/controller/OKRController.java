package com.selfgrowth.core.okrs.controller;

import com.selfgrowth.core.okrs.repository.OKRRepository;
import com.selfgrowth.core.okrs.service.OKRService;
import com.selfgrowth.model.okr.OKR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OKRController {
    private final OKRService mOKRService;

    public OKRController(OKRService mOKRService) {
        this.mOKRService = mOKRService;
    }

    @Autowired
    OKRRepository okrRepository;

    @PostMapping("/okr")
    public OKR create(@RequestBody OKR body){
        OKR okr = body;
        return okrRepository.save(okr);
    }

    @GetMapping("/okrs")
    public List<OKR> showAll(){
        return okrRepository.findAll();
    }
}
