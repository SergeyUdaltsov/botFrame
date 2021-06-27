package com.teleBot.model;

/**
 * @author Serhii_Udaltsov on 4/10/2021
 */
public enum CommandType {
    START,
    DASHBOARD_PROCESSOR,
    PAYMENT_SELECT,
    PAYMENT_LIST,
    PAYMENT_ACTION_SELECT,
    PAYMENT_EDIT_SELECT,
    PAYMENT_EDIT_EXECUTE,
    PAYMENT_EDIT_EXECUTE_SAVE,
    PAYMENT_REMOVE,
    PAYMENT_CREATE,
    PAYMENT_DESCRIBE,
    PLAYER,
    PLAYER_PROCESSOR,
    PLAYER_SELECT,
    PLAYER_CREATE,
    PLAYER_SAVE,
    PLAYER_REMOVE,
    PLAYER_EDIT,
    PLAYER_EDIT_EXECUTE,
    PLAYER_LIST,
    PLAYER_ACTION_EXECUTE,
    YEAR_SELECT,
    BALANCE_SELECT,
    BALANCE_SEND,
    BALANCE_EXECUTE,
    BILL_EXECUTE,
    BILL_EDIT,
    BILL_SUNDAY_ICE_EXECUTE,
    BILL_SUNDAY_ICE_SELECT,
    BILL_EDIT_TYPE_SELECT,
    BILL_EDIT_EXECUTE_SAVE,
    BILL_EDIT_EXECUTE,
    BILL_SELECT,
    BILL_COACH,
    BILL_MONTHLY_ICE,
    BILL_SUNDAY_ICE,
    TRANSFER_SELECT,
    TRANSFER_CREATE,
    TRANSFER_BUILD,
    TRANSFER_REMOVE,
    TRANSFER_EXECUTE;

    public static CommandType fromValue(String value) {
        for (CommandType commandType : values()) {
            if (commandType.name().equalsIgnoreCase(value)) {
                return commandType;
            }
        }
        throw new IllegalArgumentException("Command not found for value " + value);
    }
}
