package com.exam.routers.pojo;

import java.lang.reflect.Method;

import io.netty.handler.codec.http.HttpMethod;

/**
 * RouterInfo
 * @author luo
 */
public class RouterInfo {

    private String reqUrl;
    private HttpMethod method;
   
 

    public RouterInfo(){}
    
    public RouterInfo(String reqUrl,HttpMethod method){
        this.reqUrl=reqUrl;
        this.method=method;
    }

    /**
     * @return the reqUrl
     */
    public String getReqUrl() {
        return reqUrl;
    }

    /**
     * @param reqUrl the reqUrl to set
     */
    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
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


    
}