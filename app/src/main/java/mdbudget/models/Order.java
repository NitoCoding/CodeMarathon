package mdbudget.models;

public class Order extends BaseModel{
    OrderDetail orderDetail;
    int orderTotal;
    String orderDate;
    
    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Order(OrderDetail orderDetail, int orderTotal, String orderDate) {
        this.orderDetail = orderDetail;
        this. orderTotal = orderTotal;
        this.orderDate = orderDate;
    }

    public Order(int id, OrderDetail orderDetail, int orderTotal, String orderDate) {
        super(id);
        this.orderDetail = orderDetail;
        this.orderTotal = orderTotal;
        this.orderDate = orderDate;
    }
    
    public OrderDetail getOrderDetail() {
        return orderDetail;
    }
    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public void displayInfo() {
        System.out.println("Order ID: " + getId());
        System.out.println("Order Detail: " + getOrderDetail());
        System.out.println("Order Total: " + getOrderTotal());
        System.out.println("Order Date: " + getOrderDate());
    }
}
