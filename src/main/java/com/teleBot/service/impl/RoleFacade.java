package com.teleBot.service.impl;

import com.teleBot.model.Button;
import com.teleBot.model.MessageHolder;
import com.teleBot.service.IRoleFacade;
import com.teleBot.service.IUserSessionService;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

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
    public SendMessage filterByNotAllowed(MessageHolder holder, long operatorId) {
        List<Button> buttons = holder.getButtons();
        System.out.println("Actions before filtering-----");
        buttons.stream().map(Button::getValue).forEach(System.out::println);
        List<String> titles = new ArrayList<>();
        for (Button button : buttons) {
            String value = button.getValue();
            if (!button.isCheckable() || button.isCheckable() && isActionAllowed(value, operatorId)) {
                titles.add(value);
            }
        }
        System.out.println("Actions after filtering-----");
        titles.forEach(System.out::println);
        return MessageUtils.buildMessage(titles, holder.getMessage(), operatorId, holder.getKeyBoardType(),
                holder.isWithCommonButtons());
    }
}
