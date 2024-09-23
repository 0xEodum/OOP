package org.lw1base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class Doctor_rep_yaml extends AbstractDoctorRepository {
    public Doctor_rep_yaml(String filename) {
        super(filename);
    }

    @Override
    protected ObjectMapper createObjectMapper() {
        return new ObjectMapper(new YAMLFactory());
    }
}