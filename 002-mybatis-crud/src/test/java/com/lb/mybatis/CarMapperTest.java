package com.lb.mybatis;

import com.lb.mybatis.pojo.Car;
import com.lb.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarMapperTest {


    /* JDBC中使用? 作为占位符。那么MyBatis中会使用什么作为占位符呢？
        String sql = "insert into t_car(car_num,brand,guide_price,produce_time,car_type) values(?,?,?,?,?)";
        给 ? 传值。那么MyBatis中应该怎么传值呢？
        ps.setString(1,"103");
        ps.setString(2,"奔驰E300L");
        ps.setDouble(3,50.3);
        ps.setString(4,"2022-01-01");
        ps.setString(5,"燃油车");

        在MyBatis中可以这样做：
        在Java程序中，将数据放到Map集合中
        在sql语句中使用 #{map集合的key} 来完成传值，#{} 等同于JDBC中的 ? ，#{}就是占位符 */

    /**
     * 测试使用MyBatis插入数据
     * 该方法展示了如何使用MyBatis将数据插入到数据库中
     */
    @Test
    public void testInsert() {
       /*  // 准备数据
        Map<String, Object> map = new HashMap<>();
        map.put("k1", "103");
        map.put("k2", "奔驰E300L");
        map.put("k3", 50.3);
        map.put("k4", "2020-10-01");
        map.put("k5", "燃油车");

        // 获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        // 执行插入操作，返回插入的行数
        int count = sqlSession.insert("insertCar", map);

        // 打印插入的行数
        System.out.println("插入的记录数量：" + count); */


        Map<String, Object> map = new HashMap<>();
        // 让key的可读性增强
        map.put("carNum", "103");
        map.put("brand", "奔驰E300L");
        map.put("guidePrice", 50.3);
        map.put("produceTime", "2020-10-01");
        map.put("carType", "燃油车");

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        int count = sqlSession.insert("insertCar", map);
        System.out.println("插入的记录数量：" + count);
    }

    @Test
    public void testInsertCarByPOJO() {
        // 创建POJO，封装数据
        Car car = new Car();
        car.setCarNum("103");
        car.setBrand("奔驰C200");
        car.setGuidePrice(33.23);
        car.setProduceTime("2020-10-11");
        car.setCarType("燃油车");

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        int count = sqlSession.insert("insertCarByPOJO", car);
        System.out.println("插入的记录数量：" + count);
    }

    // 如果采用map集合传参，#{} 里写的是map集合的key，如果key不存在不会报错，数据库表中会插入NULL。
    // 如果采用POJO传参，#{} 里写的是get方法的方法名去掉get之后将剩下的单词首字母变小写
    // （例如：getAge对应的是#{age}，getUserName对应的是#{userName}），如果这样的get方法不存在会报错。

    @Test
    public void testDeleteByCarNum() {
        // 获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        // 执行SQL语句
        int count = sqlSession.delete("deleteByCarNum", "102");
        System.out.println("删除了几条记录：" + count);
    }

    @Test
    public void testUpdateCarByPOJO() {
        // 准备数据
        Car car = new Car();
        car.setId(34L);
        car.setCarNum("102");
        car.setBrand("比亚迪汉");
        car.setGuidePrice(30.23);
        car.setProduceTime("2018-09-10");
        car.setCarType("电车");
        // 获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        // 执行SQL语句
        int count = sqlSession.update("updateCarByPOJO", car);
        System.out.println("更新了几条记录：" + count);
    }

    @Test
    public void testSelectCarById() {
        // 获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        // 执行SQL语句
        Object car = sqlSession.selectOne("selectCarById", 1);
        System.out.println(car);
    }

    @Test
    public void testSelectCarAll() {
        // 获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        // 执行SQL语句
        List<Object> cars = sqlSession.selectList("selectCarAll");
        // 输出结果
        cars.forEach(System.out::println);
    }

    @Test
    public void testNamespace() {
        // 获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        // 执行SQL语句
        // List<Object> cars = sqlSession.selectList("selectCarAll");
        // 这个命名空间主要是为了防止sqlId冲突的，所以可以在mapper.xml中使用namespace来区分
        List<Object> cars = sqlSession.selectList("car2.selectCarAll");
        // 输出结果
        cars.forEach(System.out::println);
    }
}
