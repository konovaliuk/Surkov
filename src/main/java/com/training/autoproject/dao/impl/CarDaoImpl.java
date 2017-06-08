package com.training.autoproject.dao.impl;

import com.training.autoproject.dao.CarDao;
import com.training.autoproject.entity.Car;
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
        return entityManager.createQuery("select c from  Car c where c.isactive=0 ", Car.class).getResultList();
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
        return entityManager.createQuery("select c from Car c ", Car.class).getResultList();
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
        return entityManager.find(Car.class, id);
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

    @Override
    public List<Car> findCarsByPage(int number) {
        return entityManager.createQuery("SELECT c from Car c", Car.class).setFirstResult(number).setMaxResults(4).getResultList();
    }

}
