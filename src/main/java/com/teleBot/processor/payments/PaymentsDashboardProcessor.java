package com.teleBot.processor.payments;

import com.teleBot.model.CommandType;
import com.teleBot.model.KeyBoardType;
import com.teleBot.model.MessageHolder;
import com.teleBot.processor.IProcessor;
import com.teleBot.utils.Buttons;
import com.teleBot.utils.CollectionUtils;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 12/20/2021
 */
public class PaymentsDashboardProcessor implements IProcessor {

    @Override
    public List<MessageHolder> processRequest(Update update) throws TelegramApiException {
        MessageHolder holder = MessageUtils.holder(Buttons.Payments.BUTTONS,
                "Выбери раздел", KeyBoardType.VERTICAL, true, true);
        holder.setButtonsType(getButtonsType());
        return Collections.singletonList(holder);
    }

    @Override
    public Map<String, String> getCommands() {
        return CollectionUtils.<String, String>mapBuilder()
                .withPair("Провести", CommandType.PAYMENT_YEAR_SELECT_PROCESSOR.name())
                .withPair("Редактировать", CommandType.BALANCE_PROCESSOR.name())
                .withPair("Провести льготный", CommandType.BILLS_PROCESSOR.name())
                .withPair("Показать последние", CommandType.PLAYERS_PROCESSOR.name())
                .withPair("Файл с платежами", CommandType.TRANSFERS_PROCESSOR.name())
                .withPair("Назад", CommandType.START.name())
                .build();
    }
}
