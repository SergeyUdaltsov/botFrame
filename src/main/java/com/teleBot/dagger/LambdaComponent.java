package com.teleBot.dagger;

import com.teleBot.lambda.BotLambda;
import dagger.Component;

import javax.inject.Singleton;

/**
 * @author Serhii_Udaltsov on 4/7/2021
 */
@Component(modules = {
        DaggerBotProvider.class,
        DaggerServiceProvider.class
})
@Singleton
public interface LambdaComponent {

    void inject(BotLambda lambda);
}
