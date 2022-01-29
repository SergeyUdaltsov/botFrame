package com.teleBot.processor.registration.user;

import com.teleBot.model.MessageHolder;
import com.teleBot.processor.IProcessor;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 12/24/2021
 */
public class UserStartRegistrationProcessor implements IProcessor {

    @Override
    public List<MessageHolder> processRequest(Update update) throws TelegramApiException {
        return null;
    }

    @Override
    public Map<String, String> getCommands() {
        return null;
    }
}
