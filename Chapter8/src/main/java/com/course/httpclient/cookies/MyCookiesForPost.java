package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    //用来存储cookies的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        //加载配置文件
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testPostCookies() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;
        HttpGet get = new HttpGet(testUrl);
        //DefaultHttpClient可以获取cookies，HttpClient无法获取cookies
        //HttpClient client=new DefaultHttpClient();
        DefaultHttpClient client = new DefaultHttpClient();
        //execute需要在方法后面抛出异常：throws IOException
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        //获取cookies信息
        this.store = client.getCookieStore();
        //获取的cookies是个列表，使用for迭代取值
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println(
                    "cookie name = "
                            + name +
                            ";cookie value = "
                            + value);
        }

    }

    @Test(dependsOnMethods = {"testPostCookies"})
    public void testPostMethod() throws IOException {
        String uri = bundle.getString("test.post.with.cookies");
        String testUrl = this.url + uri;
        //声明一个对象，用于执行方法
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个post方法
        HttpPost post = new HttpPost(testUrl);
        //添加参数
        JSONObject param = new JSONObject();
        param.put("name", "dingzhi");
        param.put("age", "18");
        //设置请求头
        post.setHeader("content-type", "application/json");
        //将参数添加至方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        //将参数与post方法绑定
        post.setEntity(entity);
        //声明一个对象来存储相应结果
        String result;
        //设置cookies信息
        client.setCookieStore((this.store));
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取返回值
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        //处理返回值，即对比返回值是否符合预期
        //将返回的响应结果转化成json对象
        JSONObject resultJson = new JSONObject(result);
        //具体的判断返回结果的值
        //获取到的结果值
        String success = (String) resultJson.get("huhansan");
        String status = (String) resultJson.get("status");
        Assert.assertEquals("success", success);
        Assert.assertEquals("1", status);
    }
}
