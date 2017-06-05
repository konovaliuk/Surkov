package com.training.autoproject.util;

/**
 * Interface to be implemented by any object for
 * mail sending
 *
 * @author Oleh Surkov
 * @version 1.0
 */
public interface MailUtil {
    /**
     * Method to send mail
     *
     * @param email for sending
     */
    void sendMail(String email);
}
