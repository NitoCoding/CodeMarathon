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
    private static Connection conn;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

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


            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                int orderTotal = resultSet.getInt("orderTotal");
                User orderUser = UserController.getUserById(resultSet.getInt("orderUserId"));
                String orderDate = resultSet.getString("orderDate");

                Order order = new Order(orderId, orderTotal, orderUser, orderDate);

                orders.add(order);
            }
                // orderId, orderTotal, orderUserId, orderDate



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
                String orderDate = resultSet.getString("orderDate");

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

    public static ArrayList<BaseModel> getDetailsById(int id) {
        ArrayList<BaseModel> details = new ArrayList<>();
        try {
            conn = Connector.getConnection();
            // System.out.println(id);
            String queryOrder = "SELECT * FROM tb_OrderDetail WHERE orderDetailOrderId = ?";
            PreparedStatement statement = conn.prepareStatement(queryOrder);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            // System.out.println(resultSet);
    
            while (resultSet.next()) {
                // Retrieve the data from the resultSet
                int detailId = resultSet.getInt("orderDetailId");
                // System.out.println(id);
                int orderDetailOrderId = resultSet.getInt("orderDetailOrderId");
                int orderDetailMenuId = resultSet.getInt("orderDetailMenuId");
                int orderDetailMenuAmount = resultSet.getInt("orderDetailMenuAmount");
    
                // Get the associated objects using the retrieved IDs
                Order orderDetailOrder = OrderController.getOrderById(orderDetailOrderId);
                Menu orderDetailMenu = MenuController.getDataById(orderDetailMenuId);
    
                // Create the OrderDetail object
                OrderDetail detail = new OrderDetail(detailId, orderDetailOrder, orderDetailMenu, orderDetailMenuAmount);
                System.out.println(detail);
    
                details.add(detail);
            }
            System.out.println(details);
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
        return details;
    }
    

    // public static boolean addOrderDetail(OrderDetail orderDetail, )

}
