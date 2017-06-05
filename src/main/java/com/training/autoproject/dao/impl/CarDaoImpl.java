package com.training.autoproject.dao.impl;

import com.training.autoproject.controller.UsersController;
import com.training.autoproject.dao.CarDao;
import com.training.autoproject.entity.Car;
import com.training.autoproject.service.impl.ApplicationServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Base implementation of
 * {@link com.training.autoproject.dao.CarDao}
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class CarDaoImpl implements CarDao {
    /**
     * Logger for logging class
     */
    private static final Logger log = LogManager.getLogger(CarDaoImpl.class);
    /**
     * EntityManager  for using JPA
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.CarDao}
     *
     * @return list of Car
     */
    @Override
    public List<Car> findCarsByIsActive() {
        log.info("Invoke findCarsByIsActive");
        List<Car> cars = entityManager.createQuery("select c from  Car c where c.isactive=0 ", Car.class).getResultList();
        return cars;
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.CarDao}
     *
     * @return list of Car
     */
    @Override
    public List<Car> findCars() {
        log.info("Invoke findCars");
        List<Car> cars = entityManager.createQuery("select c from Car c ", Car.class).getResultList();
        return cars;
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.CarDao}
     *
     * @param id of Car
     * @return
     */
    @Override
    public Car findCarById(Long id) {
        log.info("Invoke findCarById");
        Car car = entityManager.find(Car.class, id);
        return car;
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.CarDao}
     *
     * @param car
     */
    @Override
    public void updateCarByIsActive(Car car) {
        log.info("Invoke updateCarByIsActive");
        entityManager.merge(car);
    }

}
