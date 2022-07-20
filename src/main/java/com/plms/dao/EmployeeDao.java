package com.plms.dao;

import com.plms.entities.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EmployeeDao {

    ApplicationContext context= new ClassPathXmlApplicationContext("config.xml");
    private JdbcTemplate jdbcTemplate;
    public EmployeeDao(){
        this.jdbcTemplate=this.context.getBean("jdbcTemplate",JdbcTemplate.class);
    }
    public int insertData(Employee emp){
        String sql = "INSERT INTO temployee (empName,username,password,userType,dob,gender,contactNo,emailId,address) VALUES(?,?,?,?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql,emp.getEmpName(),emp.getUsername(),emp.getPassword(),emp.getUserType(),emp.getDob(),emp.getGender(),emp.getContactNo(),emp.getEmailId(),emp.getAddress());
        return result;
    }

    public List<Employee> getAllEmployeeShortDetails(){
        String sql = "SELECT empId,empName,username,userType,contactNo,emailId FROM temployee";
        List<Employee> employees = this.jdbcTemplate.query(sql,new RowMapperImplmentationEmp());
        return employees;
    }

    public int deleteSingleEmployee(int id){
        String query = "DELETE FROM `temployee` WHERE empId=?";
        int result = this.jdbcTemplate.update(query,id);
        return result;
    }

}
