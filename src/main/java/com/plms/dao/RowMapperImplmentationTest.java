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
        tst.setGroupName(rs.getString(3));
        tst.setTstUnit(rs.getString(4));
        tst.setNormalRange(rs.getString(5));
        tst.setCost(rs.getString(6));
        return tst;
    }
}
