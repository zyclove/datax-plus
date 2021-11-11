package com.dataxservice.dataxservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @RequestMapping(value = "/t", method = RequestMethod.GET)
    public String hello() {
        return "Hello world";
    }

}
