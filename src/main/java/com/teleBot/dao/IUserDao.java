package com.teleBot.dao;

import com.teleBot.model.User;

/**
 * @author Serhii_Udaltsov on 6/6/2021
 */
public interface IUserDao {

    User getUserById(long id);
}
