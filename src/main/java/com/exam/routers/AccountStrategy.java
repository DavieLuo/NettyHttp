package com.exam.routers;

import java.util.HashMap;
import java.util.Map;

import com.exam.controllers.UserController;
import com.exam.routers.base.Router;
import com.exam.routers.base.RouterStrategy;
import com.exam.routers.base.RouterInfo;

import io.netty.handler.codec.http.HttpMethod;

/**
 * AccountStrategy
 */
public class AccountStrategy implements RouterStrategy {

    @Override
    public Map<String, Router> routerFunc() {
        Map<String,Router> map = new HashMap<>();
        map.put("/login", UserController::login);
        return map;
    }

  
   
  
    
}