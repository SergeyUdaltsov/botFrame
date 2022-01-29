package com.teleBot.service;

import com.teleBot.model.CommandType;
import com.teleBot.model.Context;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/10/2021
 */
public interface IContextService {

    void save(Context context);

    Context getContext(Update update);

    void updateContextCommands(Map<String, String> commands, Update update);

    void updateContextParams(Update update, Map<String, Object> params);

    void updateContextParams(long chatId, Map<String, Object> params);

    String getMessageText(Update update);

    String getStringValueFromParams(Update update, String paramKey);

    Object getValueFromParams(Update update, String paramKey);

}
