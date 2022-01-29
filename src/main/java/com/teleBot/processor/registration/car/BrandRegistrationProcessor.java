package com.teleBot.processor.registration.car;

import com.teleBot.model.ButtonsType;
import com.teleBot.model.CommandType;
import com.teleBot.model.KeyBoardType;
import com.teleBot.model.MessageHolder;
import com.teleBot.processor.IProcessor;
import com.teleBot.service.IUserService;
import com.teleBot.utils.CollectionUtils;
import com.teleBot.utils.Constants;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 12/24/2021
 */
public class BrandRegistrationProcessor implements IProcessor {

    private IUserService userService;

    public BrandRegistrationProcessor(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public List<MessageHolder> processRequest(Update update) throws TelegramApiException {
        List<String> titles = Arrays.asList("Toyota", "BMW", "Mercedes", "Audi");
        MessageHolder holder = MessageUtils.holder(titles, "Выбери марку", KeyBoardType.THREE_ROW,
                false, false);
        holder.setButtonsType(ButtonsType.INLINE);
        return Collections.singletonList(holder);
    }

    @Override
    public Map<String, String> getCommands() {
        return CollectionUtils.<String, String>mapBuilder()
                .withPair(Constants.EACH, CommandType.MODEL_REGISTRATION_PROCESSOR.name())
                .build();
    }
}
