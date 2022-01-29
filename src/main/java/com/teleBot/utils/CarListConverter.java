package com.teleBot.utils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.teleBot.model.Car;

import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 12/24/2021
 */
public class CarListConverter implements DynamoDBTypeConverter<List<Car>, List<Map<String, String>>> {

    @Override
    public List<Car> convert(List<Map<String, String>> object) {
        return null;
    }

    @Override
    public List<Map<String, String>> unconvert(List<Car> object) {
        return null;
    }
}
