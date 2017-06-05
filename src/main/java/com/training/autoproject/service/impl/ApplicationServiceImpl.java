package com.training.autoproject.service.impl;

import com.training.autoproject.dao.ApplicationDao;
import com.training.autoproject.dao.CarDao;
import com.training.autoproject.dao.impl.ApplicationDaoImpl;
import com.training.autoproject.entity.Application;
import com.training.autoproject.entity.Car;
import com.training.autoproject.service.ApplicationService;
import com.training.autoproject.util.MailUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Base implementation of
 * {@link com.training.autoproject.service.ApplicationService}
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {
    /**
     * Logger for logging class
     */
    private static final Logger log = LogManager.getLogger(ApplicationServiceImpl.class);
    /**
     * Constant for inner using
     */
    private static final int NOT_CLOSED = 1;
    /**
     * field for injecting realization of {@link com.training.autoproject.dao.CarDao}
     */
    @Autowired
    CarDao carDao;
    /**
     * field for injecting realization of {@link com.training.autoproject.dao.ApplicationDao}
     */
    @Autowired
    ApplicationDao applicationDao;
    /**
     * field for injecting realization of {@link com.training.autoproject.util.MailUtil}
     */
    @Autowired
    MailUtil mailUtil;

    /**
     * Implementation method from
     * {@link com.training.autoproject.service.ApplicationService}
     *
     * @param application
     * @param carId
     */
    @Transactional
    @Override
    public void addApplication(Application application, Long carId) {
        log.info("invoke addApplication");
        mailUtil.sendMail(application.getEmail());
        Car car = carDao.findCarById(carId);
        application.setIsclosed(NOT_CLOSED);
        application.setCarByCarId(car);
        applicationDao.addApplication(application);
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.service.ApplicationService}
     *
     * @return list of Cars
     */
    @Transactional(readOnly = true)
    @Override
    public List<Car> getAccessibleCars() {
        log.info("invoke getAccessibleCars");
        return carDao.findCarsByIsActive();
    }
}
