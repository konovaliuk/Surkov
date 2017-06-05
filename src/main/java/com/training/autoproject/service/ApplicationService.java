package com.training.autoproject.service;

import com.training.autoproject.entity.Application;
import com.training.autoproject.entity.Car;

import java.util.List;
import java.util.Map;

/**
 * Created by Oleg on 16-May-17.
 */
public interface ApplicationService {

    void addApplication(Application application, Long carId);

    List<Car> getAccessibleCars();


}
