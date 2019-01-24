package com.exam.routers;

import java.util.HashMap;
import java.util.Map;

import com.exam.controllers.UserController;

/**
 * AccountStrategy
 */
public class AccountStrategy extends AbstractStrategy{

    
    public AccountStrategy() {
        initData(UserController.class,InitMap());
    }

    @Override
    public void initData(Class<?> userController,Map<String,String> map) {
        super.initData(userController, map);

    }

    public Map<String,String> InitMap() {
        Map<String,String> routerMap = new HashMap<>();
        routerMap.put("login", "login");
        return routerMap;
    }

  
    
}