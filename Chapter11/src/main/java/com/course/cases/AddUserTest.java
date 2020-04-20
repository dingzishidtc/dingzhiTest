package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {
    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口")
    public void addUser() throws IOException, InterruptedException {
        SqlSession session= DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);

        //发请求，获取结果
        String result=getResult(addUserCase);

        //验证返回接口
        //查询用户看是否添加成功
        Thread.sleep(2000);
        User user = session.selectOne("addUser",addUserCase);
        System.out.println(user.toString());

        //处理结果，即哦按段返回结果是否符合预期
        Assert.assertEquals(addUserCase.getExpected(),result);
    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post=new HttpPost(TestConfig.addUserUrl);
        JSONObject param =new JSONObject();
        param.put("username",addUserCase.getUserName());
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());
        //设置请求头header信息
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明一个对象用于执行并存储相应结果
        String result;
        //执行post方法
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        //获取相应结果
        result= EntityUtils.toString(response.getEntity());
        System.out.println(result);
        return result;
    }

}
