package mdbudget.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mdbudget.Connector;
import mdbudget.models.Menu;

/**
 * menuController
 */
public class menuController {
    Connection conn;
    Statement statement;
    ResultSet resultSet;

    public ArrayList<Menu> getAllData() throws SQLException{
        ArrayList<Menu> menus = new ArrayList<>();
        try {
            conn = Connector.getConnection();
            
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from menu");

            while (resultSet.next()) {
                int menuId = resultSet.getInt("menuId");
                String menuNama = resultSet.getString("menuNama");
                String menuKategori = resultSet.getString("menuKategori");
                int menuHarga = resultSet.getInt("menuHarga");
                String menuGambar = resultSet.getString("menuGambar");

                Menu menu = new Menu(menuId, menuNama, menuKategori, menuHarga, menuGambar);
                menus.add(menu);
            }
        } catch (SQLException e) {
            // Handle the exception or log the error
            e.printStackTrace();
        } 
        return menus;
    }

    public Menu getDataById(int id)throws SQLException{
        Menu menu = null;
        try {
            conn = Connector.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from menu where id = '"+ id +"'");
            while (resultSet.next()) {
                int menuId = resultSet.getInt("menuId");
                String menuNama = resultSet.getString("menuNama");
                String menuKategori = resultSet.getString("menuKategori");
                int menuHarga = resultSet.getInt("menuHarga");
                String menuGambar = resultSet.getString("menuGambar");
                menu = new Menu(menuId, menuNama, menuKategori, menuHarga, menuGambar);
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return menu;
    }
    
}