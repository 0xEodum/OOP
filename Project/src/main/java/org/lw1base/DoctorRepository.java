package org.lw1base;

public class DoctorRepository extends AbstractDoctorRepository {
    public DoctorRepository(String filename, ISerializationStrategy strategy) {
        super(filename, strategy);
    }
}
