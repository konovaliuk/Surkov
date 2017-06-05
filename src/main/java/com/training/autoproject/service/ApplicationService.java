package com.training.autoproject.service;

import com.training.autoproject.entity.Application;
import com.training.autoproject.entity.Car;

import java.util.List;
import java.util.Map;

/**
 * Interface for ApplicationService layer
 *
 * @author Oleh Surkov
 * @version 1.0
 */
public interface ApplicationService {
    /**
     * Method to add Application
     *
     * @param application
     * @param carId
     */
    void addApplication(Application application, Long carId);

    /**
     * Method which gets accessible Cars from database
     *
     * @return list of Cars
     */
    List<Car> getAccessibleCars();


}
