package com.training.autoproject.dao;

import com.training.autoproject.entity.BlackList;

import java.util.List;

/**
 * Interface for BlackListDao layer
 *
 * @author Oleh Surkov
 * @version 1.0
 */
public interface BlackListDao {
    /**
     * Method which finds all rows in database
     *
     * @return list of BlackList
     */
    List<BlackList> findBlackList();

    /**
     * Method to add BlackList
     *
     * @param blackList
     */
    void addBlackList(BlackList blackList);

    /**
     * Method which finds BlackList by passnum in database
     *
     * @param passnum
     * @return BlackList  by passnum
     */
    BlackList findBlackListByPassnum(String passnum);


}
