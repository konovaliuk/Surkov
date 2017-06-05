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
 * Created by Oleg on 14.05.2017.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class BlackListDaoImpl implements BlackListDao {
    private static final Logger log = LogManager.getLogger(BlackListDao.class);
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<BlackList> findBlackList() {
        log.info("invoke findBlackList");
        List<BlackList> blackLists = entityManager.createQuery("select bl from BlackList bl", BlackList.class).getResultList();
        return blackLists;
    }

    @Override
    public void addBlackList(BlackList blackList) {
        log.info("Invoke addBlackList");
        entityManager.persist(blackList);
    }

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
