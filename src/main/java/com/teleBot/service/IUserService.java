package com.teleBot.service;

import com.teleBot.model.MessageHolder;
import com.teleBot.model.User;

/**
 * @author Serhii_Udaltsov on 6/6/2021
 */
public interface IUserService {

    User getUserById(long id);

    MessageHolder buildYearMessageHolder(long id);
}
