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
	private static String uri = "mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+1.4.2";

	public static void main(String[] args) {
		try (MongoClient mongoClient = MongoClients.create(uri)) {
			MongoDatabase database = mongoClient.getDatabase("sample_training");
			MongoCollection<Document> collection = database.getCollection("grades");

			try {
				InsertOneResult result = collection.insertOne(new Document()
//						.append("_id", new ObjectId())
						.append("name", "Henri")
						.append("classes", Arrays.asList("math 101", "circuits 211")));
				System.out.println("Success! Inserted document id: " + result.getInsertedId());
			} catch (MongoException me) {
				System.err.println("Unable to insert due to an error: " + me);
			}

		}

		SpringApplication.run(TriviaApplication.class, args);
	}

}
