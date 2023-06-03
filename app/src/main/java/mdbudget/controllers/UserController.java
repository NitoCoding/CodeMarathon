package mdbudget.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mdbudget.models.BaseModel;
import mdbudget.models.User;
import mdbudget.utils.Connector;
import mdbudget.utils.IdDatabaseGenerator;

public class UserController {
    static Connection conn;
    static PreparedStatement statement;
    static ResultSet resultSet;

    public static int loginUser(String nama, String password) {
        int id = 0;
        try {
            conn = Connector.getConnection();
            String queryOrder = "SELECT * FROM tb_User WHERE userNama = ? AND userPassword = ? AND (userRole != 'admin' OR userRole IS NULL) LIMIT 1";
            statement = conn.prepareStatement(queryOrder);
            statement.setString(1, nama);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("userId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connections
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // Handle the exception or log the error
                e.printStackTrace();
            }
        }
        return id;
    }

    public static int loginUserAdmin(String nama, String password) {
        int id = 0;
        try {
            conn = Connector.getConnection();
            String queryOrder = "SELECT * FROM tb_User WHERE userNama = ? AND userPassword = ? LIMIT 1";
            statement = conn.prepareStatement(queryOrder);
            statement.setString(1, nama);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("userId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connections
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // Handle the exception or log the error
                e.printStackTrace();
            }
        }
        return id;
    }

    public static int registerUser(String nama, String password) {
        int rowsAffected = 0;
        try {
            conn = Connector.getConnection();
            int id = IdDatabaseGenerator.generateId("userId", "tb_User");
            String queryOrder = "INSERT INTO tb_User VALUES (?, ?, ?, null)";
            statement = conn.prepareStatement(queryOrder);
            statement.setInt(1, id);
            statement.setString(2, nama);
            statement.setString(3, password);
            rowsAffected = statement.executeUpdate();


            if (resultSet.next()) {
                rowsAffected += resultSet.getInt("userId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connections
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // Handle the exception or log the error
                e.printStackTrace();
            }
        }
        return rowsAffected;
    }

    public static User getUserById(int id){
        User user = null;
        try {
            conn = Connector.getConnection();
            String queryOrder = "SELECT * FROM tb_User WHERE userId == ? LIMIT 1";
            statement = conn.prepareStatement(queryOrder);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String userNama = resultSet.getString("userNama");
                String userPass = resultSet.getString("userPassword");
                String userRole = resultSet.getString("userRole");

                user = new User(userId, userNama, userPass, userRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connections
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // Handle the exception or log the error
                e.printStackTrace();
            }
        }
        return user;
    }

    public static ArrayList<BaseModel> getAllDataUsers(){
        ArrayList<BaseModel> users = new ArrayList<>();
        try {
            conn = Connector.getConnection();
            String queryOrder = "SELECT * FROM tb_User";
            statement = conn.prepareStatement(queryOrder);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String userNama = resultSet.getString("userNama");
                String userPass = resultSet.getString("userPassword");
                String userRole = resultSet.getString("userRole");

                User user = new User(userId, userNama, userPass, userRole);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connections
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // Handle the exception or log the error
                e.printStackTrace();
            }
        }
        return users;
    }
}
