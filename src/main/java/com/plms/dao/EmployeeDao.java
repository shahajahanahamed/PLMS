package com.plms.dao;

import com.plms.entities.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class EmployeeDao {
    private ApplicationContext context;
    private JdbcTemplate template;

    public EmployeeDao() {
        context = new ClassPathXmlApplicationContext("/com/plms/config.xml");
        template = context.getBean("jdbcTemplate", JdbcTemplate.class);
    }

    public int insertData(Employee emp){
        String sql = "insert into temployee (empName,username,password,userType,dob,gender,contactNo,emailId,address) values(?,?,?,?,?,?,?,?,?)";
        int result = template.update(sql,emp.getEmpName(),emp.getUsername(),emp.getPassword(),emp.getUserType(),emp.getDob(),emp.getGender(),emp.getContactNo(),emp.getEmailId(),emp.getAddress());
        return result;
    }

}
