package com.consul.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by bandhu on 4/12/20.
 */
@ConfigurationProperties()
@Data
public class MyConfiguration {

    private String username;
    private String password;

}
