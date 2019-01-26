package com.exam.routers.base;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import com.exam.routers.RouterContext;
import com.exam.routers.base.StateContent.MState;
import com.exam.routers.pojo.RouterInfo;
import com.exam.untls.CallBack;
import com.exam.untls.ResolveRequestData;
import com.exam.untls.ResultType;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * DispathHandler
 */
public class DispathHandler {

    private StateContent content;


    private Set<RouterStrategy> strategys = RouterContext.getInstance().getRouterSet();

    public DispathHandler() {
        content = new StateContent();
    }

    private RouterInfo checkUrl(String url) {
        RouterInfo routerInfo = null;
        Set<RouterStrategy> strategys = RouterContext.getInstance().getRouterSet();
        for (RouterStrategy strategy : strategys) {
            routerInfo = strategy.resolveurl(url, content);
            if(content.getState().equals(MState.PATH_SUCCESS)||content.getState().equals(MState.NO_SUCH_METHOD)){
               break;
            }
        }
        return routerInfo;
    }

    private Object handlerinvoke(RouterInfo rInfo,FullHttpRequest request) {
        
        try {
            if(rInfo==null){
                return CallBack.error(ResultType.InvaildPath);
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
            return CallBack.error(ResultType.ResolveDataFail);
        }
        return CallBack.error(ResultType.Error);
    }

    public Object handler(String url,FullHttpRequest request){
       return  handlerinvoke(checkUrl(url), request);
    }
    
}