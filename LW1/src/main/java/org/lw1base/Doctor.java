package org.lw1base;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.Objects;
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
    protected static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z-]+$");
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
        validateName(lastName, "Отчетсов");
        validateName(firstName, "Имя");
        validateName(middleName, "Фамилия");
        validateQualification(qualification);
        validateSpecialtyId(specialtyId);

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
        validateDoctor(doctor);
        return doctor;
    }

    protected static void validateDoctor(Doctor doctor) {
        validateDoctorId(doctor.doctorId);
        validateName(doctor.lastName, "Отчество");
        validateName(doctor.firstName, "Имя");
        validateName(doctor.middleName, "Фамилия");
        validateQualification(doctor.qualification);
        validateSpecialtyId(doctor.specialtyId);
    }

    protected static void validateDoctorId(int doctorId) {
        if (doctorId <= 0) {
            throw new IllegalArgumentException("ID врача должно быть целым положительным числом");
        }
    }

    protected static void validateName(String name, String fieldName) {
        if (name == null || name.isEmpty() || !NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(fieldName + " должно содержать только буквы и дефисы и быть непустым");
        }
    }

    protected static void validateQualification(int qualification) {
        if (qualification < MIN_QUALIFICATION || qualification > MAX_QUALIFICATION) {
            throw new IllegalArgumentException("Квалификация должна находиться между " + MIN_QUALIFICATION + " и " + MAX_QUALIFICATION);
        }
    }

    protected static void validateSpecialtyId(int specialtyId) {
        if (specialtyId <= 0) {
            throw new IllegalArgumentException("ID специальности должно быть целым положительным числом");
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

    public String toJson() throws IOException {
        return objectMapper.writeValueAsString(this);
    }
}
