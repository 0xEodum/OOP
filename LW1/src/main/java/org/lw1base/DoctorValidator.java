package org.lw1base;


import java.util.regex.Pattern;

public class DoctorValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[^\\\\d\\\\s]+$", Pattern.UNICODE_CHARACTER_CLASS);

    public static void validateDoctorId(int doctorId) {
        if (doctorId <= 0) {
            throw new IllegalArgumentException("ID врача должно быть целым положительным числом.");
        }
    }

    public static void validateName(String name, String fieldName) {
        if (name == null || name.isEmpty() || !NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(fieldName + " должно содержать только буквы и дефисы и быть непустым");
        }
    }

    public static void validateQualification(int qualification, int minQualification, int maxQualification) {
        if (qualification < minQualification || qualification > maxQualification) {
            throw new IllegalArgumentException("Квалификация должна находиться между " + minQualification + " и " + maxQualification);
        }
    }

    public static void validateSpecialtyId(int specialtyId) {
        if (specialtyId <= 0) {
            throw new IllegalArgumentException("ID специальности должно быть целым положительным числом.");
        }
    }

    public static void validateDoctor(Doctor doctor) {
        validateDoctorId(doctor.getDoctorId());
        validateDoctorId(doctor.getDoctorId());
        validateName(doctor.getLastName(), "Last Name");
        validateName(doctor.getFirstName(), "First Name");
        validateName(doctor.getMiddleName(), "Middle Name");
        validateQualification(doctor.getQualification(), 1, 5);
        validateSpecialtyId(doctor.getSpecialtyId());
    }
}

