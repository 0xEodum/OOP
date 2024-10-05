package org.lw1base;


public class Main {
    public static void main(String[] args) {
//        IDoctorRepository dbRepo = new DoctorRepositoryDBAdapter();
//
//        Doctor doctor1 = Doctor.createNewDoctor("Johnson", "Emily", "Rose", 5, "Neurologist");
//        Doctor doctor2 = Doctor.createNewDoctor("Williams", "Michael", "David", 3, "Pediatrician");
//
//        dbRepo.addDoctor(doctor1);
//        dbRepo.addDoctor(doctor2);


        BriefDoctor bd1 = new BriefDoctor(1, "Valeriy", "Sergeevich", "Korochkin");
        BriefDoctor bd2 = new BriefDoctor(2, "Dmitriy", "Evgenievich", "Alexandrov");
        System.out.println(bd1);
        System.out.println(bd2);
    }
}