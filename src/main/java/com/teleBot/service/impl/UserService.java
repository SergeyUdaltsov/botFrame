package com.teleBot.service.impl;

import com.teleBot.dao.IUserDao;
import com.teleBot.model.KeyBoardType;
import com.teleBot.model.MessageHolder;
import com.teleBot.model.User;
import com.teleBot.service.IUserService;
import com.teleBot.utils.MessageUtils;

import java.util.List;

/**
 * @author Serhii_Udaltsov on 6/6/2021
 */
public class UserService implements IUserService {

    private IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public MessageHolder buildYearMessageHolder(long id) {
        User user = getUserById(id);
        List<String> years = user.getYears();
        return MessageUtils.holder(years, "Выбери год", KeyBoardType.TWO_ROW, false, true);
    }
}
