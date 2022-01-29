package com.teleBot.processor.registration.car;

import com.teleBot.model.Car;
import com.teleBot.model.CommandType;
import com.teleBot.model.KeyBoardType;
import com.teleBot.model.MessageHolder;
import com.teleBot.model.User;
import com.teleBot.processor.IProcessor;
import com.teleBot.service.IUserService;
import com.teleBot.utils.CollectionUtils;
import com.teleBot.utils.Constants;
import com.teleBot.utils.JsonUtils;
import com.teleBot.utils.MessageUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 12/24/2021
 */
public class ModelRegistrationProcessor implements IProcessor {

    private IUserService userService;

    public ModelRegistrationProcessor(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public List<MessageHolder> processRequest(Update update) throws TelegramApiException {
        User user = userService.getUserByUpdateAndSaveIfNotExist(update);
        String brand = MessageUtils.getTextFromUpdate(update);
        Car car = new Car(brand, null, null, 0);
        user.getCars().add(car);
        userService.save(user);
        List<String> models = defineModels(brand);
        return Collections.singletonList(MessageUtils.uncheckableHolder(models, "Выбери модель",
                KeyBoardType.FOUR_ROW));
    }

    private List<String> defineModels(String brand) {
        switch (brand) {
            case "Toyota": return Arrays.asList("Camry", "Rav4", "Prado", "Land Cruiser");
            case "BMW" : return Arrays.asList("M3", "M5", "X5", "X6");
            case "Mercedes" : return Arrays.asList("ML", "GL", "GLK", "AMG");
            case "Audi" : return Arrays.asList("A4", "A6", "Q3", "Q5", "Q7");
            default:return Collections.singletonList("Модели не найдены");
        }
    }

    @Override
    public Map<String, String> getCommands() {
        return CollectionUtils.<String, String>mapBuilder()
                .withPair(Constants.EACH, CommandType.BRAND_REGISTRATION_PROCESSOR.name())
                .build();
    }
}
