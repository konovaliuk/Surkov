package com.training.autoproject.dao;

import com.training.autoproject.entity.BlackList;

import java.util.List;

/**
 * Created by Oleg on 10.05.2017.
 */
public interface BlackListDao {
    List<BlackList> findBlackList();

    void addBlackList(BlackList blackList);

    BlackList findBlackListByPassnum(String passnum);


}
