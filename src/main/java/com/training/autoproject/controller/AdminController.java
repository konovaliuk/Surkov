package com.training.autoproject.controller;

import com.training.autoproject.entity.Application;
import com.training.autoproject.entity.BlackList;
import com.training.autoproject.entity.JsonOrderResponse;
import com.training.autoproject.entity.Order;
import com.training.autoproject.exception.CarAccessException;
import com.training.autoproject.service.ApplicationService;
import com.training.autoproject.service.OrderService;
import com.training.autoproject.service.impl.ApplicationServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Oleg on 16-May-17.
 */
@Controller
public class AdminController {
    private static final Logger log = LogManager.getLogger(AdminController.class);

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @ResponseBody
    public List<Application> startAdmin() {
        log.info("request to /admin");
        List<Application> applicationList = orderService.getActiveApplications();
        return applicationList;
    }

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getOrders() {
        log.info("request to /admin/orders");
        List<Order> orderList = orderService.getActiveOrders();
        return orderList;
    }

    @RequestMapping(value = "/admin/blacklist", method = RequestMethod.GET)
    @ResponseBody
    public List<BlackList> getBlackList() {
        log.info("request to /admin/blacklist");
        List<BlackList> orderList = orderService.getBlackList();
        return orderList;
    }

    @RequestMapping(value = "/admin/{id}/addorder", method = RequestMethod.GET)
    @ResponseBody
    public JsonOrderResponse addOrder(@PathVariable("id") Long id) {
        log.info("request to admin/id/addorder");
        JsonOrderResponse jsonOrderResponse = new JsonOrderResponse();
        try {
            orderService.addOrder(id);
        } catch (CarAccessException e) {
            log.info("CarAccessExceptin", new CarAccessException());
            jsonOrderResponse.setStatus("error");
            return jsonOrderResponse;
        }
        jsonOrderResponse.setApplicationList(orderService.getActiveApplications());
        return jsonOrderResponse;
    }

    @RequestMapping(value = "/admin/{id}/closeapp", method = RequestMethod.GET)
    @ResponseBody
    public List<Application> closeApp(@PathVariable("id") Long id) {
        log.info("request to /admin/id/closeapp");
        orderService.closeApplication(id);
        List<Application> applicationList = orderService.getActiveApplications();
        return applicationList;
    }

    @RequestMapping(value = "/admin/{id}/closeord", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> closeOrder(@PathVariable("id") Long id) {
        log.info("request to /admin/id/closeord");
        orderService.closeOrder(id);
        List<Order> orderList = orderService.getActiveOrders();
        return orderList;
    }

    @RequestMapping(value = "/admin/{id}/updateord", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> updateOrder(@PathVariable("id") Long id, @RequestParam("repcost") int repcost) {
        log.info("request to /admin/id/updateord");
        orderService.updateOrder(id, repcost);
        List<Order> orderList = orderService.getActiveOrders();
        return orderList;
    }


}
