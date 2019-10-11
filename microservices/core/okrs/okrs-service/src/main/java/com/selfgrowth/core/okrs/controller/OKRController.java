package com.selfgrowth.core.okrs.controller;

import com.selfgrowth.core.okrs.service.OKRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OKRController {
    private final OKRService mOKRService;

    public OKRController(OKRService mOKRService) {
        this.mOKRService = mOKRService;
    }

//    @Autowired
//    OKRController(OKRService okrService){
//        this.mOKRService = okrService;
//
//    }
}
