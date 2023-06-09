package mdbudget.models;

public class OrderDetail extends BaseModel{
    private int orderDetailId;
    private Order orderDetailOrder;
    private Menu orderDetailMenu;
    private int orderDetailMenuAmount; 
    
    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrderDetailOrder() {
        return orderDetailOrder;
    }

    public void setOrderDetailOrder(Order orderDetailOrder) {
        this.orderDetailOrder = orderDetailOrder;
    }

    public Menu getOrderDetailMenu() {
        return orderDetailMenu;
    }

    public void setOrderDetailMenu(Menu orderDetailMenu) {
        this.orderDetailMenu = orderDetailMenu;
    }

    public int getOrderDetailMenuAmount() {
        return orderDetailMenuAmount;
    }

    public void setOrderDetailMenuAmount(int orderDetailMenuAmount) {
        this.orderDetailMenuAmount = orderDetailMenuAmount;
    }

    public OrderDetail(int orderDetailId,Order orderDetailOrder ,Menu orderDetailMenu, int orderDetailMenuAmount) {
        this.orderDetailId = orderDetailId;
        this.orderDetailOrder = orderDetailOrder;
        this.orderDetailMenu = orderDetailMenu;
        this.orderDetailMenuAmount = orderDetailMenuAmount;
    }
    
    public OrderDetail(Menu orderDetailMenu) {
        this.orderDetailMenu = orderDetailMenu;
        this.orderDetailMenuAmount = 1;
    }
    

    @Override
    public void displayInfo() {
        System.out.println("Order Detail ID: " + getId());
        System.out.println("Order Detail Menu: " + getOrderDetailMenu());
        System.out.println("Order Detail Menu Amount: " + getOrderDetailMenuAmount());
    }

}
