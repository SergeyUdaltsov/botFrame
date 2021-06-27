package com.teleBot.processor.impl;

import com.teleBot.helper.IDashboardHelper;
import com.teleBot.model.CommandType;
import com.teleBot.model.MessageHolder;
import com.teleBot.processor.IProcessor;
import com.teleBot.service.IContextService;
import com.teleBot.utils.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/18/2021
 */
public class DashboardProcessor implements IProcessor {
    private static final String PARAMS_KEY = "dashboardAction";

//    private Map<String, IDashboardHelper> helpersMap;
    private IContextService contextService;
    private IDashboardHelper helper;

    public DashboardProcessor(IContextService contextService) {
        this.contextService = contextService;
    }

    @Override
    public MessageHolder processRequest(Update update) {
//        String text = contextService.getMessageTextOrDefault(update, PARAMS_KEY);
//        this.helper = helpersMap.get(text);
//        String paramsValue = helper.getHelperParamsValue();
//        contextService.updateContextParams(update,
//                CollectionUtils.<String, Object>mapBuilder()
//                        .withPair(PARAMS_KEY, paramsValue)
//                        .build());
//        return helper.getMessage(update);
        return null;
    }

    @Override
    public CommandType getNextCommandType() {
        return helper.getNextCommandType();
    }
}
