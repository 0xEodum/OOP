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
}
