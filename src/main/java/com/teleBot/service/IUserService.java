package com.teleBot.service;

import com.teleBot.model.MessageHolder;
import com.teleBot.model.User;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Serhii_Udaltsov on 6/6/2021
 */
public interface IUserService {

    User getUserByUpdateAndSaveIfNotExist(Update update);

    User getUserById(long id);

    void save(User user);

    MessageHolder buildYearMessageHolder(long id);
}
