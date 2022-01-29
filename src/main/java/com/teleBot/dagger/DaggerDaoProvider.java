package com.teleBot.dagger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.teleBot.dao.IContextDao;
import com.teleBot.dao.IDynamoDbFactory;
import com.teleBot.dao.IPlayerDao;
import com.teleBot.dao.ISettingDao;
import com.teleBot.dao.IUserDao;
import com.teleBot.dao.IUserSessionDao;
import com.teleBot.dao.impl.ContextDao;
import com.teleBot.dao.impl.DynamoDbFactory;
import com.teleBot.dao.impl.PlayerDao;
import com.teleBot.dao.impl.SettingDao;
import com.teleBot.dao.impl.UserDao;
import com.teleBot.dao.impl.UserSessionDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * @author Serhii_Udaltsov on 4/10/2021
 */
@Module
public class DaggerDaoProvider {

    @Provides
    @Singleton
    public AmazonDynamoDB dynamoDb() {
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();
        builder.withRegion("eu-central-1");
        return builder.build();
    }

    @Provides
    @Singleton
    public IContextDao contextDao(IDynamoDbFactory dynamoDbFactory) {
        return new ContextDao(dynamoDbFactory);
    }

    @Provides
    @Singleton
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB dynamoDb) {
        return new DynamoDBMapper(dynamoDb);
    }

    @Provides
    @Singleton
    public IDynamoDbFactory dynamoFactory(AmazonDynamoDB dynamoDb) {
        return new DynamoDbFactory(dynamoDb);
    }

    @Provides
    @Singleton
    public ISettingDao settingDao(IDynamoDbFactory dynamoDbFactory) {
        return new SettingDao(dynamoDbFactory);
    }

    @Provides
    @Singleton
    public IUserDao userDao(IDynamoDbFactory dynamoDbFactory) {
        return new UserDao(dynamoDbFactory);
    }

    @Provides
    @Singleton
    public IPlayerDao playerDao(IDynamoDbFactory dynamoDbFactory) {
        return new PlayerDao(dynamoDbFactory);
    }

    @Provides
    @Singleton
    public IUserSessionDao sessionDao(IDynamoDbFactory dynamoDbFactory) {
        return new UserSessionDao(dynamoDbFactory);
    }
}
