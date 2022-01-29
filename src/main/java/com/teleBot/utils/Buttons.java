package com.teleBot.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author Serhii_Udaltsov on 12/20/2021
 */
public interface Buttons {

    interface Year {
    }

    interface Payments {
        List<String> BUTTONS = Arrays.asList("Провести", "Редактировать", "Провести Льготный", "Файл с платежами");
    }

}
