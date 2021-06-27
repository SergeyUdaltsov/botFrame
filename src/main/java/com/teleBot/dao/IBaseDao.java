package com.teleBot.dao;

import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.KeyAttribute;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;

import java.util.List;

/**
 * @author Serhii_Udaltsov on 4/10/2021
 */
public interface IBaseDao<ENTITY> {
    void save(ENTITY entity);

    void saveAll(List<Item> items);

    List<ENTITY> getAllEntities();

    List<ENTITY> findAllByQuery(QuerySpec querySpec);

    ENTITY getEntityByPrimaryKey(PrimaryKey primaryKey);

    ENTITY getEntityByQueryObject(ENTITY object);

    List<ENTITY> findAllByIndexQueryObject(String indexName, QuerySpec querySpec);

    List<Item> getItemsByIndex(String indexName, String hashKeyName, String hashKeyValue);

    void updateItem(PrimaryKey primaryKey, AttributeUpdate... attributeUpdates);

    List<ENTITY> findAllByKeyAttribute(KeyAttribute keyAttribute);

    void remove(ENTITY item);

    void removeAll(List<PrimaryKey> primaryKeys);
}
