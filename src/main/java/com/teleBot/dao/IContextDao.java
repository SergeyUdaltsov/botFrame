package com.teleBot.dao;

import com.teleBot.model.CommandType;
import com.teleBot.model.Context;

import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/10/2021
 */
public interface IContextDao {

    void save(Context context);

    Context getContext(long userId);

    void updateContextCommand(long userId, CommandType commandType, CommandType previousCommandType,
                              Map<String, Object> params);
}
