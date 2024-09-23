package org.lw1base;

import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        IDoctorRepository dbRepo = new DoctorRepositoryDBAdapter();

        Doctor doctor1 = Doctor.createNewDoctor("Johnson", "Emily", "Rose", 5, "Neurologist");
        Doctor doctor2 = Doctor.createNewDoctor("Williams", "Michael", "David", 3, "Pediatrician");

        dbRepo.addDoctor(doctor1);
        dbRepo.addDoctor(doctor2);
    }
}