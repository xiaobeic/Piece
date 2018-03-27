package com.jjg.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JJGController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String demo() throws Exception {
//		priReportServiceImpl.demo();
        return "OK";
    }
}
