package com.k001.selfgrowth.zuul.controller;

import com.k001.selfgrowth.zuul.config.JWTUtil;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TokenController {
    public String getToken(){
        return JWTUtil.getJWTToken();
    }
}
