package com.example.exerciseproduct.controller;

import com.example.exerciseproduct.model.Url;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/userRibbon")
public class UserRibbonController {

    private static final Logger LOGGR = LoggerFactory.getLogger(UserRibbonController.class);

    /**
     * config server获取配置文件的值,配置文件读取数据到pojo
     */
    @Autowired
    private Url url;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/login")
    public Map<Object, Object> login(@RequestParam(value = "phone")String phone, @RequestParam(value = "pnum")String pnum) {
        Map<Object, Object> resMap = new HashMap<>();
        if ("1".equals(phone) && "1".equals(pnum)) {
            resMap.put("succ",true);
            resMap.put("msg","成功");
        }else {
            resMap.put("succ",false);
            resMap.put("msg","失败");
        }
        return resMap;
    }

    @Hystrix(fallbackHandler = {})
    @GetMapping(value = "/getRibbonProduct")
    public String getRibbonProduct(HttpServletRequest request) {
        String cookie = request.getHeader("cookie");
        LOGGR.info("cookie===================="+cookie);
        String msg = this.restTemplate.getForObject("http://exercise-product/product/getRibbonProductByID?id=我是9527的请求", String.class);
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("exercise-product");
        // 打印当前那个节点
        UserRibbonController.LOGGR.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
        return msg + "Ribbon是: " + serviceInstance.getPort() + "的响应" + "==========" + url.toString();
    }

}
