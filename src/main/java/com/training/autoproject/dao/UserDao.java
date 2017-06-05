package com.training.autoproject.dao;

import com.training.autoproject.entity.User;

/**
 * Interface for UserDao layer
 *
 * @author Oleh Surkov
 * @version 1.0
 */
public interface UserDao {
    /**
     * Method which finds User in databaase by nickname
     *
     * @param nickname
     * @return
     */
    User findUserByNickname(String nickname);
}
