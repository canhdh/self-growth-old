package com.k001.selfgroth.registrationactivate.web;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    static Connection connection;

    public static Connection connection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://35.2000.136.217:3306/user",
                                                    "root","QuocAnhCanh");
        }catch (Exception ex){
            System.out.println("Is my connection " + ex);
        }
        return connection;
    }
}
