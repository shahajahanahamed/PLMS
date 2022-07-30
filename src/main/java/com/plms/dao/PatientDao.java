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
        String sql = "SELECT * FROM tpatient";
        List<Patient> patients = this.jdbcTemplate.query(sql,new RowMapperImplmentationPatient());
        return patients;
    }

    public List<Patient> getAllPatientShortDetails(String name){
        String sql = "SELECT * FROM tpatient where ptnName=?";
        List<Patient> patients = this.jdbcTemplate.query(sql,new RowMapperImplmentationPatient(),name);
        return patients;
    }
    public int deleteSinglePatient(int id){
        String query = "DELETE FROM `tpatient` WHERE ptnId=?";
        int result = this.jdbcTemplate.update(query,id);
        return result;
    }

    public List<Patient> getAllPatientShortDetailsByTestType(String testType) {
        String sql = "SELECT * FROM tpatient where testType=?";
        List<Patient> patient = this.jdbcTemplate.query(sql, new RowMapperImplmentationPatient(),testType);
        return patient;
    }

    public List<Patient> getAllTestType() {
        String sql = "SELECT testType FROM tpatient";
        List<Patient> patientTestType = this.jdbcTemplate.query(sql, new RowMapperImplmentationPatient());
        return patientTestType;
    }

    public Patient getSinglePatientDetails(int ptnId) {
        String sql = "SELECT * FROM tpatient where ptnId=?";
        Patient patient = this.jdbcTemplate.queryForObject(sql, new RowMapperImplmentationPatient(),ptnId);
        return patient;
    }

    public int updatePatient(Patient ptnt) {
        String sql = "UPDATE tpatient SET ptnName=?,testType=?,age=?,contactNo=?,collectedOn=?,address=? where ptnId=?";
        int result = this.jdbcTemplate.update(sql,ptnt.getPtnName(),ptnt.getTestType(),ptnt.getAge(),ptnt.getPtnContact(),ptnt.getPtnTestCollectedDate(),ptnt.getPtnAddress(),ptnt.getPtnId());
        return result;
    }
}
