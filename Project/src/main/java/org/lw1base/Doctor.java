package org.lw1base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class Doctor extends BriefDoctor {

    @JsonProperty("qualification")
    private int qualification;

    @JsonProperty("specialty")
    private String specialty;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Doctor(Builder builder) {
        super(builder.doctorId.orElse(0), builder.lastName, builder.firstName, builder.middleName);
        this.qualification = builder.qualification;
        this.specialty = builder.specialty;
    }

    public String getMiddleName() {
        return middleName;
    }

    public int getQualification() {
        return qualification;
    }

    public String getSpecialty() {
        return specialty;
    }

    @Override
    public String getInitials() {
        return firstName.charAt(0) + "." + middleName.charAt(0) + "." + lastName.charAt(0) + ".";
    }

    public static Doctor createNewDoctor(String lastName, String firstName, String middleName, int qualification, String specialty) {
        return new Builder()
                .lastName(lastName)
                .firstName(firstName)
                .middleName(middleName)
                .qualification(qualification)
                .specialty(specialty)
                .build();
    }

    public static Doctor updateExistingDoctor(int doctorId, String lastName, String firstName, String middleName, int qualification, String specialty) {
        return new Builder()
                .doctorId(doctorId)
                .lastName(lastName)
                .firstName(firstName)
                .middleName(middleName)
                .qualification(qualification)
                .specialty(specialty)
                .build();
    }

    public static Doctor createFromString(String doctorString) {
        String[] parts = doctorString.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Неверный формат строки врача. Ожидается 6 значений, разделенных запятыми.");
        }
        try {
            return new Builder()
                    .doctorId(Integer.parseInt(parts[0].trim()))
                    .lastName(parts[1].trim())
                    .firstName(parts[2].trim())
                    .middleName(parts[3].trim())
                    .qualification(Integer.parseInt(parts[4].trim()))
                    .specialty(parts[5].trim())
                    .build();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат числа в строке врача.", e);
        }
    }

    public static Doctor createFromJson(String json) throws IOException {
        return objectMapper.readValue(json, Doctor.class);
    }

    public String toJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

    public static class Builder {
        private Optional<Integer> doctorId = Optional.empty();
        private String lastName;
        private String firstName;
        private String middleName;
        private int qualification;
        private String specialty;

        public Builder doctorId(int doctorId) {
            this.doctorId = Optional.of(doctorId);
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder qualification(int qualification) {
            this.qualification = qualification;
            return this;
        }

        public Builder specialty(String specialty) {
            this.specialty = specialty;
            return this;
        }

        public Doctor build() {
            Doctor doctor = new Doctor(this);
            DoctorValidator.validateDoctor(doctor);
            return doctor;
        }
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", qualification=" + qualification +
                ", specialty='" + specialty + '\'' +
                ", initials='" + getInitials() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return qualification == doctor.qualification &&
                Objects.equals(middleName, doctor.middleName) &&
                Objects.equals(specialty, doctor.specialty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), middleName, qualification, specialty);
    }

    public boolean isSameBriefDoctor(BriefDoctor other) {
        return super.equals(other);
    }
}