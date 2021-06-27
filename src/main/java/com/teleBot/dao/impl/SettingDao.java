package com.teleBot.dao.impl;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.teleBot.dao.IDynamoDbFactory;
import com.teleBot.dao.ISettingDao;
import com.teleBot.model.Role;
import com.teleBot.utils.CollectionUtils;
import com.teleBot.utils.JsonUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 6/5/2021
 */
public class SettingDao implements ISettingDao {

    private static final String TABLE_NAME = "Settings";
    private static final String ROLES = "ROLES";
    private static final TypeReference INTEGER_TYPE = new TypeReference<Integer>() {};
    private static final TypeReference ROLES_MAP_TYPE = new TypeReference<Map<Role, List<String>>>() {};

    private Table TABLE;

    public SettingDao(IDynamoDbFactory dynamoDbFactory) {
        TABLE = dynamoDbFactory.buildDynamo().getTable(TABLE_NAME);
    }

    @Override
    public Map<Role, List<String>> getRoleMapping() {
        Map roleMapping = getOrDefault(ROLES, ROLES_MAP_TYPE, Collections.emptyMap());
        if (CollectionUtils.isEmpty(roleMapping)) {
            throw new IllegalStateException("RoleMapping not found");
        }
        return roleMapping;
    }

    private <T> T getOrDefault(String keyName, TypeReference<T> typeReference, T defaultValue) {
        Item settingItem = TABLE.getItem("key", keyName);
        if (settingItem == null) {
            return defaultValue;
        }
        Object value = settingItem.get("v");
        JsonNode jsonNode = JsonUtils.parseObjectToJsonNode(value);
        return JsonUtils.parseJsonNode(jsonNode, typeReference);
    }
}
