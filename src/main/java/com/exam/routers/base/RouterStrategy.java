package com.exam.routers.base;

import java.util.Map;
import com.exam.routers.pojo.RouterInfo;
/**
 * AccountStrategy
 */
public interface RouterStrategy {

   public Map<RouterInfo,Router> routerFunc();

   
}