package com.exam.routers;

import java.util.HashMap;
import java.util.Map;

import com.exam.controllers.HomeController;
import com.exam.routers.base.RouterStrategy;

/**
 * HomeStrategy
 */
public class HomeStrategy extends RouterStrategy {



    @Override
    public Map<String,String> initData() {
        Map<String,String> routerMap = new HashMap<>();
        routerMap.put("/me", "home");
        return routerMap;

    }

    @Override
    public Class<?> getExcuteClass() {
        return HomeController.class;
    }


}