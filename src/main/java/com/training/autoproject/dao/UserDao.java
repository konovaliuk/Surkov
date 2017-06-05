package com.training.autoproject.dao;

import com.training.autoproject.entity.User;

/**
 * Created by Oleg on 10.05.2017.
 */
public interface UserDao {
    User findUserByNickname(String nickname);
}
