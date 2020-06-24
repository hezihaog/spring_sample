package com.itheima.test;

import com.itheima.config.SpringConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试QueryRunner是否单例
 */
public class QueryRunnerTest {
    private static final Class<?> SPRING_CONFIG = SpringConfiguration.class;

    @Test
    public void testQueryRunner() {
        //获取容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SPRING_CONFIG);
        //获取QueryRunner实例
        QueryRunner runner = context.getBean("runner", QueryRunner.class);
        QueryRunner runner2 = context.getBean("runner", QueryRunner.class);
        System.out.println(runner);
        System.out.println(runner2);
        System.out.println(runner == runner2);
    }
}