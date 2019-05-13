package com.exam.routers.base;

/**
 * HandlerContext
 */
public class HandlerContext {

    private HomeHandler handler;

    /**
     * 返回结果
     */
    private Object result;

    /**
     * 执行的方法
     */
    private Router router;

    public HandlerContext(){
        handler = null;
    }

    /**
     * @return the handler
     */
    public HomeHandler getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(HomeHandler handler) {
        this.handler = handler;
    }

    /**
     * @return the result
     */
    public Object getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Object result) {
        this.result = result;
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