package mdbudget.models;

import mdbudget.utils.DateGenerator;

public class Order extends BaseModel {
    private int orderId;
    private int orderTotal;
    private User orderUser;
    private String orderDate;

    public Order(int id, int total, User user) {
        this.orderId = id;
        this.orderTotal = total;
        this.orderUser = user;
        this.orderDate = DateGenerator.getDateString();
    }

    public Order(int id, int total, User user, String date) {
        this.orderId = id;
        this.orderTotal = total;
        this.orderUser = user;
        this.orderDate = DateGenerator.getDateString();
    }

    @Override
    public void displayInfo() {
        System.out.println("Order ID: " + getId());
        System.out.println("Order Total: " + getOrderTotal());
        System.out.println("Order User ID: " + getOrderUser().getId());
        System.out.println("Order Date: " + getOrderDate());
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
