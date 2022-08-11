package com.example.validatingforminput;

import com.mongodb.ConnectionString;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

@Configuration
public class AppConfig {

    @Value("${mongodb.uri}")
    private String mongouri;

//    public @Bean
//    MongoClientFactoryBean mongo() {
//        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
//        mongo.setHost("localhost");
//        return mongo;
//    }

    public @Bean
    MongoClientFactoryBean mongo() {
        String mongoUrlSCRAM = mongouri.replace("<password>", System.getenv("USER_PASS"));
        //System.out.println("logging SCRAM url: " + mongoUrlSCRAM);
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setConnectionString(new ConnectionString(mongoUrlSCRAM));
        return mongo;
    }

}
