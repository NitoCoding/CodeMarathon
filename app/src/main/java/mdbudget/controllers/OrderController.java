package mdbudget.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mdbudget.models.BaseModel;
import mdbudget.models.Menu;
import mdbudget.models.Order;
import mdbudget.models.OrderDetail;
import mdbudget.models.User;
import mdbudget.utils.Connector;
import mdbudget.utils.DateGenerator;
import mdbudget.utils.IdDatabaseGenerator;

public class OrderController {
    static Connection conn;
    static PreparedStatement statement;
    static ResultSet resultSet;

    public static boolean addOrder(ArrayList<OrderDetail> order, int total, int userId) {
        boolean status = false;
        try {
            int rowsAffected = 0;
            int orderId = IdDatabaseGenerator.generateId("orderId", "tb_order");
            conn = Connector.getConnection();
            String queryOrder = "INSERT INTO tb_Order (orderId, orderTotal, orderUserId, orderDate) VALUES (?, ?, ?, ?)";
            statement = conn.prepareStatement(queryOrder);
            statement.setInt(1, orderId);
            statement.setInt(2, total);
            statement.setInt(3, userId);
            statement.setString(4, DateGenerator.getDateString());
            rowsAffected += statement.executeUpdate();
            conn.setAutoCommit(false);

            if (rowsAffected > 0) {
                int detailId = IdDatabaseGenerator.generateId("orderDetailId", "tb_OrderDetail");
                for (OrderDetail orderDetail : order) {
                    String queryDetailString = "INSERT INTO tb_OrderDetail (orderDetailId, orderDetailOrderId, orderDetailMenuId, orderDetailMenuAmount) VALUES (?, ?, ?, ?)";
                    statement = conn.prepareStatement(queryDetailString);
                    statement.setInt(1, detailId);
                    statement.setInt(2, orderId);
                    statement.setInt(3, orderDetail.getOrderDetailMenu().getMenuId());
                    statement.setInt(4, orderDetail.getOrderDetailMenuAmount());
                    rowsAffected += statement.executeUpdate();
                    detailId++;
                }
            }

            if (rowsAffected > 1) {
                status = true;
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (

        SQLException e) {
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

    public static ArrayList<BaseModel> getAllDataOrders() {
        ArrayList<BaseModel> orders = new ArrayList<>();
        try {
            conn = Connector.getConnection();
            String queryOrder = "SELECT * FROM tb_Order";
            PreparedStatement statement = conn.prepareStatement(queryOrder);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("orderId");
                    int orderTotal = resultSet.getInt("orderTotal");
                    User orderUser = UserController.getUserById(resultSet.getInt("orderUserId"));
                    String orderDate = resultSet.getString("orderDate");

                    Order order = new Order(orderId, orderTotal, orderUser, orderDate);

                    orders.add(order);
                }
                // orderId, orderTotal, orderUserId, orderDate

            }

        } catch (

        SQLException e) {
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
        return orders;
    }

    public static Order getOrderById(int id) {
        Order order = null;
        try {
            conn = Connector.getConnection();
            String queryOrder = "SELECT * FROM tb_Order";
            PreparedStatement statement = conn.prepareStatement(queryOrder);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // orderId, orderTotal, orderUserId, orderDate
                int orderId = resultSet.getInt("orderId");
                int orderTotal = resultSet.getInt("orderTotal");
                User orderUser = UserController.getUserById(resultSet.getInt("orderUserId"));
                String orderDate = resultSet.getString("queryDate");

                order = new Order(orderId, orderTotal, orderUser, orderDate);

            }

        } catch (

        SQLException e) {
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
        return order;
    }

    public static OrderDetail getDetailById(int id) {
        OrderDetail detail = null;
        try {
            conn = Connector.getConnection();
            String queryOrder = "SELECT * FROM tb_Order";
            PreparedStatement statement = conn.prepareStatement(queryOrder);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // orderDetailId, orderDetailOrderId, orderDetailMenuId, orderDetailMenuAmount
                int detailId = resultSet.getInt("orderDetailId");
                Order orderDetailOrderId = OrderController.getOrderById(resultSet.getInt("orderDetailOrderId"));
                Menu orderDetailMenuId = MenuController.getDataById(resultSet.getInt("orderDetailMenuId"));
                int orderDetailMenuAmount = resultSet.getInt("orderDetailMenuAmount");

                detail = new OrderDetail(detailId, orderDetailOrderId, orderDetailMenuId, orderDetailMenuAmount);

            }

        } catch (

        SQLException e) {
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
        return detail;
    }

    // public static boolean addOrderDetail(OrderDetail orderDetail, )

}
