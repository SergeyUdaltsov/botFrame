package com.teleBot.dao.impl;

import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.teleBot.dao.IContextDao;
import com.teleBot.dao.IDynamoDbFactory;
import com.teleBot.model.CommandType;
import com.teleBot.model.Context;

import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/10/2021
 */
public class ContextDao extends BaseDao<Context> implements IContextDao {
    private IDynamoDbFactory dynamoDbFactory;

    public ContextDao(IDynamoDbFactory dynamoDbFactory) {
        super(dynamoDbFactory, Context.class);
        this.dynamoDbFactory = dynamoDbFactory;
    }

    @Override
    public Context getContext(long userId) {
        Context context = new Context();
        context.setUserId(userId);
        return getEntityByQueryObject(context);
    }

    @Override
    public void updateContextCommand(long userId, CommandType commandType, CommandType previousCommandType,
                                     Map<String, Object> params) {
    }
}
