package com.training.autoproject.util.impl;

import com.training.autoproject.service.impl.ApplicationServiceImpl;
import com.training.autoproject.util.MailUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by Oleg on 16-May-17.
 */
@Component
public class MailUtilImpl implements MailUtil {
    private static final Logger log = LogManager.getLogger(MailUtilImpl.class);
    @Autowired
    MailSender mailSender;
    @Autowired
    SimpleMailMessage simpleMailMessage;

    @Override
    @Async
    public void sendMail(String email) {
        log.info("invoke sendMail");
        SimpleMailMessage simpleMailMessage1 = new SimpleMailMessage(simpleMailMessage);
        simpleMailMessage1.setFrom("surkovoleg2010@gmail.com");
        simpleMailMessage1.setTo(email);
        simpleMailMessage1.setText("Your application is accepted,our manager will connect with you.Thanks that choose our service.");
        mailSender.send(simpleMailMessage1);
    }
}
