package com.teleBot.model;

/**
 * @author Serhii_Udaltsov on 4/10/2021
 */
public enum CommandType {
    START,
    DASHBOARD_PROCESSOR,
    PAYMENTS_PROCESSOR,
    BRAND_REGISTRATION_PROCESSOR,
    MODEL_REGISTRATION_PROCESSOR,
    PAYMENT_YEAR_SELECT_PROCESSOR,
    PAYMENT_PLAYER_SELECT_PROCESSOR,
    PAYMENT_TYPE_SELECT_PROCESSOR,
    BALANCE_PROCESSOR,
    BILLS_PROCESSOR,
    PLAYERS_PROCESSOR,
    TRANSFERS_PROCESSOR,
    PAYMENT_SELECT;

    public static CommandType fromValue(String value) {
        for (CommandType commandType : values()) {
            if (commandType.name().equalsIgnoreCase(value)) {
                return commandType;
            }
        }
        System.out.println("Command not found for value " + value);
        return START;
    }
}
