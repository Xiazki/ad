package com.ad.ds;

import com.ad.dao.UserDao;
import com.ad.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xiang on 2017/4/7.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

}
