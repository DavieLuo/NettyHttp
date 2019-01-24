package com.exam.routers;

/**
 * RouterStrategy
 */
public interface RouterStrategy<T> {

    public Object resolveurl(String methodName,T map);
    
}