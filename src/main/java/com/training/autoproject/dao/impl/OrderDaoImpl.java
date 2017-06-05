package com.training.autoproject.dao.impl;

import com.training.autoproject.controller.UsersController;
import com.training.autoproject.dao.OrderDao;
import com.training.autoproject.entity.Order;
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
 * Created by Oleg on 14.05.2017.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class OrderDaoImpl implements OrderDao {
    private static final Logger log = LogManager.getLogger(OrderDaoImpl.class);
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Order> findOrdersByIsClosed() {
        log.info("Invoke findOrdersByIsClosed");
        List<Order> orderList = entityManager.createQuery("select o from Order o where o.isclosed=1", Order.class).getResultList();
        return orderList;
    }

    @Override
    public void updateOrder(Order order) {
        log.info("Invoke updateOrder");
        entityManager.merge(order);

    }

    @Override
    public void addOrder(Order order) {
        log.info("Invoke addOrder");
        entityManager.persist(order);
    }

    @Override
    public Order findOrderById(Long id) {
        log.info("Invoke findOrderById");
        Order order = entityManager.find(Order.class, id);
        return order;
    }
}
