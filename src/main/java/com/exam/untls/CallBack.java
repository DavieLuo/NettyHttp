package com.exam.untls;

/**
 * CallBack
 */
public class CallBack {

    private Integer code;
    private String msg;
    private String errorStatus;
    private Object result;

    public CallBack() {
    }

    public CallBack(Integer code, String msg, String errorStatus, Object result) {
        this.code = code;
        this.msg = msg;
        this.errorStatus = errorStatus;
        this.result = result;
    }

    public static CallBack  success(String msg,Object res){
        return new CallBack(0,msg,"SUCCESS",res);
    }

    public static CallBack error(Integer code,String msg){
        return new CallBack(code,msg,"FAIL",null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String isErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CallBack{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", errorStatus='" + errorStatus + '\'' +
                ", result=" + result +
                '}';
    }

    
}