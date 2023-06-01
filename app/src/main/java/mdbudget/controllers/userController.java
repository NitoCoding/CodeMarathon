package mdbudget.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mdbudget.config.Connector;

public class UserController {
    static Connection conn;
    static Statement statement;
    static ResultSet resultSet;

    public static int loginUser(String nama, String password){
        int status = 0;
        try {
            conn = Connector.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user WHERE userNama = 'nito' AND userPassword = 'nito' AND (userRole != 'admin' OR userRole IS NULL) limit 1");
            
            if(resultSet.next()){
                status = resultSet.getInt("userId");
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return status;
    }
}
