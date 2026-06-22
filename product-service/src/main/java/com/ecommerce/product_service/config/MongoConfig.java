package com.ecommerce.product_service.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final MongoConfigProperties mongoConfigProperties;

    public MongoConfig(MongoConfigProperties mongoConfigProperties) {
        this.mongoConfigProperties = mongoConfigProperties;
    }

    @Override
    protected String getDatabaseName() {
        return "product-db";
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential.createCredential(
            mongoConfigProperties.getUsername(), mongoConfigProperties.getAuthDatabase(),
            mongoConfigProperties.getPassword()
                .toCharArray()
        );

        String connectionString = String.format(
            "mongodb://%s:%s", mongoConfigProperties.getHost(), mongoConfigProperties.getPort());

        MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(connectionString))
            .credential(credential)
            .build();

        return MongoClients.create(settings);
    }

}
