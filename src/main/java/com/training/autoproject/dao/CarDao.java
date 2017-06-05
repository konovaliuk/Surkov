package com.training.autoproject.dao;

import com.training.autoproject.entity.Car;

import java.util.List;

/**
 * Created by Oleg on 10.05.2017.
 */
public interface CarDao {
    List<Car> findCarsByIsActive();

    List<Car> findCars();

    Car findCarById(Long id);

    void updateCarByIsActive(Car car);

}
