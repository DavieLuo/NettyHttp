package com.exam.routers.base;

/**
 * MainStrategy
 */
public interface MainStrategy<T> {

    public T resolveurl(Object url,StateContent content);

   
}