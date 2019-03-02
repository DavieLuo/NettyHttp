package com.exam.routers;

import java.util.HashMap;
import java.util.Map;

import com.exam.routers.base.Router;
import com.exam.routers.base.RouterStrategy;
import com.exam.routers.pojo.RouterInfo;

/**
 * HomeStrategy
 */
public class HomeStrategy implements RouterStrategy {

    @Override
    public Map<RouterInfo, Router> routerFunc() {
        Map<RouterInfo, Router> map = new HashMap<>();
        return map;
    }

}