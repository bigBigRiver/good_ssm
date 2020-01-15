package com.good.frame.service.impl;

import com.good.frame.bean.User;
import com.good.frame.dao.IUserDao;
import com.good.frame.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public boolean addUser(User user) {
        int flag = userDao.insertUser(user);
        if(flag == 0)
            return false;
        else
            return true;
    }
}
