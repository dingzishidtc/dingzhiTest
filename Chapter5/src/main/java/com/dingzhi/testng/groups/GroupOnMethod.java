package com.dingzhi.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMethod {
    @Test(groups = "server")
    public void test1(){
        System.out.println("服务端组测试方法1");
    }
    @Test(groups = "server")
    public void test2(){
        System.out.println("服务端组测试方法2");
    }
    @Test(groups = "client")
    public void test3(){
        System.out.println("客户组测试方法3");
    }
    @Test(groups = "client")
    public void test4(){
        System.out.println("客户组测试方法4");
    }
    @BeforeGroups("server")
    public void beforeGroupsServer(){
        System.out.println("服务端运行前的方法");
    }
    @AfterGroups("server")
    public void afterGroupsServer(){
        System.out.println("服务端运行后的方法!!!");
    }
    @BeforeGroups("client")
    public void beforeGroupsClients(){
        System.out.println("客户端运行前的方法");
    }
    @AfterGroups("client")
    public void afterGroupsClient(){
        System.out.println("客户端运行后的方法!!!");
    }
}
