package com.teleBot.application;

import com.teleBot.exception.ExceptionHandler;
import com.teleBot.model.Context;
import com.teleBot.model.MessageHolder;
import com.teleBot.model.User;
import com.teleBot.processor.IProcessor;
import com.teleBot.processor.IProcessorFactory;
import com.teleBot.service.IContextService;
import com.teleBot.service.IRoleFacade;
import com.teleBot.service.ISecretService;
import com.teleBot.service.IUserService;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/7/2021
 */
public class TelegramBot extends TelegramLongPollingBot {

    private IProcessorFactory factory;
    private IContextService contextService;
    private IRoleFacade roleFacade;
    private IUserService userService;
    private ISecretService secretService;
    private Map<Long, User> usersCache = new HashMap<>();

    public TelegramBot(IProcessorFactory factory, IContextService contextService, IRoleFacade roleFacade,
                       IUserService userService, ISecretService secretService) {
        this.factory = factory;
        this.contextService = contextService;
        this.roleFacade = roleFacade;
        this.userService = userService;
        this.secretService = secretService;
    }

    @Override
    public String getBotToken() {
        return secretService.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        List<BotApiMethod> messages = new ArrayList<>();
        long userId = MessageUtils.getUserIdFromUpdate(update);
        try {
            User userFromCache = usersCache.get(userId);
            if (userFromCache == null) {
                User userFromDb = userService.getUserById(userId);
                if (userFromDb == null) {
                    execute(new SendMessage(String.valueOf(userId), "Права на использование бота отсутствуют"));
                    return;
                }
                usersCache.put(userId, userFromDb);
            }
            Context context = contextService.getContext(update);
            IProcessor processor = factory.getProcessor(update, context);
            List<MessageHolder> result = processor.processRequest(update);
            messages = roleFacade.filterByNotAllowed(result, userId);
            fillAndUpdateContext(update, processor);
        } catch (Exception e) {
            messages.add(ExceptionHandler.handle(e, userId));
            e.printStackTrace();
        }
        try {
            for (BotApiMethod message : messages) {
                execute(message);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void fillAndUpdateContext(Update update, IProcessor processor) {
        Map<String, String> commands = processor.getCommands();
        contextService.updateContextCommands(commands, update);
    }

    @Override
    public String getBotUsername() {
        return "FrameBot";
    }
}
