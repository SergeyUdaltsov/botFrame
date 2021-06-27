package com.teleBot.helper;

import com.teleBot.model.MessageHolder;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Serhii_Udaltsov on 4/18/2021
 */
public interface IHelper {

    MessageHolder getMessage(Update update);

}
