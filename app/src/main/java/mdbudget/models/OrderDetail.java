package mdbudget.models;

public class OrderDetail {

    int orderDetailId;
    Menu orderDetailMenu;
    int orderDetailMenuAmount; 
    
    public OrderDetail(Menu orderDetailMenu, int orderDetailMenuAmount) {
        this.orderDetailMenu = orderDetailMenu;
        this.orderDetailMenuAmount = orderDetailMenuAmount;
    }

    public OrderDetail(Menu orderDetailMenu) {
        this.orderDetailMenu = orderDetailMenu;
        this.orderDetailMenuAmount = 1;
    }

    public int getOrderDetail() {
        return orderDetailId;
    }

    public void setOrderDetail(int orderDetailId) {
        this.orderDetailId = orderDetailId;
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

}
