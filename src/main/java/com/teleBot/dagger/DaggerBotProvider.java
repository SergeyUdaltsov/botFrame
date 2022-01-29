package com.teleBot.dagger;

import com.teleBot.application.TelegramBot;
import com.teleBot.model.CommandKey;
import com.teleBot.model.CommandType;
import com.teleBot.processor.FileSender;
import com.teleBot.processor.IProcessor;
import com.teleBot.processor.IProcessorFactory;
import com.teleBot.processor.payments.PaymentTypeSelectProcessor;
import com.teleBot.processor.players.PaymentPlayerSelectProcessor;
import com.teleBot.processor.registration.car.BrandRegistrationProcessor;
import com.teleBot.processor.registration.car.ModelRegistrationProcessor;
import com.teleBot.processor.year.PaymentYearSelectProcessor;
import com.teleBot.processor.payments.PaymentsDashboardProcessor;
import com.teleBot.processor.common.ProcessorFactory;
import com.teleBot.processor.common.StartProcessor;
import com.teleBot.service.IContextService;
import com.teleBot.service.IPlayerService;
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
    public IProcessor startProcessor(IContextService contextService) {
        return new StartProcessor(contextService);
    }

    @Provides
    @Singleton
    @IntoMap
    @CommandKey(CommandType.PAYMENTS_PROCESSOR)
    public IProcessor paymentProcessor() {
        return new PaymentsDashboardProcessor();
    }

    @Provides
    @Singleton
    @IntoMap
    @CommandKey(CommandType.BRAND_REGISTRATION_PROCESSOR)
    public IProcessor brandRegistrationProcessor(IUserService userService) {
        return new BrandRegistrationProcessor(userService);
    }

    @Provides
    @Singleton
    @IntoMap
    @CommandKey(CommandType.MODEL_REGISTRATION_PROCESSOR)
    public IProcessor modelRegistrationProcessor(IUserService userService) {
        return new ModelRegistrationProcessor(userService);
    }

    @Provides
    @Singleton
    @IntoMap
    @CommandKey(CommandType.PAYMENT_YEAR_SELECT_PROCESSOR)
    public IProcessor paymentYearSelectProcessor(IUserService userService) {
        return new PaymentYearSelectProcessor(userService);
    }

    @Provides
    @Singleton
    @IntoMap
    @CommandKey(CommandType.PAYMENT_PLAYER_SELECT_PROCESSOR)
    public IProcessor paymentPlayerSelectProcessor(IPlayerService playerService) {
        return new PaymentPlayerSelectProcessor(playerService);
    }

    @Provides
    @Singleton
    @IntoMap
    @CommandKey(CommandType.PAYMENT_TYPE_SELECT_PROCESSOR)
    public IProcessor paymentTypeSelectProcessor() {
        return new PaymentTypeSelectProcessor();
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
