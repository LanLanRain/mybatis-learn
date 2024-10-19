package com.lb.mybatis.test;

import com.lb.mybatis.mapper.CarMapper;
import com.lb.mybatis.pojo.Car;
import com.lb.mybatis.utils.SqlSessionUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CarMapperTest {
    @Test
    public void testSelectByMultiCondition() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        // select * from t_car where brand like ?"%" and guide_price >= ? and car_type = ?
        // List<Car> cars = mapper.selectByMultiCondition("丰田", 20.0, "燃油车");

        //  select * from t_car where and guide_price >= ? and car_type = ?
        List<Car> cars = mapper.selectByMultiCondition("", 20.0, "燃油车");
        System.out.println(cars);
    }

    @Test
    public void testSelectByMultiConditionWithWhere() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        // List<Car> cars = mapper.selectByMultiConditionWithWhere("丰田", 20.0, "燃油车");
        List<Car> cars = mapper.selectByMultiConditionWithWhere("", null, "");
        System.out.println(cars);
    }

    @Test
    public void testSelectByMultiConditionWithTrim() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        // List<Car> cars = mapper.selectByMultiConditionWithTrim("丰田", 20.0, "");
        List<Car> cars = mapper.selectByMultiConditionWithTrim("", null, "");
        System.out.println(cars);
    }

    @Test
    public void testUpdateWithSet() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        Car car = new Car(38L, "1001", "丰田霸道2", 10.0, "", null);
        int count = mapper.updateWithSet(car);
        System.out.println(count);
        SqlSessionUtil.openSession().commit();
    }

    @Test
    public void testSelectWithChoose() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        //List<Car> cars = mapper.selectWithChoose("丰田霸道", 20.0, "2000-10-10");
        //List<Car> cars = mapper.selectWithChoose("", 20.0, "2000-10-10");
        //List<Car> cars = mapper.selectWithChoose("", null, "2000-10-10");
        List<Car> cars = mapper.selectWithChoose("", null, "");
        System.out.println(cars);
    }

    @Test
    public void testDeleteBatchByForeach() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        int count = mapper.deleteBatchByForeach(new Long[]{40L, 41L, 42L});
        System.out.println("删除了几条记录：" + count);
        SqlSessionUtil.openSession().commit();
    }

    @Test
    public void testDeleteBatchByForeach2() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        int count = mapper.deleteBatchByForeach2(new Long[]{40L, 41L, 42L});
        System.out.println("删除了几条记录：" + count);
        SqlSessionUtil.openSession().commit();
    }

    @Test
    public void testInsertBatchByForeach() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        Car car1 = new Car(null, "2001", "兰博基尼", 100.0, "1998-10-11", "燃油车");
        Car car2 = new Car(null, "2001", "兰博基尼", 100.0, "1998-10-11", "燃油车");
        Car car3 = new Car(null, "2001", "兰博基尼", 100.0, "1998-10-11", "燃油车");
        List<Car> cars = Arrays.asList(car1, car2, car3);
        int count = mapper.insertBatchByForeach(cars);
        System.out.println("插入了几条记录" + count);
        SqlSessionUtil.openSession().commit();
    }
}