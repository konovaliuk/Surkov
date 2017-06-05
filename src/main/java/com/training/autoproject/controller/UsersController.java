package com.training.autoproject.controller;

import com.training.autoproject.dao.OrderDao;
import com.training.autoproject.entity.Application;
import com.training.autoproject.entity.JsonResponse;
import com.training.autoproject.service.ApplicationService;
import com.training.autoproject.service.impl.ApplicationServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by Oleg on 16-May-17.
 */
@Controller
public class UsersController {
    private static final Logger log = LogManager.getLogger(UsersController.class);
    @Autowired
    ApplicationService applicationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(Model model) {
        log.info("request to /");
        model.addAttribute("App", new Application());
        model.addAttribute("carList", applicationService.getAccessibleCars());
        return "index";

    }

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
