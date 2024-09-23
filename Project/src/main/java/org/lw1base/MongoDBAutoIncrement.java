package org.lw1base;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import org.bson.Document;

public class MongoDBAutoIncrement {
    private final MongoCollection<Document> countersCollection;

    public MongoDBAutoIncrement(MongoDBConnection connection) {
        this.countersCollection = connection.getDatabase().getCollection("counters");
    }

    public int getNextSequence(String sequenceName) {
        Document filter = new Document("_id", sequenceName);
        Document update = new Document("$inc", new Document("seq", 1));
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions()
                .returnDocument(ReturnDocument.AFTER)
                .upsert(true);

        Document result = countersCollection.findOneAndUpdate(filter, update, options);
        return result.getInteger("seq");
    }
}