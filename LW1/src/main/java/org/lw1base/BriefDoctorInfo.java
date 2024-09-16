package org.lw1base;

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
        return getFirstName().charAt(0) + "." + getMiddleName().charAt(0) + ".";
    }

    @Override
    public String toString() {
        return "BriefDoctorInfo{" +
                "doctorId=" + getDoctorId() +
                ", lastName='" + getLastName() + '\'' +
                ", initials='" + getInitials() + '\'' +
                ", qualification=" + getQualification() +
                ", specialtyId=" + getSpecialtyId() +
                '}';
    }
}
