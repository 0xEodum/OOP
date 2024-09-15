package org.lw1base;

import java.util.regex.Pattern;

public class Doctor {
    private final int doctorId;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final int qualification;
    private final int specialtyId;

    private static final int MIN_QUALIFICATION = 1;
    private static final int MAX_QUALIFICATION = 5;
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-zА-Яа-я-]+$");

    private Doctor(int doctorId, String lastName, String firstName, String middleName, int qualification, int specialtyId) {
        this.doctorId = doctorId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.qualification = qualification;
        this.specialtyId = specialtyId;
    }

    public static Doctor createDoctor(int doctorId, String lastName, String firstName, String middleName, int qualification, int specialtyId) {
        validateDoctorId(doctorId);
        validateName(lastName, "Last name");
        validateName(firstName, "First name");
        validateName(middleName, "Middle name");
        validateQualification(qualification);
        validateSpecialtyId(specialtyId);

        return new Doctor(doctorId, lastName, firstName, middleName, qualification, specialtyId);
    }

    private static void validateDoctorId(int doctorId) {
        if (doctorId <= 0) {
            throw new IllegalArgumentException("ID врача должно быть целым положительным числом");
        }
    }

    private static void validateName(String name, String fieldName) {
        if (name == null || name.isEmpty() || !NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(fieldName + " должно включать только буквы, дефисы и быть не пустым");
        }
    }

    private static void validateQualification(int qualification) {
        if (qualification < MIN_QUALIFICATION || qualification > MAX_QUALIFICATION) {
            throw new IllegalArgumentException("Квалификация должна быть между " + MIN_QUALIFICATION + " и " + MAX_QUALIFICATION);
        }
    }

    private static void validateSpecialtyId(int specialtyId) {
        if (specialtyId <= 0) {
            throw new IllegalArgumentException("ID специальности должно быть целым положительным числом");
        }
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
}
