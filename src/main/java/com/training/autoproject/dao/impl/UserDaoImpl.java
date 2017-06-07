package com.training.autoproject.dao.impl;

import com.training.autoproject.dao.UserDao;
import com.training.autoproject.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Base impementation of
 * {@link com.training.autoproject.dao.UserDao}
 *
 * @author Oleh Surkov
 * @version 1.0
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl implements UserDao {
    /**
     * Logger for logging class
     */
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);
    /**
     * EntityManager  for using JPA
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Implementation method from
     * {@link com.training.autoproject.dao.UserDao}
     *
     * @param nickname
     * @return User
     */
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
