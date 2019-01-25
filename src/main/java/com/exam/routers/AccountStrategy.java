package com.exam.routers;

import java.util.HashMap;
import java.util.Map;

import com.exam.controllers.UserController;
import com.exam.routers.base.RouterStrategy;

/**
 * AccountStrategy
 */
public class AccountStrategy extends RouterStrategy{

  
    @Override
    public Map<String,String> initData() {
        Map<String,String> routerMap = new HashMap<>();
        routerMap.put("/login", "login");
        return routerMap;
    }

    @Override
    public Class<?> getExcuteClass() {
        return UserController.class;
    }

  
    
}