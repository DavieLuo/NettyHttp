package com.exam.routers.pojo;

import java.lang.reflect.Method;

/**
 * RouterInfo
 * @author luo
 */
public class RouterInfo {


    private Method method;
    private Class<?> classType;
    private Class<?>[] param;
 

    public RouterInfo(){}
    
    public RouterInfo(Method method,Class<?> classType,Class<?>[] param){
        this.method=method;
        this.classType=classType;
        this.param=param;
    }

    /**
     * @return the method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * @return the classType
     */
    public Class<?> getClassType() {
        return classType;
    }

    /**
     * @param classType the classType to set
     */
    public void setClassType(Class<?> classType) {
        this.classType = classType;
    }

    /**
     * @return the param
     */
    public Class<?>[] getParam() {
        return param;
    }

    /**
     * @param param the param to set
     */
    public void setParam(Class<?>[] param) {
        this.param = param;
    }

    
}