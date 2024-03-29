package com.teleBot.utils;

import com.teleBot.model.KeyBoardType;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/8/2021
 */
public class KeyBoardUtils {
    public static InlineKeyboardMarkup buildInlineKeyboard(Map<String, String> buttonsMap, KeyBoardType type) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        for (Map.Entry<String, String> entry : buttonsMap.entrySet()) {
            if (rowInline.size() == type.getElementsInRow()) {
                rowsInline.add(rowInline);
                rowInline = new ArrayList<>();
            }
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(entry.getKey());
            button.setCallbackData(entry.getValue());
            rowInline.add(button);
        }
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public static ReplyKeyboardMarkup buildReplyKeyboard(Map<String, String> buttonsMap, KeyBoardType type) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        for (Map.Entry<String, String> entry : buttonsMap.entrySet()) {
            KeyboardButton button = new KeyboardButton();
            button.setText(entry.getKey());
            row.add(button);
            if (row.size() == type.getElementsInRow()) {
                rows.add(row);
                row = new KeyboardRow();
            }
        }
        if (row.size() < type.getElementsInRow()) {
            rows.add(row);
        }
        keyboard.setKeyboard(rows);
        return keyboard;
    }

}
