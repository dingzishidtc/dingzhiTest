package com.dingzhi.testng;

import org.testng.annotations.Test;

public class ExceptedException {
    /**
     * 什么时候会用到异常测试
     * 在我期望结果为某一个异常的时候
     * 比如：传入一个不合法的参数，程序抛出异常
     */
    //这是一个测试结果会失败的异常测试
//    @Test(expectedExceptions = RuntimeException.class)
//    public void rnnTimeExceptionFailed(){
//        System.out.println("这是一个失败的异常测试");
//    }
    @Test(expectedExceptions = RuntimeException.class)
    public void rnnTimeExceptionSuccess(){
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();
    }
}
