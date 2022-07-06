package com.plms.DBConn;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName="plms";
        String databaseUser="root";
        String databasePassword="plms1234";
        String url="jdbc:mysql://localhost:3306/"+databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver8.0");
            databaseLink=DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
