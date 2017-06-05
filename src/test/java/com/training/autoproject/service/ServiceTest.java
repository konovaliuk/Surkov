package com.training.autoproject.service;

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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test-config.xml")
@Rollback
@Transactional(transactionManager = "transactionManager")
public class ServiceTest {

    @Mock
    ApplicationDao applicationDao;
    @Mock
    CarDao carDao;
    @Mock
    OrderDao orderDao;
    @Mock
    UserDao userDao;
    @Mock
    BlackListDao blackListDao;
    @InjectMocks
    @Autowired
    ApplicationService generalService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(generalService, "carDao", carDao);

        ReflectionTestUtils.setField(generalService, "applicationDao", applicationDao);


    }

    @Test
    public void getCarsTest() {
        Car car = new Car();
        car.setIsactive(0);
        car.setImagePath("/src");
        car.setMake("Bmw");
        car.setPrice(1234);
        List<Car> cars = Collections.nCopies(1, car);
        when(generalService.getAccessibleCars()).thenReturn(cars);
        List<Car> cars1 = generalService.getAccessibleCars();
        Assert.assertEquals(car, cars.get(0));
    }

    @Test
    public void someTest() {
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
        long start = System.currentTimeMillis();

        generalService.addApplication(application, new Long(2));

        long finish = System.currentTimeMillis();
        System.out.println("time " + (finish - start));
    }


}
