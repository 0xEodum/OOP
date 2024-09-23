package org.lw1base;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class Doctor_rep_DB {
    private final MongoCollection<Document> collection;

    public Doctor_rep_DB() {
        MongoDBConnection connection = MongoDBConnection.getInstance();
        collection = connection.getDatabase().getCollection("doctors");
    }

    public Doctor getById(int id) {
        Document doc = collection.find(Filters.eq("doctorId", id)).first();
        return doc != null ? documentToDoctor(doc) : null;
    }

    public List<BriefDoctor> get_k_n_short_list(int k, int n) {
        List<BriefDoctor> result = new ArrayList<>();
        collection.find()
                .skip((k - 1) * n)
                .limit(n)
                .forEach(doc -> result.add(documentToBriefDoctor(doc)));
        return result;
    }

    public void addDoctor(Doctor doctor) {
        collection.insertOne(doctorToDocument(doctor));
    }

    public void replaceDoctor(int id, Doctor newDoctor) {
        collection.replaceOne(Filters.eq("doctorId", id), doctorToDocument(newDoctor));
    }

    public void deleteDoctor(int id) {
        collection.deleteOne(Filters.eq("doctorId", id));
    }

    public long get_count() {
        return collection.countDocuments();
    }

    private Doctor documentToDoctor(Document doc) {
        return new Doctor.Builder()
                .doctorId(doc.getInteger("doctorId"))
                .lastName(doc.getString("lastName"))
                .firstName(doc.getString("firstName"))
                .middleName(doc.getString("middleName"))
                .qualification(doc.getInteger("qualification"))
                .specialty(doc.getString("specialty"))
                .build();
    }

    private BriefDoctor documentToBriefDoctor(Document doc) {
        return new BriefDoctor(
                doc.getInteger("doctorId"),
                doc.getString("lastName"),
                doc.getString("firstName")
        );
    }

    private Document doctorToDocument(Doctor doctor) {
        return new Document("doctorId", doctor.getDoctorId())
                .append("lastName", doctor.getLastName())
                .append("firstName", doctor.getFirstName())
                .append("middleName", doctor.getMiddleName())
                .append("qualification", doctor.getQualification())
                .append("specialty", doctor.getSpecialty());
    }
}
