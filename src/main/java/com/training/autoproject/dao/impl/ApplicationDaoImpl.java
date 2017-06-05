package com.training.autoproject.dao.impl;

import com.training.autoproject.controller.UsersController;
import com.training.autoproject.dao.ApplicationDao;
import com.training.autoproject.entity.Application;

import com.training.autoproject.service.impl.ApplicationServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Created by Oleg on 14.05.2017.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ApplicationDaoImpl implements ApplicationDao {
    private static final Logger log = LogManager.getLogger(ApplicationServiceImpl.class);
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Application> findApplicationsByIsClosed() {
        log.info("Invoke findApplicationByIsClosed");
        List<Application> applicationList = entityManager.createQuery("select  a from Application a where a.isclosed=1", Application.class).getResultList();
        return applicationList;
    }

    @Override
    public void updateApplication(Application application) {
        log.info("Invoke updateApplication");
        entityManager.merge(application);
    }

    @Override
    public void addApplication(Application application) {
        log.info("Invoke addApplication");
        entityManager.persist(application);
    }

    @Override
    public Application findApplicationById(Long id) {
        log.info("Invoke findApplicationById");
        return entityManager.find(Application.class, id);
    }
}
