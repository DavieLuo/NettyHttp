package com.exam.routers.base;

import java.lang.reflect.InvocationTargetException;

import com.exam.routers.pojo.RouterInfo;
import com.exam.untls.CallBack;
import com.exam.untls.ResolveRequestData;
import com.exam.untls.ResultType;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * InvokeStrategy
 */
public class InvokeStrategy implements MainStrategy<Object>{

    private FullHttpRequest request;

    public InvokeStrategy(FullHttpRequest request){
        this.request=request;
    }

    @Override
    public Object resolveurl(Object url, StateContent content) {
        RouterInfo rInfo =null;

        try {
            if(url==null){
                return CallBack.error(ResultType.InvaildPath);
            }
            if(url instanceof RouterInfo){
                rInfo = (RouterInfo)url;   
            }else{
                return rInfo;
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


    
}