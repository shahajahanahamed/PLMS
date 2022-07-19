package com.plms.dao;

import com.plms.entities.Employee;
import com.plms.entities.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperImplmentationPatient implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patient ptn = new Patient();
        ptn.setPtnId(rs.getInt(1));
        ptn.setPtnName(rs.getString(2));
        ptn.setTestType(rs.getString(3));
        ptn.setPtnContact(rs.getString(5));
        ptn.setPtnEmailId(rs.getString(6));
        return ptn;
    }
}
