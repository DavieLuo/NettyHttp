package com.exam.untls;

public enum ResultType {

    OK(0,"OK"),
    Fail(1,"fail"),
    Error(2,"异常"),
    InvaildToken(3,"无效token"),

    InvaildPath(10,"无效path"),
    ResolveDataFail(11,"解析数据异常"),
    NullBack(12,"响应结果为空"),
    NO_PERMISSION(13,"没有访问权限"),

    ;
    public Integer code;
    public String msg;
    
    ResultType(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}