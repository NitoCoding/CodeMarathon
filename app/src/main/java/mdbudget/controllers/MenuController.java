package mdbudget.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mdbudget.models.BaseModel;
import mdbudget.models.Menu;
import mdbudget.utils.Connector;

/**
 * menuController
 */
public class MenuController {
    static Connection conn;
    static PreparedStatement statement;
    static ResultSet resultSet;

    public static ArrayList<BaseModel> getAllDataMenus(){
        ArrayList<BaseModel> menus = null;
        try {
            String readQuery = "select * from tb_Menu";

            conn = Connector.getConnection();
            statement = conn.prepareStatement(readQuery);
            resultSet = statement.executeQuery();
            
            menus = new ArrayList<>();
            while (resultSet.next()) {
                int menuId = resultSet.getInt("menuId");
                String menuNama = resultSet.getString("menuNama");
                int menuHarga = resultSet.getInt("menuHarga");
                String menuGambar = resultSet.getString("menuGambar");

                Menu menu = new Menu(menuId, menuNama, menuHarga, menuGambar);
                menus.add(menu);
            }
        } catch (SQLException e) {
            // Handle the exception or log the error
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
        return menus;
    }

    public static Menu getDataById(int id){
        Menu menu = null;
        try {
            String readQuery = "SELECT * FROM tb_Menu WHERE id = ? LIMIT 1";
            conn = Connector.getConnection(); 
            statement = conn.prepareStatement(readQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int menuId = resultSet.getInt("menuId");
                String menuNama = resultSet.getString("menuNama");
                int menuHarga = resultSet.getInt("menuHarga");
                String menuGambar = resultSet.getString("menuGambar");
                menu = new Menu(menuId, menuNama,  menuHarga, menuGambar);
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
        return menu;
    }
    
}