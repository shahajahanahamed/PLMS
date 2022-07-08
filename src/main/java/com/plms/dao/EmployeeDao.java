package com.plms.dao;
import com.plms.entities.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
public class EmployeeDao {

    EmployeeDao(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/com/plms/config.xml");
        JdbcTemplate template = context.getBean("jdbcTemplate",JdbcTemplate.class);
    }

}
