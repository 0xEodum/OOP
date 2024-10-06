package org.lw1base;

import java.util.List;

public interface ISerializationStrategy {
    List<Doctor> readFromFile(String filename);
    void writeToFile(String filename, List<Doctor> doctors);
}
