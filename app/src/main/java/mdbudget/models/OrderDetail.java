package mdbudget.models;

public class OrderDetail extends BaseModel{
    int orderDetailId;
    Menu orderDetailMenu;
    int orderDetailMenuAmount; 
    
    public OrderDetail(int orderDetailId, Menu orderDetailMenu, int orderDetailMenuAmount) {
        this.orderDetailId = orderDetailId;
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
