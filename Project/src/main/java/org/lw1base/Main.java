package org.lw1base;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Doctor newDoctor = Doctor.createNewDoctor("Smith", "John", "Robert", 5, "Cardiology");
            System.out.println(newDoctor);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}