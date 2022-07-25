package com.plms.dao;

import com.plms.entities.Patient;
import com.plms.entities.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TestDao {

    ApplicationContext context= new ClassPathXmlApplicationContext("config.xml");
    private JdbcTemplate jdbcTemplate;
    public TestDao(){
        this.jdbcTemplate=this.context.getBean("jdbcTemplate",JdbcTemplate.class);
    }
    public int insertData(Test tst){
        String sql = "INSERT INTO ttest (testName,testGroup,testUnit,testRange,testCost) VALUES(?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql,tst.getTestName(),tst.getGroupName(),tst.getTstUnit(),tst.getNormalRange(),tst.getCost());
        return result;
    }

    public List<Test> getAllTestShortDetails(){
        String sql = "SELECT testId,testName,testGroup,testUnit,testRange,testCost FROM ttest";
        List<Test> tests = this.jdbcTemplate.query(sql,new RowMapperImplmentationTest());
        return tests;
    }
    public int deleteSingleTest(int id){
        String query = "DELETE FROM `ttest` WHERE testId=?";
        int result = this.jdbcTemplate.update(query,id);
        return result;
    }
}
