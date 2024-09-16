package org.lw1base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.Objects;

public class Doctor {
    @JsonProperty("doctorId")
    private final int doctorId;

    @JsonProperty("lastName")
    private final String lastName;

    @JsonProperty("firstName")
    private final String firstName;

    @JsonProperty("middleName")
    private final String middleName;

    @JsonProperty("qualification")
    private final int qualification;

    @JsonProperty("specialtyId")
    private final int specialtyId;

    private static final int MIN_QUALIFICATION = 1;
    private static final int MAX_QUALIFICATION = 5;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    protected Doctor(int doctorId, String lastName, String firstName, String middleName, int qualification, int specialtyId) {
        this.doctorId = doctorId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.qualification = qualification;
        this.specialtyId = specialtyId;
    }

    public static Doctor createFromRaw(int doctorId, String lastName, String firstName, String middleName, int qualification, int specialtyId) {
        DoctorValidator.validateDoctorId(doctorId);
        DoctorValidator.validateName(lastName, "Last Name");
        DoctorValidator.validateName(firstName, "First Name");
        DoctorValidator.validateName(middleName, "Middle Name");
        DoctorValidator.validateQualification(qualification, MIN_QUALIFICATION, MAX_QUALIFICATION);
        DoctorValidator.validateSpecialtyId(specialtyId);
        return new Doctor(doctorId, lastName, firstName, middleName, qualification, specialtyId);
    }

    public static Doctor createFromString(String doctorString) {
        String[] parts = doctorString.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Неверный формат строки врача. Ожидается 6 значений, разделенных запятыми.");
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
            throw new IllegalArgumentException("Неверный формат числа в строке врача.", e);
        }
    }

    public static Doctor createFromJson(String json) throws IOException {
        Doctor doctor = objectMapper.readValue(json, Doctor.class);
        DoctorValidator.validateDoctor(doctor);
        return doctor;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public int getQualification() {
        return qualification;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public String toJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return doctorId == doctor.doctorId &&
                qualification == doctor.qualification &&
                specialtyId == doctor.specialtyId &&
                Objects.equals(lastName, doctor.lastName) &&
                Objects.equals(firstName, doctor.firstName) &&
                Objects.equals(middleName, doctor.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, lastName, firstName, middleName, qualification, specialtyId);
    }
}
