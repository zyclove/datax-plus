package com.datax.plus.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.datax.plus.model.HttpRequestResult;
import com.datax.plus.model.User;
import com.datax.plus.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public @ResponseBody
    HttpRequestResult checkLogin(@RequestBody User user) {
        HttpRequestResult req = new HttpRequestResult();
        req.setCode(20000);
        req.setMsg("");

        // https://www.jianshu.com/p/e88d3f8151db

        // JWT create 方法，
   //     String token= JWT.create().withAudience("user.getId()").sign(Algorithm.HMAC256(user.getPassword()));
//        String jwtToken = Jwts.builder().setSubject(reqPerson.getUsername()).claim("roles", "member").setIssuedAt(new Date())
//                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        Map<String, String > payload = new HashMap<String, String>();
        String token = JwtUtil.signJwt(user, payload);
        req.setToken(token);
        return req;
    }
}