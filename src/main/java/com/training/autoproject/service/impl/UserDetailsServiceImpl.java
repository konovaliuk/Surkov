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
 * Created by Oleg on 16-May-17.
 */

public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LogManager.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    UserDao userDao;

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
