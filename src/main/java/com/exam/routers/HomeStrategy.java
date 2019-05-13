package com.exam.routers;

import java.util.HashMap;
import java.util.Map;

import com.exam.controllers.HomeController;
import com.exam.routers.base.Router;
import com.exam.routers.base.RouterStrategy;
import com.exam.routers.base.RouterInfo;

import io.netty.handler.codec.http.HttpMethod;

/**
 * HomeStrategy
 */
public class HomeStrategy implements RouterStrategy {

    @Override
    public Map<String, Router> routerFunc() {
        HomeController homeC = new HomeController();
        Map<String, Router> map = new HashMap<>();        
        map.put("/home",homeC::home);
        return map;
    }

}