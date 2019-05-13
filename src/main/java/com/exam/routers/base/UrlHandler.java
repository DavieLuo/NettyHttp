package com.exam.routers.base;

import java.util.Map;
import java.util.Set;

import com.exam.routers.base.RouterInfo;
import com.exam.untls.CallBack;
import com.exam.untls.ResultType;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * UrlHandler
 * api filter
 * @author luo
 */
public class UrlHandler implements HomeHandler {

    @Override
    public boolean hasUrl(FullHttpRequest request,HandlerContext context) {
        
        boolean flag = false;
        // do somethings 
        Router router = null;
        Set<RouterStrategy> strategys = RouterContext.getInstance().getRouterSet();
        for (RouterStrategy strategy : strategys) {
            Map<String, Router> map= strategy.routerFunc();
            //request.uri()          
            router = map.get(gettruePath(request.uri()));
            if(router!=null){
                context.setRouter(router);
                flag = true;
                break;
            }
        }
        
        if(flag){
            context.setHandler(new PermissionHandler());
        }else {
            context.setResult(CallBack.error(ResultType.InvaildPath));
        }        
        return flag;
    }
    
    public String gettruePath(String url){
        if(url.contains("?")){
            return url.split("[?]")[0];
        }
        return url;
    }
    

    
    
}