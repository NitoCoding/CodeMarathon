package mdbudget.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mdbudget.Connector;
import mdbudget.models.Menu;
import mdbudget.models.Order;
import mdbudget.models.OrderDetail;

public class OrderController {
    static Connection conn;
    static Statement statement;
    static ResultSet resultSet;

    public static int getLastOrderDataId() {
        int id = 0;
        try {
            conn = Connector.getConnection();

            statement = conn.createStatement();
            resultSet = statement.executeQuery("select max(orderId) as orderId from order");

            // menus = new ArrayList<>();
            while (resultSet.next()) {
                id = resultSet.getInt("orderId");
                if (id == 0) {
                    id += 1;
                }
                // int menuId = resultSet.getInt("menuId");
                // String menuNama = resultSet.getString("menuNama");
                // String menuKategori = resultSet.getString("menuKategori");
                // int menuHarga = resultSet.getInt("menuHarga");
                // String menuGambar = resultSet.getString("menuGambar");

                // Menu menu = new Menu(menuId, menuNama, menuKategori, menuHarga, menuGambar);
                // menus.add(menu);
            }
        } catch (SQLException e) {
            // Handle the exception or log the error
            e.printStackTrace();
        }
        return id;
    }

    public static boolean addOrder(ArrayList<OrderDetail> order, int total) {
        boolean status = false;
        try {
            int rowsAffected = 0;
            int orderId = getLastOrderDataId();
            conn = Connector.getConnection();
            statement = conn.createStatement();
            if (order.size() > 1 && order.size() != 0) {
                String queryOrder = "INSERT INTO order (orderId, orderDetailId, orderTotal) VALUES (%d, %d, %d)";
                String formattedQuery = String.format(queryOrder, orderId, orderId, total);
                rowsAffected += statement.executeUpdate(formattedQuery);

                if (rowsAffected > 0) {
                    for (OrderDetail orderDetail : order) {
                        String queryDetailString = "INSERT INTO orderDetail (orderDetailId, orderMenuId, orderAmount) VALUES (%d, %d, %d)";
                        String formattedQueryDetail = String.format(queryDetailString, orderId,
                                orderDetail.getOrderDetailMenu().getMenuId(), orderDetail.getOrderDetailMenuAmount());
                        rowsAffected += statement.executeUpdate(formattedQueryDetail);
                    }
                }
                if (rowsAffected > 1) {
                    status = true;
                }
                // for (int i = 0; i < order.size(); i++) {
            }
            // }
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return status;
    }

}
