package mdbudget.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mdbudget.models.OrderDetail;
import mdbudget.utils.Connector;
import mdbudget.utils.IdDatabaseGenerator;

public class OrderController {
    static Connection conn;
    static Statement statement;
    static ResultSet resultSet;

    public static boolean addOrder(ArrayList<OrderDetail> order, int total) {
        boolean status = false;
        try {
            int rowsAffected = 0;
            int orderId = IdDatabaseGenerator.generateId("orderId", "tb_order");
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
                                orderDetail.getOrderDetailMenu().getId(), orderDetail.getOrderDetailMenuAmount());
                        rowsAffected += statement.executeUpdate(formattedQueryDetail);
                    }
                }
                if (rowsAffected > 1) {
                    status = true;
                }
                conn.commit();
                // for (int i = 0; i < order.size(); i++) {
            }
            // }
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
        return status;
    }

}
