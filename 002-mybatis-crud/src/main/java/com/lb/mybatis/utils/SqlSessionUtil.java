package com.lb.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class SqlSessionUtil {
    // 静态变量，用于存储 SqlSessionFactory 对象
    private static SqlSessionFactory sqlSessionFactory;

    // 静态代码块，在类加载时初始化 SqlSessionFactory 对象
    static {
        // 创建 SqlSessionFactoryBuilder 对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        try {
            // 使用 Resources 类加载 mybatis-config.xml 文件，并创建 SqlSessionFactory 对象
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            // 如果发生 I/O 异常，抛出运行时异常
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取 SqlSession 对象
     *
     * @return SqlSession 对象
     */
    public static SqlSession getSqlSession() {
        // 从 SqlSessionFactory 中获取 SqlSession 对象
        return sqlSessionFactory.openSession(true);
    }
}
