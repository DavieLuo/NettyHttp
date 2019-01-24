package com.exam.routers;

import java.util.HashMap;
import java.util.Map;

/**
 * RouterContent
 */
public class RouterContext {

    private Map<String,RouterStrategy> routerMap = new HashMap<>();

    public void registRouter(String name,RouterStrategy strategy) {
        routerMap.put(name, strategy);
    }

    public Object handler(String pathName,String methodName,Object map) {
        if(isHas(pathName))
            return  getStrategy(pathName).resolveurl(methodName, map);
        return null;
    }
    
    private boolean isHas(String name){
        return routerMap.containsKey(name);
    }

    private RouterStrategy getStrategy(String name) {
        return routerMap.get(name);
    }
}