package com.teleBot.service.impl;

import com.teleBot.dao.ISettingDao;
import com.teleBot.model.Role;
import com.teleBot.service.ISettingService;

import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 6/5/2021
 */
public class SettingService implements ISettingService {

    private ISettingDao settingDao;

    public SettingService(ISettingDao settingDao) {
        this.settingDao = settingDao;
    }

    @Override
    public Map<Role, List<String>> getRoleMapping() {
        return settingDao.getRoleMapping();
    }
}
