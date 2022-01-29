package com.teleBot.model;

import java.util.Map;

/**
 * @author Serhii_Udaltsov on 12/23/2021
 */
public class CallBack {

    private CommandType commandType;
    private Map<String, String> params;

    public CallBack() {
    }

    public CallBack(CommandType commandType, Map<String, String> params) {
        this.commandType = commandType;
        this.params = params;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }


}
