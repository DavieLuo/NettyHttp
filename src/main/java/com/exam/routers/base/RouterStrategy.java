package com.exam.routers.base;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.exam.routers.base.StateContent.MState;
import com.exam.routers.pojo.RouterInfo;

/**
 * AccountStrategy
 */
public abstract class RouterStrategy implements MainStrategy<RouterInfo>{

    //保存 url -> func 的映射
    public Map<String,String> routerMap= new ConcurrentHashMap<>();

    /**
     * 获取执行的class
     * @return
     */
    public abstract Class<?> getExcuteClass();


    /**
     * 初始化 url->func 数据
     */
    protected abstract Map<String,String> initData();


    @Override
    public RouterInfo resolveurl(String url,StateContent content) {
        try {
            if(has(url)){
              return  handler(getValue(url), content);
            }
            content.setState(MState.PATH_FAIL);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否存在 url
     */
    public boolean has(String key) {
        if(key==null||key.equals(""))
            return false;
        return initData().containsKey(key);
    }

    /**
     * 获取funcname
     */
    public String getValue(String key){
        return initData().get(key);
    }


    /**
     * 判断实体类是否有该方法 
     */
    public RouterInfo handler(String methodName,StateContent content) throws Exception{
     //   Class mclass = Class.forName(className);
        Class<?> mclass = getExcuteClass();
        Method[] methods =mclass.getMethods();
        for (Method method: methods) {
            if(method.getName().equals(methodName)){
                RouterInfo  routerInfo =new RouterInfo(method,mclass,method.getParameterTypes());
                content.setState(MState.NO_SUCH_METHOD);
                return routerInfo;
            }
        }
       return null;
    }
}