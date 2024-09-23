package org.lw1base;

import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MongoDBConnection connection = MongoDBConnection.getInstance();
        MongoDatabase database = connection.getDatabase();


        if (!database.listCollectionNames().into(new ArrayList<>()).contains("doctors")) {
            database.createCollection("doctors");
        }
    }
}