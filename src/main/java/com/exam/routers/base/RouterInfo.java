package com.exam.routers.base;

import io.netty.handler.codec.http.HttpMethod;

/**
 * RouterInfo
 * @author luo
 */
public class RouterInfo {


    private HttpMethod method;
    private Router router;
   
    public RouterInfo(){}
    
    public RouterInfo(HttpMethod method,Router router){
    
        this.method=method;
        this.router=router;
    }

  
    /**
     * @return the method
     */
    public HttpMethod getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    /**
     * @return the router
     */
    public Router getRouter() {
        return router;
    }

    /**
     * @param router the router to set
     */
    public void setRouter(Router router) {
        this.router = router;
    }


    
}