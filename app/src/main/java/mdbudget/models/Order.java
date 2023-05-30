package mdbudget.models;

public class Order {
    int orderId;
    OrderDetail orderDetail;
    int orderTotal;
    String orderDate;
    
    public Order(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
    
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public OrderDetail getOrderDetail() {
        return orderDetail;
    }
    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
