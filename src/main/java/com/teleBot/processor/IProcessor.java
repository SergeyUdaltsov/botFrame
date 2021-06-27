package com.teleBot.processor;

import com.teleBot.model.CommandType;
import com.teleBot.model.MessageHolder;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

/**
 * @author Serhii_Udaltsov on 4/7/2021
 */
public interface IProcessor {

    MessageHolder processRequest(Update update) throws TelegramApiException;

    CommandType getNextCommandType();
}
