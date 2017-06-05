package com.training.autoproject.service.impl;

import com.training.autoproject.dao.UserDao;
import com.training.autoproject.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Base implementation of
 * {@link  org.springframework.security.core.userdetails.UserDetailsService}
 *
 * @author Oleh Surkov
 * @version 1.0
 */

public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * Logger for logging class
     */
    private static final Logger log = LogManager.getLogger(UserDetailsServiceImpl.class);
    /**
     * field for injecting realization of {@link com.training.autoproject.dao.UserDao}
     */
    @Autowired
    UserDao userDao;

    /**
     * Implementation method from
     * {@link org.springframework.security.core.userdetails.UserDetailsService }
     *
     * @param nickname
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        log.info("invoke loadUserByUsername");
        User user = userDao.findUserByNickname(nickname);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getRoleTypeById().getRole()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getNickname(), user.getPassword().trim(), roles);
        return userDetails;
    }
}
