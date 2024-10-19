package com.lb.mybatis.mapper;

import com.lb.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {


    /**
     * 根据多条件查询Car
     *
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiCondition(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    /**
     * 根据多条件查询Car，使用where标签
     *
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiConditionWithWhere(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    /**
     * 根据多条件查询Car，使用trim标签
     *
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiConditionWithTrim(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    /**
     * 更新信息，使用set标签
     *
     * @param car
     * @return
     */
    int updateWithSet(Car car);

    /**
     * 使用choose when otherwise标签查询
     * @param brand
     * @param guidePrice
     * @param produceTime
     * @return
     */
    List<Car> selectWithChoose(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("produceTime") String produceTime);

    /**
     * 通过foreach完成批量删除
     * @param ids
     * @return
     */
    int deleteBatchByForeach(@Param("ids") Long[] ids);

    /**
     * 通过foreach完成批量删除
     * @param ids
     * @return
     */
    int deleteBatchByForeach2(@Param("ids") Long[] ids);

    /**
     * 批量添加，使用foreach标签
     * @param cars
     * @return
     */
    int insertBatchByForeach(@Param("cars") List<Car> cars);
}
