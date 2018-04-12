package com.evolution.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Program: evolution
 * @Description: Lombok入门demo
 * @Author: liuzijian
 * @Date: 2018-04-11 08:53
 */
public class LombokDemo {

    @Getter
    @Setter
    @ToString
    class Student{
        private String name;
        private int age;
        private String stuNo;
        private String address;
    }

    public static void main(String[] args) {
        Student student = new LombokDemo().new Student();
        student.setName("zhangsan");
        student.setAge(18);
        student.setStuNo("89757");
        student.setAddress("首开广场A座1705室");

        System.out.println(student);
    }
}
