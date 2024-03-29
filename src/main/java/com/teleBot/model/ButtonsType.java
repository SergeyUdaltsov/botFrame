package com.teleBot.model;

import com.teleBot.utils.KeyBoardUtils;
import com.teleBot.utils.MessageUtils;
import org.glassfish.jersey.internal.util.Producer;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author Serhii_Udaltsov on 12/24/2021
 */
public enum ButtonsType {
    KEYBOARD(KeyBoardUtils::buildReplyKeyboard),
    INLINE(KeyBoardUtils::buildInlineKeyboard);

    private BiFunction<Map<String, String>, KeyBoardType, ReplyKeyboard> buttonsFunction;

    ButtonsType(BiFunction<Map<String, String>, KeyBoardType, ReplyKeyboard> buttonsFunction) {
        this.buttonsFunction = buttonsFunction;
    }

    public BiFunction<Map<String, String>, KeyBoardType, ReplyKeyboard> getButtonsFunction() {
        return buttonsFunction;
    }
}
