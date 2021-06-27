package com.teleBot.dagger;

import com.teleBot.application.TelegramBot;
import com.teleBot.helper.IDashboardHelper;
import com.teleBot.model.CommandKey;
import com.teleBot.model.CommandType;
import com.teleBot.processor.FileSender;
import com.teleBot.processor.IProcessor;
import com.teleBot.processor.IProcessorFactory;
import com.teleBot.processor.impl.DashboardProcessor;
import com.teleBot.processor.impl.ProcessorFactory;
import com.teleBot.processor.impl.StartProcessor;
import com.teleBot.service.IContextService;
import com.teleBot.service.IRoleFacade;
import com.teleBot.service.ISecretService;
import com.teleBot.service.IUserService;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

import javax.inject.Singleton;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/7/2021
 */
@Module(includes = {
        DaggerDaoProvider.class,
        DaggerHelperProvider.class,
        DaggerServiceProvider.class})
public class DaggerBotProvider {

    @Provides
    @Singleton
    public TelegramBot telegramBot(IProcessorFactory processorFactory, IContextService contextService,
                                   IRoleFacade roleFacade, IUserService userService,
                                   ISecretService secretService) {
        return new TelegramBot(processorFactory, contextService, roleFacade, userService, secretService);
    }

    @Provides
    @Singleton
    @IntoMap
    @CommandKey(CommandType.START)
    public IProcessor startProcessor(IContextService contextService, IRoleFacade roleFacade) {
        return new StartProcessor(contextService, roleFacade);
    }

    @Provides
    @Singleton
    @IntoMap
    @CommandKey(CommandType.DASHBOARD_PROCESSOR)
    public IProcessor dashboardProcessor(IContextService contextService) {
        return new DashboardProcessor(contextService);
    }

    @Provides
    @Singleton
    public FileSender messageExecutor(ISecretService secretService) {
        return new FileSender(secretService);
    }

    @Provides
    @Singleton
    public IProcessorFactory factory(Map<CommandType, IProcessor> processors,
                                     IContextService contextService) {
        return new ProcessorFactory(processors, contextService);
    }
}
