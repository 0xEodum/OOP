package org.lw1base;

import java.util.List;

public class DoctorRepositoryDBAdapter implements IDoctorRepository {
    private final Doctor_rep_DB dbRepository;

    public DoctorRepositoryDBAdapter() {
        this.dbRepository = new Doctor_rep_DB();
    }

    @Override
    public Doctor getById(int id) {
        return dbRepository.getById(id);
    }

    @Override
    public List<BriefDoctor> get_k_n_short_list(int k, int n) {
        return dbRepository.get_k_n_short_list(k, n);
    }

    @Override
    public void sortByField(String fieldName) {
        throw new UnsupportedOperationException("Sorting is not implemented for database repository");
    }

    @Override
    public void addDoctor(Doctor doctor) {
        dbRepository.addDoctor(doctor);
    }

    @Override
    public void replaceDoctor(int id, Doctor newDoctor) {
        dbRepository.replaceDoctor(id, newDoctor);
    }

    @Override
    public void deleteDoctor(int id) {
        dbRepository.deleteDoctor(id);
    }

    @Override
    public int get_count() {
        return (int) dbRepository.get_count();
    }
}
