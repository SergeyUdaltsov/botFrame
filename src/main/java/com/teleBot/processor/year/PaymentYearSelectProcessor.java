package com.teleBot.processor.year;

import com.teleBot.model.ButtonsType;
import com.teleBot.model.CommandType;
import com.teleBot.model.MessageHolder;
import com.teleBot.service.IUserService;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.List;

/**
 * @author Serhii_Udaltsov on 12/20/2021
 */
public class PaymentYearSelectProcessor extends YearSelectProcessor {

    private IUserService userService;

    public PaymentYearSelectProcessor(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public List<MessageHolder> processRequest(Update update) throws TelegramApiException {
        MessageHolder messageHolder = userService.buildYearMessageHolder(MessageUtils.getUserIdFromUpdate(update));
        messageHolder.setButtonsType(ButtonsType.INLINE);
        return Collections.singletonList(messageHolder);
    }

    @Override
    protected CommandType getBackCommandType() {
        return CommandType.PAYMENTS_PROCESSOR;
    }

    @Override
    protected CommandType getEachCommandType() {
        return CommandType.PAYMENT_PLAYER_SELECT_PROCESSOR;
    }
}
