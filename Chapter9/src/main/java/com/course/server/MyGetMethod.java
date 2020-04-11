package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


//该标签用于告诉@ComponentScan标签来扫面这个类
@RestController
@Api(value="/",description = "这是我全部的get方法")
public class MyGetMethod {
    /**
     * 下方演示的是如何将cookies信息通过接口返回
     */
    //设置接口的访问路径,method用于限制这个接口用什么方法来访问
    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value="通过这个方法可以获取到cookies",httpMethod = "GET")
    //下方括号内是固定的，这样响应信息会直接返回给页面
    public String getCookies(HttpServletResponse response) {
        //HttpServletRequest 装请求信息的类
        //HttpServletResponse 装响应信息的类
        Cookie cookie = new Cookie("login", "true");
        //将cookie加入响应信息
        response.addCookie(cookie);
        return "恭喜你获得cookies成功";
    }

    /**
     * 要求客户端携带cookies访问
     */

    //设置接口的访问路径,method用于限制这个接口用什么方法来访问
    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value="要求客户端携带cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "你必须带cookies";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")) {
                return "这是一个需要cookies信息才能访问的get请求";
            }
        }
        return "你必须带cookies1111111111111";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式url：key=value&key=value
     * 模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param", method = RequestMethod.GET)
    @ApiOperation(value="需要携带参数才能访问的get请求方法1",httpMethod = "GET")
    public Map<String, Integer> getList(@RequestParam Integer start,
                                        @RequestParam Integer end) {
        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋", 400);
        myList.put("小当家", 1);
        myList.put("衬衫", 300);
        return myList;
    }

    /**
     * 第二种需要携带参数访问的get请求
     * url：ip:port/get/with/param/10/20
     * 模拟获取商品列表
     */
    @RequestMapping(value = "  {start}/{end}", method = RequestMethod.GET)
    @ApiOperation(value="需要携带参数才能访问的get请求方法2",httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start,
                         @PathVariable Integer end) {
        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋", 400);
        myList.put("小当家", 1);
        myList.put("衬衫", 300);
        return myList;
    }
}
