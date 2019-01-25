package com.exam.routers.base;

/**
 * StateContent
 */
public class StateContent {

    private MState mState;

    public enum MState{
        PATH_FAIL,
        PATH_SUCCESS,
        NO_SUCH_METHOD,
        METHODTYPE_ERROR,
        RESOLVE,
        ; 

    }

    public void setState(MState state){
        mState=state;
    }

    public MState getState(){
        return mState;
    }
}
