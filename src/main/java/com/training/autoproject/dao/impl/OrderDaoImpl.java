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
 * Base implementation of
 * {@link com.training.autoproject.dao.OrderDao}
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class OrderDaoImpl implements OrderDao {
    /**
     * Logger for logging class
     */
    private static final Logger log = LogManager.getLogger(OrderDaoImpl.class);
    /**
     * EntityManager  for using JPA
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.OrderDao}
     *
     * @return list of Orders
     */
    @Override
    public List<Order> findOrdersByIsClosed() {
        log.info("Invoke findOrdersByIsClosed");
        return entityManager.createQuery("select o from Order o where o.isclosed=1", Order.class).getResultList();
    }

    /**
     * Implementation method from
     * {@link  com.training.autoproject.dao.OrderDao}
     *
     * @param order
     */
    @Override
    public void updateOrder(Order order) {
        log.info("Invoke updateOrder");
        entityManager.merge(order);

    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.OrderDao}
     *
     * @param order
     */
    @Override
    public void addOrder(Order order) {
        log.info("Invoke addOrder");
        entityManager.persist(order);
    }

    /**
     * Implementation mrthod from
     * {@link com.training.autoproject.dao.OrderDao}
     *
     * @param id
     * @return Order
     */
    @Override
    public Order findOrderById(Long id) {
        log.info("Invoke findOrderById");
        return entityManager.find(Order.class, id);
    }
}
