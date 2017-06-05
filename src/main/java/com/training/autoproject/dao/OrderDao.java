package com.training.autoproject.dao;

import com.training.autoproject.entity.Order;

import java.util.List;

/**
 * Created by Oleg on 10.05.2017.
 */
public interface OrderDao {
    List<Order> findOrdersByIsClosed();

    void updateOrder(Order order);

    void addOrder(Order order);

    Order findOrderById(Long id);

}
