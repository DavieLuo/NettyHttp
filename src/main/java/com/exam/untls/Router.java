package com.exam.untls;

/**
 * Router
 */
public class Router {

    private String pathName;
    private String methodName;

    public Router(){}

    public Router(String pathName,String methodName){
        this.pathName=pathName;
        this.methodName=methodName;
    }

    /**
     * @param pathName the pathName to set
     */
    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    /**
     * @return the pathName
     */
    public String getPathName() {
        return pathName;
    }

    /**
     * @param methodName the methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    /**
     * @return the methodName
     */
    public String getMethodName() {
        return methodName;
    }
}