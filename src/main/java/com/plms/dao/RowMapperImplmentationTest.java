package com.plms.dao;

import com.plms.entities.Patient;
import com.plms.entities.Test;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperImplmentationTest implements RowMapper<Test> {

    @Override
    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
        Test tst = new Test();
        tst.setTestId(rs.getInt(1));
        tst.setTestName(rs.getString(2));
        tst.setTestStartDate(rs.getString(3));
        tst.setTestCompleteDate(rs.getString(4));
        tst.setTestStatus(rs.getString(5));
        return tst;
    }
}
