package com.example.exerciseproduct.controller;

import com.example.exerciseproduct.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userFeign")
public class UserFeignController {

    private static final Logger LOGGR = LoggerFactory.getLogger(UserFeignController.class);

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping(value = "/getFeignProduct")
    public String getRibbonProduct() {
        String msg = this.userFeignClient.getFeignProduct("华为P30");
        return msg;
    }

}
