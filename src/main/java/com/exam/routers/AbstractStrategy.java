package com.exam.routers;

import java.util.HashMap;
import java.util.Map;

/**
 * AccountStrategy
 */
public abstract class AbstractStrategy implements RouterStrategy<Map<String,Object>>{

    private Map<String,String> routerMap = new HashMap<>();

    private Class<?> mclass;

    protected void initData(Class<?> classType,Map<String,String> map) {
        this.mclass=classType;
        this.routerMap=map;
    }

    @Override
    public Object resolveurl(String methodName,Map<String,Object> obj) {
        try {
            if(has(methodName)){
                System.out.println(methodName);
              return  handler(getValue(methodName),obj );
            }else{
                return "no such func";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean has(String key) {
        if(key==null||key.equals(""))
            return false;
        return routerMap.containsKey(key);
    }

    public String getValue(String methodName){
        return routerMap.get(methodName);
    }

    public Object handler(String mthod,Map<String,Object> param) throws Exception{
     //   Class mclass = Class.forName(className);
       return  mclass.getMethod(mthod,Map.class).invoke(mclass.newInstance(), param);
    }
}