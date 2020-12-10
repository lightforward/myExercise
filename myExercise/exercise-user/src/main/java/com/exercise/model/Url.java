package com.exercise.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Data
//@RefreshScope
@Component
@ConfigurationProperties(prefix="url")
public class Url implements Serializable {

    private String url1;

    private String url2;

    private String url3;

    private String url4;

    private String url5;

    private String url6;
}