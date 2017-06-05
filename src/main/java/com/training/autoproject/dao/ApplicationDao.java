package com.training.autoproject.dao;

import com.training.autoproject.entity.Application;

import java.util.List;


/**
 * Interface for ApplicationDao layer
 *
 * @author Oleh Surkov
 * @version 1.0
 */
public interface ApplicationDao {
    /**
     * Method which finds all application in database where isclosed ==1
     *
     * @return list of not closed applications in database
     */
    List<Application> findApplicationsByIsClosed();

    /**
     * Method to update application
     *
     * @param application
     */
    void updateApplication(Application application);

    /**
     * Method to add application
     *
     * @param application
     */
    void addApplication(Application application);

    /**
     * Method which finds application by id
     *
     * @param id of application
     * @return application by id
     */
    Application findApplicationById(Long id);


}
