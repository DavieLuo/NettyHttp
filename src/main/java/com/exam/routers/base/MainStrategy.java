package com.exam.routers.base;

/**
 * MainStrategy
 */
public interface MainStrategy<T> {

    public T resolveurl(String url,StateContent content);

   
}