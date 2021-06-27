package com.teleBot.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.teleBot.utils.CommandTypeConverter;
import com.teleBot.utils.MapObjectConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/10/2021
 */
@DynamoDBTable(tableName = "Context")
public class Context {
    public static final String HASH_KEY = "id";
    public static final String PARAMS_FIELD = "p";
    public static final String LOCATION_FIELD = "l";

    @DynamoDBHashKey(attributeName = HASH_KEY)
    private long userId;

    @DynamoDBAttribute(attributeName = PARAMS_FIELD)
    @DynamoDBTypeConverted(converter = MapObjectConverter.class)
    private Map<String, Object> params;

    @DynamoDBTypeConverted(converter = CommandTypeConverter.class)
    @DynamoDBAttribute(attributeName = LOCATION_FIELD)
    private List<CommandType> location = new ArrayList<>();

    public Context() {
    }

    public Context(int userId, Map<String, Object> params, List<CommandType> location) {
        this.userId = userId;
        this.params = params;
        this.location = location;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<CommandType> getLocation() {
        return location;
    }

    public void setLocation(List<CommandType> location) {
        this.location = location;
    }
}
