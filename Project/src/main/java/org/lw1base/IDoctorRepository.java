package org.lw1base;

import java.util.List;

public interface IDoctorRepository {
    Doctor getById(int id);
    List<BriefDoctor> get_k_n_short_list(int k, int n);
    void sortByField(String fieldName);
    void addDoctor(Doctor doctor);
    void replaceDoctor(int id, Doctor newDoctor);
    void deleteDoctor(int id);
    int get_count();
}
