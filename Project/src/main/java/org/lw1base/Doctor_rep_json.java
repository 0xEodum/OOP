package org.lw1base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Doctor_rep_json {
    private List<Doctor> doctors;
    private final String filename;
    private final ObjectMapper objectMapper;

    public Doctor_rep_json(String filename) {
        this.filename = filename;
        this.objectMapper = new ObjectMapper();
        this.doctors = new ArrayList<>();
        readFromFile();
    }

    // a. Чтение всех значений из файла
    private void readFromFile() {
        File file = new File(filename);
        if (file.exists()) {
            try {
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Doctor.class);
                doctors = objectMapper.readValue(file, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // b. Запись всех значений в файл
    public void writeToFile() {
        try {
            objectMapper.writeValue(new File(filename), doctors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // c. Получить объект по ID
    public Doctor getById(int id) {
        return doctors.stream()
                .filter(doctor -> doctor.getDoctorId() == id)
                .findFirst()
                .orElse(null);
    }

    // d. Получить список k по счету n объектов класса short
    public List<BriefDoctor> get_k_n_short_list(int k, int n) {
        return doctors.stream()
                .skip((long) (k - 1) * n)
                .limit(n)
                .map(doctor -> new BriefDoctor(doctor.getDoctorId(), doctor.getLastName(), doctor.getFirstName()))
                .collect(Collectors.toList());
    }

    // e. Сортировать элементы по выбранному полю
    public void sortByField(String fieldName) {
        Comparator<Doctor> comparator = switch (fieldName) {
            case "lastName" -> Comparator.comparing(Doctor::getLastName);
            case "firstName" -> Comparator.comparing(Doctor::getFirstName);
            case "qualification" -> Comparator.comparingInt(Doctor::getQualification);
            case "specialty" -> Comparator.comparing(Doctor::getSpecialty);
            default -> throw new IllegalArgumentException("Invalid field name: " + fieldName);
        };
        doctors.sort(comparator);
    }

    // f. Добавить объект в список
    public void addDoctor(Doctor doctor) {
        int newId = generateNewId();
        Doctor newDoctor = Doctor.createNewDoctor(
                doctor.getLastName(),
                doctor.getFirstName(),
                doctor.getMiddleName(),
                doctor.getQualification(),
                doctor.getSpecialty()
        );
        doctors.add(new Doctor.Builder()
                .doctorId(newId)
                .lastName(newDoctor.getLastName())
                .firstName(newDoctor.getFirstName())
                .middleName(newDoctor.getMiddleName())
                .qualification(newDoctor.getQualification())
                .specialty(newDoctor.getSpecialty())
                .build());
    }

    private int generateNewId() {
        return doctors.stream()
                .mapToInt(Doctor::getDoctorId)
                .max()
                .orElse(0) + 1;
    }

    // g. Заменить элемент списка по ID
    public void replaceDoctor(int id, Doctor newDoctor) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getDoctorId() == id) {
                doctors.set(i, newDoctor);
                return;
            }
        }
        throw new IllegalArgumentException("Doctor with id " + id + " not found");
    }

    // h. Удалить элемент списка по ID
    public void deleteDoctor(int id) {
        doctors.removeIf(doctor -> doctor.getDoctorId() == id);
    }

    // i. Получить количество элементов
    public int get_count() {
        return doctors.size();
    }
}
