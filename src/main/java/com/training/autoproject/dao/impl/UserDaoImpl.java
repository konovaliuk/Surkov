package com.training.autoproject.dao.impl;

import com.training.autoproject.controller.UsersController;
import com.training.autoproject.dao.UserDao;
import com.training.autoproject.entity.User;
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
public class UserDaoImpl implements UserDao {
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findUserByNickname(String nickname) {
        log.info("Invoke findUserByNickName");
        List<User> user = entityManager.createQuery("select a from User a where a.nickname=:nickname", User.class).setParameter("nickname", nickname).getResultList();
        if (user.size() == 0) {
            log.info("return null");
            return null;
        }
        log.info("return not null");
        return user.get(0);
    }
}
