package org.lw1base;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Doctor_rep_json extends AbstractDoctorRepository {
    public Doctor_rep_json(String filename) {
        super(filename);
    }

    @Override
    protected ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }
}