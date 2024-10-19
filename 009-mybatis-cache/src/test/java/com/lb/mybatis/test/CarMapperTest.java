package com.lb.mybatis.test;

import com.lb.mybatis.mapper.CarMapper;
import com.lb.mybatis.pojo.Car;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class CarMapperTest {


          /*   **一、缓存的作用**

    通过减少对数据库的 I/O 操作来提高程序的执行效率。将 select 语句的查询结果存储在缓存中，下次执行相同的 select 语句时，直接从缓存中获取结果，而不必再次查询数据库。

            **二、缓存分类**

            1. **一级缓存**：
            - **存储位置**：存储在 SqlSession 对象中。
            - **默认状态**：默认开启，无需额外配置。
            - **生效条件**：使用同一个 SqlSession 对象执行同一条 SQL 语句时，会走一级缓存。
            - **失效情况**：
            - 使用不同的 SqlSession 对象。
            - 查询条件发生变化。
            - 手动清空一级缓存。
            - 在两次查询之间执行了增删改操作（与操作的表无关，只要有 insert、delete、update 操作，一级缓存就失效）。

            2. **二级缓存**：
            - **存储位置**：存储在 SqlSessionFactory 对象中，其作用范围大于一级缓存。
            - **开启条件**：
            - `<setting name="cacheEnabled" value="true">`：全局性开启或关闭所有映射器配置文件中已配置的任何缓存，默认值为 true，一般无需设置。
            - 在需要使用二级缓存的 SqlMapper.xml 文件中添加`<cache />`配置。
            - 使用二级缓存的实体类对象必须实现`java.io.Serializable`接口。
            - **缓存写入时机**：SqlSession 对象关闭或提交之后，一级缓存中的数据才会被写入到二级缓存当中。
            - **失效情况**：两次查询之间出现增删改操作，二级缓存会失效，同时一级缓存也会失效。
            - **相关配置**：
            - `eviction`：指定从缓存中移除某个对象的淘汰算法，默认采用 LRU（Least Recently Used，最近最少使用）策略，还可以选择 FIFO（First In First Out，先进先出）、SOFT（软引用，淘汰软引用指向的对象，具体算法与 JVM 的垃圾回收算法有关）、WEAK（弱引用，淘汰弱引用指向的对象，具体算法与 JVM 的垃圾回收算法有关）等策略。
            - `flushInterval`：二级缓存的刷新时间间隔，单位为毫秒。如果没有设置，则代表不刷新缓存，只要内存足够大，一直会向二级缓存中缓存数据，除非执行了增删改操作。
            - `readOnly`：设置为`true`时，多条相同的 sql 语句执行之后返回的对象是共享的同一个，性能好但多线程并发可能存在安全问题；设置为`false`时，返回的对象是副本，调用了`clone`方法，性能一般但安全。
            - `size`：设置二级缓存中最多可存储的 Java 对象数量，默认值为 1024。

            **三、集成第三方缓存**

    MyBatis 可以集成第三方缓存组件来替代自带的二级缓存，其中集成 EhCache（Java 编写）较为常见，步骤如下：
            1. 引入依赖：在项目的 pom.xml 文件中添加`<dependency>`，引入`mybatis-ehcache`依赖。
            2. 配置文件：在类的根路径下新建`ehcache.xml`文件，并提供磁盘存储、默认管理策略等配置信息。
            3. 修改配置：在 SqlMapper.xml 文件中的`<cache/>`标签添加`type`属性，指定为`org.mybatis.caches.ehcache.EhcacheCache`。

 */
    /* 一级缓存：将查询到的数据存储到SqlSession中。
    二级缓存：将查询到的数据存储到SqlSessionFactory中。
    或者集成其它第三方的缓存：比如EhCache【Java语言开发的】、Memcache【C语言开发的】等。
    缓存只针对于DQL语句，也就是说缓存机制只对应select语句。 */
    @Test
    public void testSelectById() throws Exception{
        // 注意：不能使用我们封装的SqlSessionUtil工具类。
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        SqlSession sqlSession1 = sqlSessionFactory.openSession();

        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        Car car1 = mapper1.selectById(83L);
        System.out.println(car1);

        CarMapper mapper2 = sqlSession1.getMapper(CarMapper.class);
        Car car2 = mapper2.selectById(83L);
        System.out.println(car2);

        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        CarMapper mapper3 = sqlSession2.getMapper(CarMapper.class);
        Car car3 = mapper3.selectById(83L);
        System.out.println(car3);

        CarMapper mapper4 = sqlSession2.getMapper(CarMapper.class);
        Car car4 = mapper4.selectById(83L);
        System.out.println(car4);

    }

    @Test
    public void testSelectById2() throws Exception{
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        Car car1 = mapper1.selectById(83L);
        System.out.println(car1);

        // 关键一步
        sqlSession1.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);
        Car car2 = mapper2.selectById(83L);
        System.out.println(car2);
    }
}