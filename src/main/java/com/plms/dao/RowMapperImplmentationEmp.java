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
        emp.setPassword(rs.getString(4));
        emp.setUserType(rs.getString(5));
        emp.setDob(rs.getString(6));
        emp.setGender(rs.getString(7));
        emp.setContactNo(rs.getString(8));
        emp.setEmailId(rs.getString(9));
        emp.setAddress(rs.getString(10));
        return emp;
    }
}
