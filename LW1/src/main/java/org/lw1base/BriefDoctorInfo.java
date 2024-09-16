package org.lw1base;

import java.io.IOException;

public class BriefDoctorInfo extends Doctor {
    private BriefDoctorInfo(int doctorId, String lastName, String firstName, String middleName, int qualification, int specialtyId) {
        super(doctorId, lastName, firstName, middleName, qualification, specialtyId);
    }

    public static BriefDoctorInfo createFromDoctor(Doctor doctor) {
        return new BriefDoctorInfo(
                doctor.getDoctorId(),
                doctor.getLastName(),
                doctor.getFirstName(),
                doctor.getMiddleName(),
                doctor.getQualification(),
                doctor.getSpecialtyId()
        );
    }

    public String getInitials() {
        return firstName.charAt(0) + "." + middleName.charAt(0) + ".";
    }

    @Override
    public String toString() {
        return "BriefDoctorInfo{" +
                "doctorId=" + doctorId +
                ", lastName='" + lastName + '\'' +
                ", initials='" + getInitials() + '\'' +
                ", qualification=" + qualification +
                ", specialtyId=" + specialtyId +
                '}';
    }
}
