package com.teleBot.utils;

import com.teleBot.model.Button;
import com.teleBot.model.ButtonsType;
import com.teleBot.model.KeyBoardType;
import com.teleBot.model.MessageHolder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Serhii_Udaltsov on 4/12/2021
 */
public class MessageUtils {

    public static String getTextFromUpdate(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        return callbackQuery != null ? callbackQuery.getData() : update.getMessage().getText();
    }

    public static long getUserIdFromUpdate(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        return callbackQuery != null
                ? callbackQuery.getFrom().getId()
                : update.getMessage().getFrom().getId();
    }

    public static Map<String, String> commonButtonsMap() {
        Map<String, String> buttons = new HashMap<>();
        buttons.put("Назад", "Назад");
        buttons.put("Главная", "Главная");
        return buttons;
    }

    public static List<String> dashboardButtons() {
        return Arrays.asList("Платежи", "Начисления", "Баланс", "Игроки", "Переводы");
    }

    public static MessageHolder buildDashboardHolder() {
        return holder(dashboardButtons(), "Выбери раздел", KeyBoardType.TWO_ROW, true, false);
    }

    public static User getUserNameFromUpdate(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        if (callbackQuery != null) {
            return callbackQuery.getFrom();
        }
        return update.getMessage().getFrom();
    }

    public static Map<String, String> buildButtons(List<Button> buttons, boolean withCommon) {
        Map<String, String> resultMap = new LinkedHashMap<>();
        for (Button button : buttons) {

            resultMap.put(button.getValue(), StringUtils.isBlank(button.getCallback())
                    ? button.getValue()
                    : button.getCallback());
        }
        if (withCommon) {
            resultMap.putAll(commonButtonsMap());
        }
        return resultMap;
    }

    public static SendMessage buildMessage(List<Button> buttons, MessageHolder holder, long operatorId) {
        SendMessage sendMessage = new SendMessage(String.valueOf(operatorId), holder.getMessage());
        Map<String, String> buttonsMap = buildButtons(buttons, holder.isWithCommonButtons());
        ReplyKeyboard keyboard = holder.getButtonsType()
                .getButtonsFunction()
                .apply(buttonsMap, holder.getKeyBoardType());

        sendMessage.setReplyMarkup(keyboard);
        return sendMessage;
    }

    public static MessageHolder commonCheckableVerticalHolder(List<String> titles, String message) {
        return commonCheckableHolder(titles, message, KeyBoardType.VERTICAL);
    }

    public static MessageHolder commonUnCheckableVerticalHolder(List<String> titles, String message) {
        return commonHolder(titles, message, KeyBoardType.VERTICAL, false);
    }

    public static MessageHolder commonCheckableHolder(List<String> titles, String message, KeyBoardType type) {
        return commonHolder(titles, message, type, true);
    }

    public static MessageHolder uncheckableHolder(List<String> titles, String message, KeyBoardType type) {
        return holder(titles, message, type, false, false);
    }

    public static MessageHolder commonHolder(List<String> titles, String message, KeyBoardType type,
                                             boolean isCheckable) {
        return holder(titles, message, type, isCheckable, true);
    }

    public static MessageHolder holder(List<String> titles, String message, KeyBoardType type,
                                       boolean isCheckable, boolean withCommonButtons) {
        return MessageHolder.builder()
                .withCommonButtons(withCommonButtons)
                .withMessage(message)
                .withButtons(commonButtons(titles, isCheckable))
                .withKeyboardType(type)
                .withButtonsType(ButtonsType.INLINE)
                .build();
    }

    public static List<Button> commonButtons(List<String> titles, boolean isCheckable) {
        return titles.stream()
                .map(t -> Button.builder()
                        .withCheckable(isCheckable)
                        .withValue(t)
                        .build())
                .collect(Collectors.toList());
    }
}
