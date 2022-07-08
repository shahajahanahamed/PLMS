package com.plms.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class EmployeeDao {
    private ApplicationContext context;
    private JdbcTemplate template;

    EmployeeDao() {
        context = new ClassPathXmlApplicationContext("/com/plms/config.xml");
        template = context.getBean("jdbcTemplate", JdbcTemplate.class);
    }

}
