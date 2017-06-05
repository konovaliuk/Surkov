package com.training.autoproject.service;

import com.training.autoproject.entity.Application;
import com.training.autoproject.entity.BlackList;
import com.training.autoproject.entity.Order;
import com.training.autoproject.exception.CarAccessException;

import java.util.List;

/**
 * Interface for OrderService layer
 *
 * @author Oleh Surkov
 * @version 1.0
 */
public interface OrderService {
    /**
     * Method to add Order
     *
     * @param appId
     * @throws CarAccessException
     */

    void addOrder(Long appId) throws CarAccessException;

    /**
     * Method to close Order
     *
     * @param id
     */
    void closeOrder(Long id);

    /**
     * Method to get active Orders
     *
     * @return list of Cars
     */

    List<Order> getActiveOrders();

    /**
     * Method to add passnum to BlackList
     *
     * @param passnum
     */

    void addToBlackList(String passnum);

    /**
     * Method to finds passnum in BlackList
     *
     * @param passnum
     * @return boolean value
     */
    boolean findInBlackList(String passnum);

    /**
     * Method to update Order
     *
     * @param orderId
     * @param repairCost
     */
    void updateOrder(Long orderId, int repairCost);

    /**
     * Method to close Application
     *
     * @param appId
     */
    void closeApplication(Long appId);

    /**
     * Method to get all BlackList
     *
     * @return list of BlackList
     */
    List<BlackList> getBlackList();

    /**
     * Method to get active Applications
     *
     * @return list of Application
     */
    List<Application> getActiveApplications();
}
