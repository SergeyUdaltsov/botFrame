package com.teleBot.processor.year;

import com.teleBot.model.CommandType;
import com.teleBot.processor.IProcessor;
import com.teleBot.utils.CollectionUtils;
import com.teleBot.utils.Constants;

import java.util.Map;

/**
 * @author Serhii_Udaltsov on 12/20/2021
 */
public abstract class YearSelectProcessor implements IProcessor {

    protected abstract CommandType getEachCommandType();

    protected abstract CommandType getBackCommandType();

    @Override
    public Map<String, String> getCommands() {
        return CollectionUtils.<String, String>mapBuilder()
                .withPair(Constants.EACH, getEachCommandType().name())
                .withPair("Назад", getBackCommandType().name())
                .build();
    }
}
