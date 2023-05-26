package mdbudget.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mdbudget.Connector;
import mdbudget.models.User;

public class UserController {
    static Connection conn;
    static Statement statement;
    static ResultSet resultSet;

    public static User loginUser(String nama, String password){
        User user = null;
        try {
            conn = Connector.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user WHERE userNama = '" + nama + "' AND userPassword = '" + password + "' AND userRole != 'admin'");
            while(resultSet.next()){
                int userId = resultSet.getInt("userId");
                String userNama = resultSet.getString("userNama");
                user = new User();
                user.setUserId(userId);
                user.setUserNama(userNama);
            }
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return user;
    }

    public static boolean registerUser(String nama, String password){
        boolean status = false;
        // User user = new User(nama,password);
        try {
            conn = Connector.getConnection();
            statement = conn.createStatement();
            int rowsAffected = statement.executeUpdate("insert into user (userId,userNama,userPassword) values '"+nama +"''"+password+"'");
            
            if(rowsAffected > 0){
                status = true;
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return status;
        
    }
}
