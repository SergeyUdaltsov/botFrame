package com.teleBot.processor.common;

import com.teleBot.model.ButtonsType;
import com.teleBot.model.CommandType;
import com.teleBot.model.Context;
import com.teleBot.model.KeyBoardType;
import com.teleBot.model.MessageHolder;
import com.teleBot.processor.IProcessor;
import com.teleBot.service.IContextService;
import com.teleBot.utils.CollectionUtils;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/7/2021
 */
public class StartProcessor implements IProcessor {

    private IContextService contextService;

    public StartProcessor(IContextService contextService) {
        this.contextService = contextService;
    }

    @Override
    public List<MessageHolder> processRequest(Update update) {
        long operatorId = MessageUtils.getUserIdFromUpdate(update);
        List<String> titles = Arrays.asList("Мое авто", "Найти деталь", "О нас", "Регистрация");
        MessageHolder holder = MessageUtils.holder(titles, "Выбери действие", KeyBoardType.TWO_ROW,
                false, false);
        holder.setButtonsType(ButtonsType.KEYBOARD);
        Context context = buildContext(operatorId);
        contextService.save(context);
        return Collections.singletonList(holder);
    }

    private Context buildContext(long chatId) {
        Context context = new Context();
        context.setUserId(chatId);
        context.setParams(Collections.emptyMap());
        return context;
    }

    @Override
    public Map<String, String> getCommands() {
        return CollectionUtils.<String, String>mapBuilder()
                .withPair("Мое авто", CommandType.BRAND_REGISTRATION_PROCESSOR.name())
                .withPair("Найти деталь", CommandType.PAYMENTS_PROCESSOR.name())
                .withPair("О нас", CommandType.BALANCE_PROCESSOR.name())
                .withPair("Регистрация", CommandType.BILLS_PROCESSOR.name())
                .withPair("Назад", CommandType.START.name())
                .build();
    }
}
