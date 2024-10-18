package com.lb.mybatis.test;

import com.lb.mybatis.mapper.StudentMapper;
import com.lb.mybatis.pojo.Student;
import com.lb.mybatis.utils.SqlSessionUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentMapperTest {

    StudentMapper mapper = SqlSessionUtil.openSession().getMapper(StudentMapper.class);

    @Test
    public void testSelectByName(){
        List<Student> students = mapper.selectByName("张三");
        students.forEach(student -> System.out.println(student));
    }
    @Test
    public void testSelectById(){
        Student student = mapper.selectById(2L);
        System.out.println(student);
    }
    @Test
    public void testSelectByBirth(){
        try {
            Date birth = new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-16");
            List<Student> students = mapper.selectByBirth(birth);
            students.forEach(student -> System.out.println(student));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void testSelectBySex(){
        List<Student> students = mapper.selectBySex('男');
        students.forEach(student -> System.out.println(student));
     }

    @Test
    public void testSelectByParamMap(){
        // 准备Map
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("nameKey", "张三");
        paramMap.put("ageKey", 20);

        List<Student> students = mapper.selectByParamMap(paramMap);
        students.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        Student student = new Student();
        student.setName("李四");
        student.setAge(30);
        student.setHeight(1.70);
        student.setSex('男');
        student.setBirth(new Date());
        int count = mapper.insert(student);
        SqlSessionUtil.openSession().commit();
    }

    @Test
    public void testSelectByNameAndSex(){
        List<Student> students = mapper.selectByNameAndSex("张三", '女');
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    @Test
    public void testSelectByNameAndAge(){
        List<Student> stus = mapper.selectByNameAndAge("张三", 20);
        stus.forEach(System.out::println);
    }
}