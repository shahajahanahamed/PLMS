package com.plms.dao;

import com.plms.entities.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperImplmentationEmp implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee emp = new Employee();
        emp.setEmpId(rs.getInt(1));
        emp.setEmpName(rs.getString(2));
        emp.setUsername(rs.getString(3));
        emp.setUserType(rs.getString(4));
        emp.setContactNo(rs.getString(5));
        emp.setEmailId(rs.getString(6));
        return emp;
    }
}
