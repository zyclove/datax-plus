package com.datax.plus.controller;

import com.datax.plus.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public String checkLogin(@RequestBody User user) {
        return String.valueOf(1);
    }
}
