package com.teleBot.service.impl;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import com.amazonaws.services.simplesystemsmanagement.model.Parameter;
import com.teleBot.service.ISecretService;
import com.teleBot.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 6/12/2021
 */
public class SecretService implements ISecretService {
    private static final String BOT_PARAMETER_NAME = "botToken";

    private AWSSimpleSystemsManagement manager;
    private Map<String, String> paramsCache = new HashMap<>();

    public SecretService(AWSSimpleSystemsManagement manager) {
        this.manager = manager;
    }

    @Override
    public String getBotToken() {
        String tokenFromCache = paramsCache.get(BOT_PARAMETER_NAME);
        if (!StringUtils.isBlank(tokenFromCache)) {
            return tokenFromCache;
        }
        GetParameterRequest request = new GetParameterRequest();
        request.setName(BOT_PARAMETER_NAME);
        request.withWithDecryption(true);
        GetParameterResult result = manager.getParameter(request);
        Parameter parameter = result.getParameter();
        String tokenFromDb = parameter.getValue();
        paramsCache.put(BOT_PARAMETER_NAME, tokenFromDb);
        return tokenFromDb;
    }
}
