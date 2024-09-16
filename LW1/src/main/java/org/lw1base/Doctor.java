package org.lw1base;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.regex.Pattern;

public class Doctor {
    @JsonProperty("doctorId")
    protected final int doctorId;

    @JsonProperty("lastName")
    protected final String lastName;

    @JsonProperty("firstName")
    protected final String firstName;

    @JsonProperty("middleName")
    protected final String middleName;

    @JsonProperty("qualification")
    protected final int qualification;

    @JsonProperty("specialtyId")
    protected final int specialtyId;

    protected static final int MIN_QUALIFICATION = 1;
    protected static final int MAX_QUALIFICATION = 5;
    protected static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-zА-Яа-я-]+$");
    protected static final ObjectMapper objectMapper = new ObjectMapper();

    protected Doctor(int doctorId, String lastName, String firstName, String middleName, int qualification, int specialtyId) {
        this.doctorId = doctorId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.qualification = qualification;
        this.specialtyId = specialtyId;
    }

    public static Doctor createFromRaw(int doctorId, String lastName, String firstName, String middleName, int qualification, int specialtyId) {
        validateDoctorId(doctorId);
        validateName(lastName, "Last name");
        validateName(firstName, "First name");
        validateName(middleName, "Middle name");
        validateQualification(qualification);
        validateSpecialtyId(specialtyId);

        return new Doctor(doctorId, lastName, firstName, middleName, qualification, specialtyId);
    }

    public static Doctor createFromString(String doctorString) {
        String[] parts = doctorString.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid doctor string format. Expected 6 comma-separated values.");
        }

        try {
            int doctorId = Integer.parseInt(parts[0].trim());
            String lastName = parts[1].trim();
            String firstName = parts[2].trim();
            String middleName = parts[3].trim();
            int qualification = Integer.parseInt(parts[4].trim());
            int specialtyId = Integer.parseInt(parts[5].trim());

            return createFromRaw(doctorId, lastName, firstName, middleName, qualification, specialtyId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in doctor string", e);
        }
    }

    public static Doctor createFromJson(String json) throws IOException {
        Doctor doctor = objectMapper.readValue(json, Doctor.class);
        validateDoctor(doctor);
        return doctor;
    }

    protected static void validateDoctor(Doctor doctor) {
        validateDoctorId(doctor.doctorId);
        validateName(doctor.lastName, "Last name");
        validateName(doctor.firstName, "First name");
        validateName(doctor.middleName, "Middle name");
        validateQualification(doctor.qualification);
        validateSpecialtyId(doctor.specialtyId);
    }

    protected static void validateDoctorId(int doctorId) {
        if (doctorId <= 0) {
            throw new IllegalArgumentException("Doctor ID must be a positive integer");
        }
    }

    protected static void validateName(String name, String fieldName) {
        if (name == null || name.isEmpty() || !NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(fieldName + " must contain only letters, hyphens, and be non-empty");
        }
    }

    protected static void validateQualification(int qualification) {
        if (qualification < MIN_QUALIFICATION || qualification > MAX_QUALIFICATION) {
            throw new IllegalArgumentException("Qualification must be between " + MIN_QUALIFICATION + " and " + MAX_QUALIFICATION);
        }
    }

    protected static void validateSpecialtyId(int specialtyId) {
        if (specialtyId <= 0) {
            throw new IllegalArgumentException("Specialty ID must be a positive integer");
        }
    }

    // Геттеры
    public int getDoctorId() { return doctorId; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public int getQualification() { return qualification; }
    public int getSpecialtyId() { return specialtyId; }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", qualification=" + qualification +
                ", specialtyId=" + specialtyId +
                '}';
    }

    public String toJson() throws IOException {
        return objectMapper.writeValueAsString(this);
    }
}
