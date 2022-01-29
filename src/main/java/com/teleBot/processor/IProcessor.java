package com.teleBot.processor;

import com.teleBot.model.ButtonsType;
import com.teleBot.model.CommandType;
import com.teleBot.model.MessageHolder;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/7/2021
 */
public interface IProcessor {

    List<MessageHolder> processRequest(Update update) throws TelegramApiException;

    Map<String, String> getCommands();

    default ButtonsType getButtonsType() {
        return ButtonsType.KEYBOARD;
    }
}
