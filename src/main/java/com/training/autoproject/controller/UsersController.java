package com.training.autoproject.controller;

import com.training.autoproject.entity.Application;
import com.training.autoproject.dto.JsonResponse;
import com.training.autoproject.service.ApplicationService;
import com.training.autoproject.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Controller for manipulating  with users requests
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Controller
public class UsersController {
    /**
     * Logger for logging class
     */
    private static final Logger log = LogManager.getLogger(UsersController.class);
    /**
     * field for injecting realization of {@link com.training.autoproject.service.ApplicationService}
     */
    @Autowired
    ApplicationService applicationService;
    @Autowired
    OrderService orderService;

    /**
     * Method that listens "/" request
     *
     * @param model
     * @return views name
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(Model model) {
        log.info("request to /");
        model.addAttribute("App", new Application());
        model.addAttribute("carList", applicationService.getAccessibleCars());
        return "index";

    }

    /**
     * Method that listens request on add Aplication
     *
     * @param application
     * @param bindingResult
     * @param id
     * @return json response
     */
    @RequestMapping(value = "/{carid}/addapplication", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse addApp(@Valid @ModelAttribute Application application, BindingResult bindingResult, @PathVariable("carid") Long id) {
        log.info("request to carid/addaplication");
        JsonResponse jsonResponse = new JsonResponse();
        if (bindingResult.hasErrors()) {
            jsonResponse.setFieldErrors(bindingResult.getFieldErrors());
            jsonResponse.setStatus("error");
            return jsonResponse;
        }
        jsonResponse.setStatus("success");
        applicationService.addApplication(application, id);
        return jsonResponse;
    }
}
