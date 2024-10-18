package com.lb.MyBatisTest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisCompleteCodeTest {
    /**
     * 测试 MyBatis 的完整代码
     *
     * @param args 命令行参数
     * @throws IOException 如果发生 I/O 错误
     */
    public static void main(String[] args) throws IOException {
        // 定义 SqlSession 对象，用于执行 SQL 语句
        SqlSession sqlSession = null;
        try {
            // 创建 SqlSessionFactoryBuilder 对象
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            // 使用 Resources 类加载 mybatis-config.xml 文件，并创建 SqlSessionFactory 对象
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            // 从 SqlSessionFactory 中获取 SqlSession 对象
            sqlSession = sqlSessionFactory.openSession();

            // 执行插入操作，返回插入的行数
            int count = sqlSession.insert("insertCar");
            // 打印插入的行数
            System.out.println("插入了" + count + "条数据");

            // 提交事务
            sqlSession.commit();
        } catch (Exception e) {
            // 如果发生异常，回滚事务
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            // 打印异常堆栈跟踪
            e.printStackTrace();
        } finally {
            // 关闭 SqlSession 对象
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}

