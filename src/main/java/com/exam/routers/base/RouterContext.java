package com.exam.routers.base;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.exam.routers.AccountStrategy;
import com.exam.routers.HomeStrategy;
import com.exam.routers.pojo.RouterInfo;


/**
 * RouterContent
 */
public class RouterContext {


    private RouterContext(){
        registRouterStrategy(new AccountStrategy(),new HomeStrategy());
    }

    private static final class SingleIton{
        private static final RouterContext INSTANCE = new RouterContext();
    }

    public static RouterContext getInstance(){
        return SingleIton.INSTANCE;
    }

    private Set<RouterStrategy> routerSet = new HashSet<>();

    private Map<RouterInfo,Router> routermap = new HashMap<>();

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

    /**
     * @param routerSet the routerSet to set
     */
    public void setRouterSet(Set<RouterStrategy> routerSet) {
        this.routerSet = routerSet;
    }

    

    

    

}