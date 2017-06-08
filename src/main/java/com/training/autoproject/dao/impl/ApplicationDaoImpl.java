package com.training.autoproject.dao.impl;


import com.training.autoproject.dao.ApplicationDao;
import com.training.autoproject.entity.Application;

import com.training.autoproject.service.impl.ApplicationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Base implementation of
 * {@link com.training.autoproject.dao.ApplicationDao}
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ApplicationDaoImpl implements ApplicationDao {
    /**
     * Logger for logging class
     */
    private static final Logger log = LogManager.getLogger(ApplicationServiceImpl.class);
    /**
     * EntityManager  for using JPA
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.ApplicationDao}
     *
     * @return list of Application
     */
    @Override
    public List<Application> findApplicationsByIsClosed() {
        log.info("Invoke findApplicationByIsClosed");
        return entityManager.createQuery("select  a from Application a where a.isclosed=1", Application.class).getResultList();

    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.ApplicationDao}
     *
     * @param application
     */
    @Override
    public void updateApplication(Application application) {
        log.info("Invoke updateApplication");
        entityManager.merge(application);
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.ApplicationDao}
     *
     * @param application
     */
    @Override
    public void addApplication(Application application) {
        log.info("Invoke addApplication");
        entityManager.persist(application);
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.ApplicationDao}
     *
     * @param id of application
     * @return application
     */
    @Override
    public Application findApplicationById(Long id) {
        log.info("Invoke findApplicationById");
        return entityManager.find(Application.class, id);
    }
}
