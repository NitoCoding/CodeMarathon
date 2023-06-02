package mdbudget.models;

public class OrderDetail extends BaseModel{
    int orderDetailId;
    Order orderDetailOrder;
    Menu orderDetailMenu;
    int orderDetailMenuAmount; 
    
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


    public Menu getOrderDetailMenu() {
        return orderDetailMenu;
    }

    public void setOrderDetailMenu(Menu orderDetailMenu) {
        this.orderDetailMenu = orderDetailMenu;
    }

    public Order getOrderDetailorder() {
        return orderDetailOrder;
    }

    public void setOrderDetailOrder(Order orderDetailOrder) {
        this.orderDetailOrder = orderDetailOrder;
    }

    public int getOrderDetailMenuAmount() {
        return orderDetailMenuAmount;
    }

    public void setOrderDetailMenuAmount(int orderDetailMenuAmount) {
        this.orderDetailMenuAmount = orderDetailMenuAmount;
    }

    @Override
    public void displayInfo() {
        System.out.println("Order Detail ID: " + getId());
        System.out.println("Order Detail Menu: " + getOrderDetailMenu());
        System.out.println("Order Detail Menu Amount: " + getOrderDetailMenuAmount());
    }

}
