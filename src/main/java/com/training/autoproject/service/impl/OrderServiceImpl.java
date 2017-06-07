package com.training.autoproject.service.impl;

import com.training.autoproject.dao.ApplicationDao;
import com.training.autoproject.dao.BlackListDao;
import com.training.autoproject.dao.CarDao;
import com.training.autoproject.dao.OrderDao;
import com.training.autoproject.entity.Application;
import com.training.autoproject.entity.BlackList;
import com.training.autoproject.entity.Car;
import com.training.autoproject.entity.Order;
import com.training.autoproject.exception.CarAccessException;
import com.training.autoproject.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Basic implementation of
 * {@link com.training.autoproject.service.OrderService}
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    /**
     * Logger for logging class
     */
    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);
    /**
     * Constant for inner using
     */
    private static final int CLOSED = 0;
    /**
     * Constant for inner using
     */
    private static final int NOT_CLOSED = 1;
    /**
     * field for injecting realization of {@link com.training.autoproject.dao.ApplicationDao}
     */
    @Autowired
    ApplicationDao applicationDao;
    /**
     * field for injecting realization of {@link com.training.autoproject.dao.CarDao}
     */
    @Autowired
    CarDao carDao;
    /**
     * field for injecting realization of {@link com.training.autoproject.dao.OrderDao}
     */
    @Autowired
    OrderDao orderDao;
    /**
     * field for injecting realization of {@link com.training.autoproject.dao.BlackListDao}
     */
    @Autowired
    BlackListDao blackListDao;

    /**
     * Implementation method from
     * {@link com.training.autoproject.service.OrderService}
     *
     * @param appId
     * @throws CarAccessException
     */
    @Transactional
    @Override
    public void addOrder(Long appId) throws CarAccessException {
        log.info("invoke AddOrder");
        Application application = applicationDao.findApplicationById(appId);
        if (application.getCarByCarId().getIsactive() == NOT_CLOSED) {
            throw new CarAccessException();
        }
        application.setIsclosed(CLOSED);
        Date date = new DateTime().plusMonths(Integer.parseInt(application.getTerm())).toDate();
        Order order = new Order();
        order.setAppByAppId(application);
        order.setIsclosed(NOT_CLOSED);
        order.setRepaircost(0);
        order.setRetdate(date);
        Car car = (application.getCarByCarId());
        car.setIsactive(NOT_CLOSED);
        carDao.updateCarByIsActive(car);
        applicationDao.updateApplication(application);
        orderDao.addOrder(order);
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.service.OrderService}
     *
     * @param id
     */
    @Transactional
    @Override
    public void closeOrder(Long id) {
        log.info("invoke closeOrder");
        Order order = orderDao.findOrderById(id);
        order.setIsclosed(CLOSED);
        orderDao.updateOrder(order);
        Car car = order.getAppByAppId().getCarByCarId();
        car.setIsactive(CLOSED);
        carDao.updateCarByIsActive(car);
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.service.OrderService}
     *
     * @return list of Orders
     */
    @Transactional(readOnly = true)
    @Override
    public List<Order> getActiveOrders() {
        log.info("invoke getActiveOrders");
        return orderDao.findOrdersByIsClosed();
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.service.OrderService}
     *
     * @param passnum
     */
    @Transactional
    @Override
    public void addToBlackList(String passnum) {
        log.info("invoke addToBlackList");
        BlackList blackList = new BlackList();
        blackList.setPassnum(passnum);
        blackListDao.addBlackList(blackList);
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.service.OrderService}
     *
     * @param passnum
     * @return boolean value
     */
    @Transactional(readOnly = true)
    @Override
    public boolean findInBlackList(String passnum) {
        log.info("invoke findInBlackList");
        BlackList blackList = blackListDao.findBlackListByPassnum(passnum);
        if (blackList != null) {
            log.info("return true");
            return true;
        }
        log.info("return false");
        return false;
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.service.OrderService}
     *
     * @param orderId
     * @param repairCost
     */
    @Transactional
    @Override
    public void updateOrder(Long orderId, int repairCost) {
        log.info("invoke updateOrder");
        Order order = orderDao.findOrderById(orderId);
        order.setRepaircost(repairCost);
        orderDao.updateOrder(order);
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.service.OrderService}
     *
     * @param appId
     */
    @Transactional
    @Override
    public void closeApplication(Long appId) {
        log.info("invoke closeApplication");
        Application application = applicationDao.findApplicationById(appId);
        application.setIsclosed(0);
        applicationDao.updateApplication(application);
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.service.OrderService}
     *
     * @return list of BlackList
     */
    @Transactional(readOnly = true)
    @Override
    public List<BlackList> getBlackList() {
        log.info("invoke getBlackList");
        return blackListDao.findBlackList();

    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.service.OrderService}
     *
     * @return list of Applications
     */
    @Transactional(readOnly = true)
    @Override
    public List<Application> getActiveApplications() {
        log.info("invoke getActiveApplications");
        return applicationDao.findApplicationsByIsClosed();
    }
}
