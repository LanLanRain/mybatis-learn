package com.lb.mybatis;

import com.lb.mybatis.mapper.CarMapper;
import com.lb.mybatis.pojo.Car;
import com.lb.mybatis.utils.SqlSessionUtil;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {

    @Test
    public void testSelectByCarType(){
        CarMapper mapper = (CarMapper) SqlSessionUtil.openSession().getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByCarType("燃油车");
        cars.forEach(System.out::println);
    }

    @Test
    public void testSelectAll(){
        CarMapper mapper = (CarMapper) SqlSessionUtil.openSession().getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll("desc");
        cars.forEach(System.out::println);
    }

    @Test
    public void testSelectAllByTableName(){
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByTableName("t_car");
        cars.forEach(System.out::println);
    }


    @Test
    public void testDeleteBatch(){
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        int count = mapper.deleteBatch("1,2,3");
        System.out.println("删除了几条记录：" + count);
        SqlSessionUtil.openSession().commit();
    }
    @Test
    public void testSelectLikeByBrand(){
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        List<Car> cars = mapper.selectLikeByBrand("奔驰");
        cars.forEach(car -> System.out.println(car));
    }

    @Test
    public void testInsertUseGeneratedKeys(){
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        Car car = new Car();
        car.setCarNum("5262");
        car.setBrand("BYD汉");
        car.setGuidePrice(30.3);
        car.setProduceTime("2020-10-11");
        car.setCarType("新能源");
        mapper.insertUseGeneratedKeys(car);
        SqlSessionUtil.openSession().commit();
        System.out.println(car.getId());
    }

}