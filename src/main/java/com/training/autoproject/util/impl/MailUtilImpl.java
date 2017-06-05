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
 * Default implementation of the
 * {@link com.training.autoproject.util.MailUtil}
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Component
public class MailUtilImpl implements MailUtil {
    /**
     * Logger for logging class
     */
    private static final Logger log = LogManager.getLogger(MailUtilImpl.class);
    /**
     * Mail Sender for sending mail,is configured in applicationContext.xml
     */
    @Autowired
    MailSender mailSender;
    /**
     * Message which sends to users,is configures in applicationContext
     */
    @Autowired
    SimpleMailMessage simpleMailMessage;

    /**
     * Implementation method from
     * {@link com.training.autoproject.util.MailUtil}
     *
     * @param email for sending
     */
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
