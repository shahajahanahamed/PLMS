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
        String sql = "INSERT INTO tpatient (ptnId,ptnName,testType,dob,gender,contactNo,emailId,address) VALUES(?,?,?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql,ptnt.getPatientId(),ptnt.getPatientName(),ptnt.getTestType(),ptnt.getDob(),ptnt.getGender(),ptnt.getContactNo(),ptnt.getEmailId(),ptnt.getAddress());
        return result;
    }

    public List<Patient> getAllPatientShortDetails(){
        String sql = "SELECT ptnId,ptnName,testType,dob,gender,contactNo,emailId FROM tpatient";
        List<Patient> patients = this.jdbcTemplate.query(sql,new RowMapperImplmentationPatient());
        return patients;
    }

}
