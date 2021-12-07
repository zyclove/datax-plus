package com.datax.plus.controller;

import com.datax.plus.model.DataJob;
import com.datax.plus.model.HttpRequestResult;
import com.datax.plus.model.User;
import com.datax.plus.model.view.DataJobList;
import com.datax.plus.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/job")
public class JobController {

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody
    HttpRequestResult list() {
        HttpRequestResult req = new HttpRequestResult();
        req.setCode(20000);
        req.setMsg("");

        DataJobList dataJobListObj = new DataJobList();
        ArrayList<DataJob> dataJobList = new ArrayList<DataJob>();

        DataJob temp = new DataJob();
        temp.setDataJobName("JOB1");

        DataJob temp2 = new DataJob();
        temp2.setDataJobName("JOB2");

        dataJobList.add(temp);
        dataJobList.add(temp2);

        dataJobListObj.setList(dataJobList);

        req.setData(dataJobListObj);
        return req;
    }

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
        User repUserObj = new User();
        repUserObj.setCode(100);
        req.setData(repUserObj);
        return req;
    }
}
