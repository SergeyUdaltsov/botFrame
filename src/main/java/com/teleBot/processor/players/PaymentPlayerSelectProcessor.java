package com.teleBot.processor.players;

import com.teleBot.model.CommandType;
import com.teleBot.model.KeyBoardType;
import com.teleBot.model.MessageHolder;
import com.teleBot.model.Player;
import com.teleBot.service.IPlayerService;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Serhii_Udaltsov on 12/20/2021
 */
public class PaymentPlayerSelectProcessor extends PlayerSelectProcessor {

    private IPlayerService playerService;

    public PaymentPlayerSelectProcessor(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public List<MessageHolder> processRequest(Update update) throws TelegramApiException {
        int year = Integer.parseInt(MessageUtils.getTextFromUpdate(update));
        List<Player> playersByYear = playerService.getPlayersByYear(year);
        List<String> titles = playersByYear.stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        MessageHolder holder = MessageUtils.holder(titles, "Выбери игрока", KeyBoardType.VERTICAL, false, true);
        holder.setButtonsType(getButtonsType());
        return Collections.singletonList(holder);
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.PAYMENT_TYPE_SELECT_PROCESSOR;
    }
}
