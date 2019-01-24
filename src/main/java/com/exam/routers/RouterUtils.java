package com.exam.routers;

import java.util.Map;

import com.exam.untls.Router;

/**
 * RouterUtils
 */
public class RouterUtils {

    public RouterUtils(){
        initUrlMap();
    }

    private RouterContext routerContext;

   /*  private static final class SingleIton{
        private static final RouterUtils INSTANCE = new RouterUtils();
    }

    public static RouterUtils getInstance() {
        return SingleIton.INSTANCE;
    } */

    private void initUrlMap(){
        routerContext = new RouterContext();
        routerContext.registRouter("account",new AccountStrategy());
        routerContext.registRouter(null,new HomeStrategy());
    }

    public  Object handler(String url,Map<String,Object> map) {
        //url
        Router router = resolve(url);
        return routerContext.handler(router.getPathName() , router.getMethodName(), map);
    }

    public Router resolve(String url){
        
        String[] ur = url.split("/");
        return  ur.length<3?new Router(null,ur[1]):new Router(ur[1], ur[2]);
    }
}