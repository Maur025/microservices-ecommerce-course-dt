package com.ecommerce.product_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mongodb-config")
@Getter
@Setter
public class MongoConfigProperties {

    private String username;
    private String authDatabase;
    private String password;
    private String host;
    private Integer port;
}
