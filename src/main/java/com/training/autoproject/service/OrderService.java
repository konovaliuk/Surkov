package com.training.autoproject.service;

import com.training.autoproject.entity.Application;
import com.training.autoproject.entity.BlackList;
import com.training.autoproject.entity.Order;
import com.training.autoproject.exception.CarAccessException;

import java.util.List;

/**
 * Created by Oleg on 16-May-17.
 */
public interface OrderService {

    void addOrder(Long appId) throws CarAccessException;

    void closeOrder(Long id);

    List<Order> getActiveOrders();

    void addToBlackList(String passnum);

    boolean findInBlackList(String passnum);

    void updateOrder(Long orderId, int repairCost);

    void closeApplication(Long appId);

    List<BlackList> getBlackList();

    List<Application> getActiveApplications();
}
