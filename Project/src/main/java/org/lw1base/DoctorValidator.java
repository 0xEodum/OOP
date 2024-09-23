package org.lw1base;


import java.util.regex.Pattern;

import java.util.regex.Pattern;

public class DoctorValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[^\\d\\s]+$", Pattern.UNICODE_CHARACTER_CLASS);

    public static void validateDoctorId(int doctorId) {
        if (doctorId < 0) {
            throw new IllegalArgumentException("ID врача должно быть неотрицательным целым числом.");
        }
    }

    public static void validateName(String name, String fieldName) {
        if (name == null || name.isEmpty() || !NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(fieldName + " должно содержать только буквы и дефисы и быть непустым");
        }
    }

    public static void validateQualification(int qualification) {
        if (qualification < 1 || qualification > 5) {
            throw new IllegalArgumentException("Квалификация должна находиться между 1 и 5");
        }
    }

    public static void validateSpecialty(String specialty) {
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new IllegalArgumentException("Специальность не может быть пустой");
        }
    }

    public static void validateDoctor(Doctor doctor) {
        if (doctor.getDoctorId() != 0) {
            validateDoctorId(doctor.getDoctorId());
        }
        validateName(doctor.getLastName(), "Last Name");
        validateName(doctor.getFirstName(), "First Name");
        validateName(doctor.getMiddleName(), "Middle Name");
        validateQualification(doctor.getQualification());
        validateSpecialty(doctor.getSpecialty());
    }
}

