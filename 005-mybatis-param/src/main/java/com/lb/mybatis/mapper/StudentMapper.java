package com.lb.mybatis.mapper;

import com.lb.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StudentMapper {
    /**
     * 根据name查询
     * @param name
     * @return
     */
    List<Student> selectByName(String name);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Student selectById(Long id);

    /**
     * 根据birth查询
     * @param birth
     * @return
     */
    List<Student> selectByBirth(Date birth);

    /**
     * 根据sex查询
     * @param sex
     * @return
     */
    List<Student> selectBySex(Character sex);

    /**
     * 根据name和age查询
     * @param paramMap
     * @return
     */
    List<Student> selectByParamMap(Map<String,Object> paramMap);

    /**
     * 保存学生数据
     * @param student
     * @return
     */
    int insert(Student student);

    /**
     * 根据name和sex查询
     * @param name
     * @param sex
     * @return
     */
    List<Student> selectByNameAndSex(String name, Character sex);

    /**
     * 根据name和age查询
     * '@Param("这里填写的其实就是map集合的key")
     *
     * @param name
     * @param age
     * @return
     */
    List<Student> selectByNameAndAge(@Param(value="name") String name, @Param("age") int age);
}
