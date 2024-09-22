package org.lw1base;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BriefDoctor implements IDoctor {
    @JsonProperty("doctorId")
    protected int doctorId;

    @JsonProperty("lastName")
    protected String lastName;

    @JsonProperty("firstName")
    protected String firstName;

    protected BriefDoctor() {}

    public BriefDoctor(@JsonProperty("doctorId") int doctorId,
                       @JsonProperty("lastName") String lastName,
                       @JsonProperty("firstName") String firstName) {
        setDoctorId(doctorId);
        setLastName(lastName);
        setFirstName(firstName);
    }

    @Override
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        DoctorValidator.validateDoctorId(doctorId);
        this.doctorId = doctorId;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        DoctorValidator.validateName(lastName, "Last Name");
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        DoctorValidator.validateName(firstName, "First Name");
        this.firstName = firstName;
    }

    @Override
    public String getInitials() {
        return firstName.charAt(0) + "." + lastName.charAt(0) + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BriefDoctor)) return false;
        BriefDoctor that = (BriefDoctor) o;
        return doctorId == that.doctorId &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, lastName, firstName);
    }

    @Override
    public String toString() {
        return "BriefDoctor{" +
                "doctorId=" + doctorId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", initials='" + getInitials() + '\'' +
                '}';
    }
}