package com.consul.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.logging.Logger;

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
@RestController
@EnableConfigurationProperties(value = MyConfiguration.class)
public class Application {

    @Value("${testkey}")
    String env;

    @Value("${spring.application.name}")
    String name;

    @Autowired
    private MyConfiguration myConfiguration;

    @GetMapping("/getConfig")
    public MyConfiguration getMyConfiguration(){
        return myConfiguration;
    }

    private Environment environment;

    Application(Environment environment) {
        this.environment = environment;
    }

    private static final Logger logger = Logger.getLogger("app");

    @RequestMapping("/")
    public String home() {
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println(String.format("active profiles: {%s}", Arrays.toString(activeProfiles)));
        return "Hello World: " + env + " : " + name;
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
