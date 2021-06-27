package com.teleBot.service;

import com.teleBot.model.Role;

import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 5/3/2021
 */
public interface ISettingService {

    Map<Role, List<String>> getRoleMapping();
}
