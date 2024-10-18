package com.lb.MyBatisTest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisIntroTest {
    /**
     * 测试 MyBatis 的插入功能
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 创建一个 SqlSessionFactoryBuilder 对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 从类加载器中获取 mybatis-config.xml 文件的输入流
        InputStream resourceAsStream =
                Thread.currentThread().getContextClassLoader().getResourceAsStream("mybatis-config.xml");

        // 这种方式只能从类路径当中获取资源，也就是说mybatis-config.xml文件需要在类路径下。
        // InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        // 使用 SqlSessionFactoryBuilder 创建一个 SqlSessionFactory 对象

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        // 从 SqlSessionFactory 中打开一个 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 使用 SqlSession 执行插入操作，返回插入的行数
        int count = sqlSession.insert("insertCar"); // 这个"insertCar"必须是sql的id
        // 打印插入的行数
        System.out.println("插入了" + count + "条数据");
        // 提交事务 （mybatis默认采用的事务管理器是JDBC，默认是不提交的，需要手动提交。）
        sqlSession.commit();
        // 关闭 SqlSession  关闭资源（只关闭是不会提交的）
        sqlSession.close();
    }
}

