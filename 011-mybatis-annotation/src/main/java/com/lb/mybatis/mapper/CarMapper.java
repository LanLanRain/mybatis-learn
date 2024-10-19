package com.lb.mybatis.mapper;

import com.lb.mybatis.pojo.Car;
import org.apache.ibatis.annotations.*;

public interface CarMapper {

    @Insert(value="insert into t_car values(null,#{carNum},#{brand},#{guidePrice},#{produceTime},#{carType})")
    int insert(Car car);

    @Delete("delete from t_car where id = #{id}")
    int deleteById(Long id);

    @Update("update t_car set car_num=#{carNum},brand=#{brand},guide_price=#{guidePrice},produce_time=#{produceTime},car_type=#{carType} where id=#{id}")
    int update(Car car);

    @Select("select * from t_car where id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "car_num", property = "carNum"),
            @Result(column = "brand", property = "brand"),
            @Result(column = "guide_price", property = "guidePrice"),
            @Result(column = "produce_time", property = "produceTime"),
            @Result(column = "car_type", property = "carType")
    })
    Car selectById(Long id);
}