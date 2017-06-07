package com.training.autoproject.service;

import com.training.autoproject.AbstractTestConfig;
import com.training.autoproject.dao.*;
import com.training.autoproject.entity.Application;
import com.training.autoproject.entity.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by Oleg on 16-May-17.
 */
public class ApplicationServiceTest extends AbstractTestConfig {
    @Mock
    ApplicationDao applicationDao;
    @Mock
    CarDao carDao;
    @InjectMocks
    @Autowired
    ApplicationService applicationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(applicationService, "carDao", carDao);
        ReflectionTestUtils.setField(applicationService, "applicationDao", applicationDao);


    }

    @Test
    public void getCarsTest() {
        Car car = new Car();
        car.setIsactive(0);
        car.setImagePath("/src");
        car.setMake("Bmw");
        car.setPrice(1234);
        List<Car> cars = Collections.nCopies(1, car);
        when(carDao.findCarsByIsActive()).thenReturn(cars);
        List<Car> cars1 = applicationService.getAccessibleCars();
        Assert.assertEquals(car, cars.get(0));
    }

    @Test
    public void someTest() throws InterruptedException {
        Car car = new Car();
        car.setMake("Moke");
        when(carDao.findCarById(new Long(2))).thenReturn(car);
        Application application = new Application();
        application.setFirstname("Oleg");
        application.setTerm("5");
        application.setEmail("oleggrandcapital@gmail.com");
        application.setLastname("surkov");
        application.setPatronymic("ruslanych");
        application.setPassnum("CT12345567");
        doNothing().when(applicationDao).addApplication(application);
        applicationService.addApplication(application, new Long(2));
        Thread.sleep(3000);
    }


}
