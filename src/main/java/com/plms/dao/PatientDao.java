package com.plms.dao;

import com.plms.entities.Employee;
import com.plms.entities.Patient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PatientDao {

    ApplicationContext context= new ClassPathXmlApplicationContext("config.xml");
    private JdbcTemplate jdbcTemplate;
    public PatientDao(){
        this.jdbcTemplate=this.context.getBean("jdbcTemplate",JdbcTemplate.class);
    }
    public int insertData(Patient ptnt){
        String sql = "INSERT INTO tpatient (ptnName,testType,age,gender,contactNo,address,collectedOn) VALUES(?,?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql,ptnt.getPtnName(),ptnt.getTestType(),ptnt.getAge(),ptnt.getPtnGender(),ptnt.getPtnContact(),ptnt.getPtnAddress(),ptnt.getPtnTestCollectedDate());
        return result;
    }

    public List<Patient> getAllPatientShortDetails(){
        String sql = "SELECT ptnId,ptnName,testType,age,gender,contactNo,collectedOn FROM tpatient";
        List<Patient> patients = this.jdbcTemplate.query(sql,new RowMapperImplmentationPatient());
        return patients;
    }
    public int deleteSinglePatient(int id){
        String query = "DELETE FROM `tpatient` WHERE ptnId=?";
        int result = this.jdbcTemplate.update(query,id);
        return result;
    }
}
