package com.plms.entities;

public class Patient {
    private int ptnId;
    private String ptnName;
    private String testType;
    private String age;
    private String ptnGender;
    private String ptnContact;
    private String ptnAddress;
    private String ptnTestCollectedDate;

    public int getPtnId() {
        return ptnId;
    }

    public void setPtnId(int ptnId) {
        this.ptnId = ptnId;
    }

    public String getPtnName() {
        return ptnName;
    }

    public void setPtnName(String ptnName) {
        this.ptnName = ptnName;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getPtnGender() {
        return ptnGender;
    }

    public void setPtnGender(String ptnGender) {
        this.ptnGender = ptnGender;
    }

    public String getPtnContact() {
        return ptnContact;
    }

    public void setPtnContact(String ptnContact) {
        this.ptnContact = ptnContact;
    }

    public String getPtnAddress() {
        return ptnAddress;
    }

    public void setPtnAddress(String ptnAddress) {
        this.ptnAddress = ptnAddress;
    }

    public String getPtnTestCollectedDate() {
        return ptnTestCollectedDate;
    }

    public void setPtnTestCollectedDate(String ptnTestCollectedDate) {
        this.ptnTestCollectedDate = ptnTestCollectedDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
