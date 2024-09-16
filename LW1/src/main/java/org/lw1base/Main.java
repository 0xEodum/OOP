package org.lw1base;

public class Main {
    public static void main(String[] args) {
        try {
            Doctor doctor1 = Doctor.createFromRaw(1, "Ivanov", "Иван", "Ivanovich", 3, 1);
            Doctor doctor2 = Doctor.createFromRaw(1, "Ivanov", "Ivan", "Ivanovich", 3, 1);
            Doctor doctor3 = Doctor.createFromRaw(2, "Petrov", "Petr", "Petrovich", 4, 2);

            System.out.println("doctor1 равен doctor2: " + doctor1.equals(doctor2));
            System.out.println("doctor1 равен doctor3: " + doctor1.equals(doctor3));

            BriefDoctorInfo briefDoctor1 = BriefDoctorInfo.createFromDoctor(doctor1);
            BriefDoctorInfo briefDoctor2 = BriefDoctorInfo.createFromDoctor(doctor2);
            BriefDoctorInfo briefDoctor3 = BriefDoctorInfo.createFromDoctor(doctor3);

            System.out.println("briefDoctor1 равен briefDoctor2: " + briefDoctor1.equals(briefDoctor2));
            System.out.println("briefDoctor1 равен briefDoctor3: " + briefDoctor1.equals(briefDoctor3));

            System.out.println("doctor1 равен briefDoctor1: " + doctor1.equals(briefDoctor1));
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}