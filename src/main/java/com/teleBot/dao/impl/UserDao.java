package com.teleBot.dao.impl;

import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.teleBot.dao.IDynamoDbFactory;
import com.teleBot.dao.IUserDao;
import com.teleBot.model.User;

/**
 * @author Serhii_Udaltsov on 6/6/2021
 */
public class UserDao extends BaseDao<User> implements IUserDao {

    public UserDao(IDynamoDbFactory dynamoFactory) {
        super(dynamoFactory, User.class);
    }

    @Override
    public User getUserById(long id) {
        return super.getEntityByPrimaryKey(new PrimaryKey(User.HASH_KEY_NAME, id));
    }
}
