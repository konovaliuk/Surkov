package com.training.autoproject.dao;

import com.training.autoproject.entity.Application;

import java.util.List;


/**
 * Created by Oleg on 10.05.2017.
 */
public interface ApplicationDao {
    List<Application> findApplicationsByIsClosed();


    void updateApplication(Application application);

    void addApplication(Application application);

    Application findApplicationById(Long id);


}
