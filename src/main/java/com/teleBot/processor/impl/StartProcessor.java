package com.teleBot.processor.impl;

import com.teleBot.model.CommandType;
import com.teleBot.model.Context;
import com.teleBot.model.MessageHolder;
import com.teleBot.processor.IProcessor;
import com.teleBot.service.IContextService;
import com.teleBot.service.IRoleFacade;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;

/**
 * @author Serhii_Udaltsov on 4/7/2021
 */
public class StartProcessor implements IProcessor {

    private IContextService contextService;
    private IRoleFacade roleFacade;

    public StartProcessor(IContextService contextService, IRoleFacade roleFacade) {
        this.contextService = contextService;
        this.roleFacade = roleFacade;
    }

    @Override
    public MessageHolder processRequest(Update update) {
        long operatorId = MessageUtils.getUserIdFromUpdate(update);
        MessageHolder messageHolder = MessageUtils.buildDashboardHolder();
        Context context = buildContext(operatorId);
        contextService.save(context);
        return messageHolder;
    }

    private Context buildContext(long chatId) {
        Context context = new Context();
        context.setUserId(chatId);
        context.setParams(Collections.emptyMap());
        return context;
    }

    @Override
    public CommandType getNextCommandType() {
        return CommandType.DASHBOARD_PROCESSOR;
    }
}