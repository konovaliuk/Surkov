package com.training.autoproject.dao;

import com.training.autoproject.entity.Order;

import java.util.List;

/**
 * Interface fo OrderDao layer
 *
 * @author Oleh Surkov
 * @version 1.0
 */
public interface OrderDao {
    /**
     * Method which finds Orders in database where isclosed==1
     *
     * @return list of Orders
     */
    List<Order> findOrdersByIsClosed();

    /**
     * Method to update Order
     *
     * @param order
     */
    void updateOrder(Order order);

    /**
     * Method to add Order
     *
     * @param order
     */
    void addOrder(Order order);

    /**
     * Method which finds Order by id in database
     *
     * @param id
     * @return
     */
    Order findOrderById(Long id);

}
