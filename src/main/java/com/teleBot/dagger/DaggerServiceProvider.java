package com.teleBot.dagger;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.teleBot.dao.IContextDao;
import com.teleBot.dao.IPlayerDao;
import com.teleBot.dao.ISettingDao;
import com.teleBot.dao.IUserDao;
import com.teleBot.dao.IUserSessionDao;
import com.teleBot.service.IContextService;
import com.teleBot.service.IPlayerService;
import com.teleBot.service.IRoleFacade;
import com.teleBot.service.IS3Service;
import com.teleBot.service.ISecretService;
import com.teleBot.service.ISettingService;
import com.teleBot.service.IUserService;
import com.teleBot.service.IUserSessionService;
import com.teleBot.service.impl.ContextService;
import com.teleBot.service.impl.PlayerService;
import com.teleBot.service.impl.RoleFacade;
import com.teleBot.service.impl.S3Service;
import com.teleBot.service.impl.SecretService;
import com.teleBot.service.impl.SettingService;
import com.teleBot.service.impl.UserService;
import com.teleBot.service.impl.UserSessionService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * @author Serhii_Udaltsov on 4/10/2021
 */
@Module(includes = {DaggerDaoProvider.class})
public class DaggerServiceProvider {

    @Provides
    @Singleton
    public ISettingService settingService(ISettingDao settingDao) {
        return new SettingService(settingDao);
    }

    @Provides
    @Singleton
    public AmazonS3 s3Client() {
        return AmazonS3Client.builder().withRegion("eu-central-1").build();
    }

    @Provides
    @Singleton
    public IS3Service s3Service(AmazonS3 client) {
        return new S3Service(client);
    }

    @Provides
    @Singleton
    public IContextService contextService(IContextDao contextDao) {
        return new ContextService(contextDao);
    }

    @Provides
    @Singleton
    public IUserService userService(IUserDao userDao) {
        return new UserService(userDao);
    }

    @Provides
    @Singleton
    public IPlayerService playerService(IPlayerDao playerDao) {
        return new PlayerService(playerDao);
    }

    @Provides
    @Singleton
    public IUserSessionService sessionService(IUserSessionDao sessionDao, ISettingService settingService,
                                              IUserService userService) {
        return new UserSessionService(sessionDao, settingService, userService);
    }

    @Provides
    @Singleton
    public IRoleFacade roleFacade(IUserSessionService sessionService) {
        return new RoleFacade(sessionService);
    }

    @Provides
    @Singleton
    public ISecretService secretService () {
        AWSSimpleSystemsManagement management = AWSSimpleSystemsManagementClientBuilder.defaultClient();
        return new SecretService(management);
    }
}
