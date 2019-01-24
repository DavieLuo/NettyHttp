package com.exam.routers;

import java.util.HashMap;
import java.util.Map;

import com.exam.controllers.HomeController;

/**
 * HomeStrategy
 */
public class HomeStrategy extends AbstractStrategy {

    HomeStrategy(){
        super.initData(HomeController.class, InitMap());
    }
    public Map<String,String> InitMap() {
        Map<String,String> routerMap = new HashMap<>();
        routerMap.put("me", "home");
        return routerMap;
    }
    
   /*  @Override
    public void initData(Class<?> userController,Map<String,String> map) {
        super.initData(userController, map);

    } */
}