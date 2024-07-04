package com.walmart.entregagl.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author jose ramos
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.walmart.entregagl.springboot.webapp")
public class SpringBootConfig {
    
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SpringBootConfig.class)
                .run();
    }
    
}
