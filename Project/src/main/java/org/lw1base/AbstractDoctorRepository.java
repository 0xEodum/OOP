package org.lw1base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDoctorRepository implements IDoctorRepository {
    protected List<Doctor> doctors;
    protected final String filename;
    protected ISerializationStrategy serializationStrategy;

    public AbstractDoctorRepository(String filename, ISerializationStrategy strategy) {
        this.filename = filename;
        this.serializationStrategy = strategy;
        this.doctors = new ArrayList<>();
        readFromFile();
    }

    public void readFromFile() {
        this.doctors = serializationStrategy.readFromFile(filename);
    }

    public void writeToFile() {
        serializationStrategy.writeToFile(filename, doctors);
    }

    @Override
    public Doctor getById(int id) {
        return doctors.stream()
                .filter(doctor -> doctor.getDoctorId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<BriefDoctor> get_k_n_short_list(int k, int n) {
        return doctors.stream()
                .skip((long) (k - 1) * n)
                .limit(n)
                .map(doctor -> new BriefDoctor(doctor.getDoctorId(), doctor.getLastName(), doctor.getFirstName(), doctor.getMiddleName()))
                .collect(Collectors.toList());
    }

    @Override
    public void sortByField(String fieldName) {
        Comparator<Doctor> comparator = switch (fieldName) {
            case "lastName" -> Comparator.comparing(Doctor::getLastName);
            case "firstName" -> Comparator.comparing(Doctor::getFirstName);
            case "qualification" -> Comparator.comparingInt(Doctor::getQualification);
            case "specialty" -> Comparator.comparing(Doctor::getSpecialty);
            default -> throw new IllegalArgumentException("Invalid field name: " + fieldName);
        };
        doctors.sort(comparator);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        int newId = generateNewId();
        doctors.add(new Doctor.Builder()
                .doctorId(newId)
                .lastName(doctor.getLastName())
                .firstName(doctor.getFirstName())
                .middleName(doctor.getMiddleName())
                .qualification(doctor.getQualification())
                .specialty(doctor.getSpecialty())
                .build());
    }

    protected int generateNewId() {
        return doctors.stream()
                .mapToInt(Doctor::getDoctorId)
                .max()
                .orElse(0) + 1;
    }

    @Override
    public void replaceDoctor(int id, Doctor newDoctor) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getDoctorId() == id) {
                doctors.set(i, newDoctor);
                return;
            }
        }
        throw new IllegalArgumentException("Doctor with id " + id + " not found");
    }

    @Override
    public void deleteDoctor(int id) {
        doctors.removeIf(doctor -> doctor.getDoctorId() == id);
    }

    @Override
    public int get_count() {
        return doctors.size();
    }


}