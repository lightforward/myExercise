package com.example.exerciseproduct.controller;

import com.example.exerciseproduct.model.Product;
import com.example.exerciseproduct.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger LOGGR = LoggerFactory.getLogger(ProductController.class);

    /**
     * Ribbon负载均衡调用
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getRibbonProductByID")
    public String getRibbonProductByID(@RequestParam(value = "id") String id, HttpServletRequest request) {
        //会发现token值能够获取，cookie无法获取，原因是因为网关会过滤掉敏感词
        String cookie = request.getHeader("cookie");
        LOGGR.info("cookie--------------------------------"+cookie);
        Product product = new Product();
        product.setId(id);
        product.setName("苹果");
        product.setPrice(new BigDecimal(100));
        return product.toString();
    }

    /**
     *Feign负载均衡调用
     *
     * @return
     */
    @GetMapping(value = "/getFeignProductByID")
    public String getFeignProductByID(@RequestParam(value = "id") String id) {
        Product product = new Product();
        product.setId(id);
        product.setName("华为");
        product.setPrice(new BigDecimal(200));

        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objectNames = null;
        String port = "" ;
        try {
            objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
            port = objectNames.iterator().next().getKeyProperty("port");
            LOGGR.info("当前服务端口为==========================" + port);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }

        return product.toString() + "Feign服务端口: " + port;
    }

}
