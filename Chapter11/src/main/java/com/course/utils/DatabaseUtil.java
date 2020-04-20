package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


public class DatabaseUtil {
    public static SqlSession getSqlSession() throws IOException {
        //获取配置文件
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
        //新建sql对象并加载配置文件，意思是使用类加载器加载配置文件
        SqlSessionFactory factory =new SqlSessionFactoryBuilder().build(reader);
        //session就是执行配置文件中sql语句的对象
        SqlSession session=factory.openSession();
        return session;
    }
}
