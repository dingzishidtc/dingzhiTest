package com.course.model;

import lombok.Data;
//@Data用于自动生成工造函数，不过不加该注解，需要手动
@Data
public class User {
    private int id;
    private String name;
    private String sex;
    private int age;

}
