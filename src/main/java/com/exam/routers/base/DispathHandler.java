package com.exam.routers.base;

import java.lang.reflect.InvocationTargetException;

import com.exam.routers.AccountStrategy;
import com.exam.routers.HomeStrategy;
import com.exam.routers.RouterContext;
import com.exam.routers.pojo.RouterInfo;
import com.exam.untls.CallBack;
import com.exam.untls.ResolveRequestData;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * DispathHandler
 */
public class DispathHandler {

    private StateContent content;

    public DispathHandler() {
        content = new StateContent();
        RouterContext.getInstance().registRouterStrategy(new AccountStrategy(),new HomeStrategy());
    }

    private RouterInfo checkUrl(String url) {
        return RouterContext.getInstance().checkUrl(url, content);
    }

    private Object handlerinvoke(RouterInfo rInfo,FullHttpRequest request) {
        try {
            if(rInfo==null){
                return CallBack.error(10, "No such func");
            }
           
            if(rInfo.getParam().length==0){
                return  rInfo.getMethod().invoke(rInfo.getClass().newInstance());
            }
            
            return rInfo.getMethod().invoke(rInfo.getClassType().newInstance(), ResolveRequestData.resolveData(request));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            return CallBack.error(9, "解析数据异常");
        }
        return CallBack.error(11, "异常");
    }

    public Object handler(String url,FullHttpRequest request){
       return  handlerinvoke(checkUrl(url), request);
    }



}