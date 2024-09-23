package org.lw1base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Doctor_rep_json extends AbstractDoctorRepository {
    private final ObjectMapper objectMapper;

    public Doctor_rep_json(String filename) {
        super(filename);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void readFromFile() {
        File file = new File(filename);
        if (file.exists()) {
            try {
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Doctor.class);
                doctors = objectMapper.readValue(file, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void writeToFile() {
        try {
            objectMapper.writeValue(new File(filename), doctors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}