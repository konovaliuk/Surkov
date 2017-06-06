package com.training.autoproject.dao.impl;

import com.training.autoproject.controller.UsersController;
import com.training.autoproject.dao.BlackListDao;
import com.training.autoproject.entity.BlackList;
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
 * Base implementation of
 * {@link com.training.autoproject.dao.BlackListDao}
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class BlackListDaoImpl implements BlackListDao {
    /**
     * Logger for logging class
     */
    private static final Logger log = LogManager.getLogger(BlackListDao.class);
    /**
     * EntityManager  for using JPA
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.BlackListDao}
     *
     * @return list of BlackList
     */
    @Override
    public List<BlackList> findBlackList() {
        log.info("invoke findBlackList");
        return entityManager.createQuery("select bl from BlackList bl", BlackList.class).getResultList();
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.BlackListDao}
     *
     * @param blackList
     */
    @Override
    public void addBlackList(BlackList blackList) {
        log.info("Invoke addBlackList");
        entityManager.persist(blackList);
    }

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.BlackListDao}
     *
     * @param passnum
     * @return BlackList
     */
    @Override
    public BlackList findBlackListByPassnum(String passnum) {
        log.info("Invoke findBlackListByPassnum");
        List<BlackList> blackList = entityManager.createQuery("select bl from BlackList bl where bl.passnum=:passnum", BlackList.class).setParameter("passnum", passnum).getResultList();
        if (blackList.size() == 0) {
            log.info("return null");
            return null;
        }
        log.info("return not null");
        return blackList.get(0);
    }
}
