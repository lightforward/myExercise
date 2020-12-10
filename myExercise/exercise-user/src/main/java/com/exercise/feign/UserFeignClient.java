package com.exercise.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exercise-product")
public interface UserFeignClient {

    @RequestMapping(value = "/product/getFeignProductByID", method = RequestMethod.GET)
    public String getFeignProduct(@RequestParam(value = "id") String id);

}
