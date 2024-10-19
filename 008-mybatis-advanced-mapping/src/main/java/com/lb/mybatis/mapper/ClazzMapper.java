package com.lb.mybatis.mapper;

import com.lb.mybatis.pojo.Clazz;

public interface ClazzMapper {

    /**
     * 根据cid获取Clazz信息
     *
     * @param cid
     * @return
     */
    Clazz selectByCid(Integer cid);

    /**
     * 根据班级编号查询班级信息。同时班级中所有的学生信息也要查询。
     * @param cid
     * @return
     */
    Clazz selectClazzAndStusByCid(Integer cid);
}
