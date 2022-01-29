package com.teleBot.processor.common;

import com.teleBot.model.CommandType;
import com.teleBot.model.Context;
import com.teleBot.processor.IProcessor;
import com.teleBot.processor.IProcessorFactory;
import com.teleBot.service.IContextService;
import com.teleBot.utils.Constants;
import com.teleBot.utils.MessageUtils;
import com.teleBot.utils.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/7/2021
 */
public class ProcessorFactory implements IProcessorFactory {

    private Map<CommandType, IProcessor> processorsMap;
    private IContextService contextService;

    public ProcessorFactory(Map<CommandType, IProcessor> processorsMap, IContextService contextService) {
        this.processorsMap = processorsMap;
        this.contextService = contextService;
    }

    @Override
    public IProcessor getProcessor(Update update, Context context) {
        if (context == null || "главная".equalsIgnoreCase(MessageUtils.getTextFromUpdate(update))) {
            return processorsMap.get(CommandType.START);
        }

        String commandKey = contextService.getMessageText(update);
        System.out.println("Command key ----------- " + commandKey);
        String commandName = defineCommandTypeName(commandKey, context.getCommands());
        System.out.println("Command name ----------- " + commandName);
        CommandType commandType = CommandType.fromValue(commandName);
        return processorsMap.get(commandType);
    }

    private String defineCommandTypeName(String commandKey, Map<String, String> commandsMap) {
        if (Constants.BACK.equals(commandKey)) {
            return commandsMap.get(commandKey);
        }
        String defaultCommandName = commandsMap.get(Constants.EACH);
        return StringUtils.isBlank(defaultCommandName)
                ? commandsMap.get(commandKey)
                : defaultCommandName;
    }
}
