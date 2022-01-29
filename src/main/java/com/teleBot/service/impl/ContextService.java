package com.teleBot.service.impl;

import com.teleBot.dao.IContextDao;
import com.teleBot.model.CommandType;
import com.teleBot.model.Context;
import com.teleBot.service.IContextService;
import com.teleBot.utils.CollectionUtils;
import com.teleBot.utils.JsonUtils;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/10/2021
 */
public class ContextService implements IContextService {

    IContextDao contextDao;

    public ContextService(IContextDao contextDao) {
        this.contextDao = contextDao;
    }

    @Override
    public void save(Context context) {
        contextDao.save(context);
    }

    @Override
    public void updateContextParams(Update update, Map<String, Object> params) {
        Context context = getContext(update);
        context.getParams().putAll(params);
        save(context);
    }

    @Override
    public void updateContextParams(long chatId, Map<String, Object> params) {
        Context context = contextDao.getContext(chatId);
        context.getParams().putAll(params);
        save(context);
    }

    @Override
    public void updateContextCommands(Map<String, String> commands, Update update) {
        Context contextFromDb = getContext(update);
        contextFromDb.setCommands(commands);
        contextDao.save(contextFromDb);
    }

    @Override
    public String getStringValueFromParams(Update update, String paramKey) {
        Context context = getContext(update);
        return (String) context.getParams().get(paramKey);
    }

    @Override
    public Object getValueFromParams(Update update, String paramKey) {
        Context context = getContext(update);
        return context.getParams().get(paramKey);
    }

    @Override
    public String getMessageText(Update update) {
        if (update.getCallbackQuery() != null) {
            return update.getCallbackQuery().getData();
        }
        return update.getMessage().getText();
    }

    @Override
    public Context getContext(Update update) {
        if (update == null) {
            return null;
        }
        CallbackQuery callbackQuery = update.getCallbackQuery();
        if (callbackQuery != null) {
            return contextDao.getContext(callbackQuery.getFrom().getId());
        }
        Long userId = update.getMessage().getFrom().getId();
        return contextDao.getContext(userId);
    }

}
