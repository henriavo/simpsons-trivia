package com.example.validatingforminput;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;


@SpringBootApplication
public class TriviaApplication {
	private static final Log log = LogFactory.getLog(TriviaApplication.class);

	public static void main(String[] args) {
		log.info("hello world");
		SpringApplication.run(TriviaApplication.class, args);
	}

}
