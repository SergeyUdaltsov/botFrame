package com.teleBot.exception;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Serhii_Udaltsov on 4/15/2021
 */
public class ExceptionHandler {
    public static BotApiMethod handle(Exception e, long userId) {
        return new SendMessage(String.valueOf(userId), e.getMessage());
    }
}
