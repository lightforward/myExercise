package com.exercise;

import com.exercise.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableZuulProxy
public class ExerciseZuulApplication {

    //将过滤器交给Spring管理
    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(ExerciseZuulApplication.class, args);
    }

}

