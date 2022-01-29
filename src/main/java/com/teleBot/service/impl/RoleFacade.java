package com.teleBot.service.impl;

import com.teleBot.model.Button;
import com.teleBot.model.MessageHolder;
import com.teleBot.service.IRoleFacade;
import com.teleBot.service.IUserSessionService;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Serhii_Udaltsov on 6/6/2021
 */
public class RoleFacade implements IRoleFacade {

    private IUserSessionService sessionService;

    public RoleFacade(IUserSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public boolean isActionAllowed(String action, long operatorId) {
        List<String> userActions = sessionService.getUserActionList(operatorId);
        return userActions.contains(action);
    }

    @Override
    public List<BotApiMethod> filterByNotAllowed(List<MessageHolder> holders, long operatorId) {
        List<BotApiMethod> messages = new ArrayList<>();
        for (MessageHolder holder : holders) {
            List<Button> buttons = holder.getButtons();
            System.out.println("Actions before filtering-----");
            buttons.stream().map(Button::getValue).forEach(System.out::println);
//            buttons.removeIf(b -> b.isCheckable() && !isActionAllowed(b.getValue(), operatorId));
            System.out.println("Actions after filtering-----");
            buttons.stream().map(Button::getValue).forEach(System.out::println);
            messages.add(MessageUtils.buildMessage(buttons, holder, operatorId));
        }
        return messages;
    }
}
