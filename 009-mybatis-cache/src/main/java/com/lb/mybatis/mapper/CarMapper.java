package com.lb.mybatis.mapper;

import com.lb.mybatis.pojo.Car;

public interface CarMapper {

    /**
     * 根据id获取Car信息。
     * @param id
     * @return
     */
    Car selectById(Long id);

    /**
     * 保存账户信息
     */
    void insertAccount();
}