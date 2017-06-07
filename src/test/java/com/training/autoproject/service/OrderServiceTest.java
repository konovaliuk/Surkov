package com.training.autoproject.service;

import com.training.autoproject.AbstractTestConfig;
import com.training.autoproject.dao.ApplicationDao;
import com.training.autoproject.dao.BlackListDao;
import com.training.autoproject.dao.CarDao;
import com.training.autoproject.dao.OrderDao;
import com.training.autoproject.entity.Application;
import com.training.autoproject.entity.BlackList;
import com.training.autoproject.entity.Car;
import com.training.autoproject.entity.Order;
import com.training.autoproject.exception.CarAccessException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by Oleg on 16-May-17.
 */
public class OrderServiceTest extends AbstractTestConfig {
    @Mock
    ApplicationDao applicationDao;
    @Mock
    CarDao carDao;
    @Mock
    BlackListDao blackListDao;
    @Mock
    OrderDao orderDao;
    @InjectMocks
    @Autowired
    OrderService orderService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(orderService, "carDao", carDao);
        ReflectionTestUtils.setField(orderService, "applicationDao", applicationDao);
        ReflectionTestUtils.setField(orderService, "blackListDao", blackListDao);
        ReflectionTestUtils.setField(orderService, "orderDao", orderDao);
    }

    @Test
    public void GetActiveOrdersOnEqualstest() {
        Order order = new Order();
        order.setIsclosed(1);
        order.setRepaircost(0);
        order.setRetdate(new Date());
        List<Order> orderList = Collections.nCopies(1, order);
        when(orderDao.findOrdersByIsClosed()).thenReturn(orderList);
        Assert.assertEquals(order, orderService.getActiveOrders().get(0));
    }

    @Test
    public void findInBlackListTrueTest() {
        BlackList blackList = new BlackList();
        when(blackListDao.findBlackListByPassnum("Moke")).thenReturn(blackList);
        Assert.assertTrue(orderService.findInBlackList("Moke"));
    }

    @Test
    public void findInBlackListFalseTest() {
        BlackList blackList = new BlackList();
        when(blackListDao.findBlackListByPassnum("Moke")).thenReturn(null);
        Assert.assertFalse(orderService.findInBlackList("Moke"));
    }

    @Test
    public void getBlackListEqualsTest() {
        BlackList blackList = new BlackList();
        List<BlackList> blackLists = Collections.nCopies(1, blackList);
        when(blackListDao.findBlackList()).thenReturn(blackLists);
        Assert.assertEquals(blackList, orderService.getBlackList().get(0));
    }

    @Test
    public void getActiveApplicationsEqualsTest() {
        Application application = new Application();
        List<Application> applicationList = Collections.nCopies(1, application);
        when(applicationDao.findApplicationsByIsClosed()).thenReturn(applicationList);
        Assert.assertEquals(application, orderService.getActiveApplications().get(0));
    }

    @Test
    public void addOrderLogicTest() {
        Car car = new Car();
        car.setIsactive(0);
        Application application = new Application();
        application.setCarByCarId(car);
        application.setTerm("4");
        when(applicationDao.findApplicationById(2L)).thenReturn(application);
        doNothing().when(carDao).updateCarByIsActive(car);
        doNothing().when(applicationDao).updateApplication(application);
        try {
            orderService.addOrder(2L);
        } catch (CarAccessException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = CarAccessException.class)
    public void addOrderLogicFailedTest() throws CarAccessException {
        Car car = new Car();
        car.setIsactive(1);
        Application application = new Application();
        application.setCarByCarId(car);
        application.setTerm("5");
        when(applicationDao.findApplicationById(3L)).thenReturn(application);
        doNothing().when(carDao).updateCarByIsActive(car);
        doNothing().when(applicationDao).updateApplication(application);
        orderService.addOrder(3L);
    }

    @Test
    public void addToBlackListLogicTest() {
        BlackList blackList = new BlackList();
        blackList.setPassnum("Moke");
        doNothing().when(blackListDao).addBlackList(blackList);
        orderService.addToBlackList("Moke");
    }

    @Test
    public void updateOrderLogicTest() {
        Order order = new Order();
        when(orderDao.findOrderById(2L)).thenReturn(order);
        doNothing().when(orderDao).updateOrder(order);
        orderService.updateOrder(2L, 214);
    }

    @Test
    public void closeApplicationLogicTest() {
        Application application = new Application();
        when(applicationDao.findApplicationById(2L)).thenReturn(application);
        doNothing().when(applicationDao).updateApplication(application);
        orderService.closeApplication(2L);
        System.out.println("hello");
    }
}
