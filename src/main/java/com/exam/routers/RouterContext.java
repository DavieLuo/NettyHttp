package com.exam.routers;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.exam.routers.base.RouterStrategy;
import com.exam.routers.base.StateContent;
import com.exam.routers.base.StateContent.MState;
import com.exam.routers.pojo.RouterInfo;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * RouterContent
 */
public class RouterContext {


    private RouterContext(){}

    private static final class SingleIton{
        private static final RouterContext INSTANCE = new RouterContext();
    }

    public static RouterContext getInstance(){
        return SingleIton.INSTANCE;
    }

    private Set<RouterStrategy> routerSet = new HashSet();

    public void registRouterStrategy(RouterStrategy ...strategys) {
        for (RouterStrategy strategy : strategys) {
        
            routerSet.add(strategy);
        }
       
    }

    /**
     * @return the routerSet
     */
    public Set<RouterStrategy> getRouterSet() {
        return routerSet;
    }

    public RouterInfo checkUrl(String url,StateContent content ){
        RouterInfo routerInfo=null;
        for (RouterStrategy strategy : routerSet) {
            routerInfo = strategy.resolveurl(url, content);
            if(content.getState().equals(MState.PATH_SUCCESS)||content.getState().equals(MState.NO_SUCH_METHOD)){
                break;
            }
        }
        return routerInfo;
    }

    

}