package com.teleBot.service.impl;

import com.teleBot.dao.IUserDao;
import com.teleBot.model.KeyBoardType;
import com.teleBot.model.MessageHolder;
import com.teleBot.model.Role;
import com.teleBot.model.User;
import com.teleBot.service.IUserService;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

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
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User getUserByUpdateAndSaveIfNotExist(Update update) {
        long id = MessageUtils.getUserIdFromUpdate(update);
        User userFromDb = userDao.getUserById(id);
        if (userFromDb != null) {
            return userFromDb;
        }
        org.telegram.telegrambots.meta.api.objects.User userModel = MessageUtils.getUserNameFromUpdate(update);

        User user = new User(id, userModel.getFirstName(), userModel.getLastName(), Role.GUEST);
        userDao.save(user);
        return user;
    }

    @Override
    public MessageHolder buildYearMessageHolder(long id) {
        User user = getUserById(id);
        List<String> years = user.getYears();
        return MessageUtils.holder(years, "Выбери год", KeyBoardType.TWO_ROW, false, true);
    }
}
