package com.training.autoproject.dao;

import com.training.autoproject.entity.Car;

import java.util.List;

/**
 * Interface for CarDao layer
 *
 * @author Oleh Surkov
 * @version 1.0
 */
public interface CarDao {
    /**
     * Method which finds Cars from database where isactive=0
     *
     * @return list of Cars
     */
    List<Car> findCarsByIsActive();

    /**
     * Method which finds all cars from database
     *
     * @return list of Cars
     */
    List<Car> findCars();

    /**
     * Method which finds Car by id
     *
     * @param id of Car
     * @return Car
     */
    Car findCarById(Long id);

    /**
     * Method to update Car
     *
     * @param car
     */
    void updateCarByIsActive(Car car);

    List<Car> findCarsByPage(int number);

}
