package mdbudget.scenes.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import mdbudget.interfaces.Showable;
import mdbudget.models.OrderDetail;
import mdbudget.scenes.BaseScene;

public class CartPage extends BaseScene implements Showable {
    private ArrayList<OrderDetail> listOrder;

    CartPage(Stage stage, ArrayList<OrderDetail> listOrder){
        super(stage);
        this.listOrder = listOrder;
    }

    @Override
    public void show(){
        Label cartLabel = new Label("Cart");

        // System.out.println("Number of orders: " + listOrder.size());

        ObservableList<OrderDetail> orders = FXCollections.observableArrayList();

        orders.addAll(listOrder);

        Label total = new Label();

        // System.out.println("Number of orders: " + orders.size());

        TableView<OrderDetail> cartTable = new TableView<>();

        TableColumn<OrderDetail, String> menuNameCol = new TableColumn<>("Name");
        menuNameCol.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getOrderDetailMenu().getMenuNama()));

        TableColumn<OrderDetail, Integer> menuQuantityCol = new TableColumn<>("Amount");
        menuQuantityCol.setCellValueFactory(new PropertyValueFactory<>("orderDetailMenuAmount"));

        TableColumn<OrderDetail, Integer> menuPriceCol = new TableColumn<>("Price");
        menuPriceCol.setCellValueFactory(
                param -> new SimpleIntegerProperty(param.getValue().getOrderDetailMenu().getMenuHarga()).asObject());

        TableColumn<OrderDetail, Void> menuActionCol = new TableColumn<>("Action");
        menuActionCol.setCellFactory(param -> new TableCell<OrderDetail, Void>() {
            private void calculateTotalPrice() {
                double totalPrice = 0.0;
                for (OrderDetail order : cartTable.getItems()) {
                    Double price = menuPriceCol.getCellObservableValue(order).getValue().doubleValue()
                            * menuQuantityCol.getCellObservableValue(order).getValue().doubleValue();
                    if (price != null) {
                        totalPrice += price;
                    }
                }
                total.setText("Total: " + Double.toString(totalPrice));
            }

            private final Button plusButton = new Button("+");
            private final Button minusButton = new Button("-");
            private final Button deleteButton = new Button("!");

            {
                plusButton.setOnAction(event -> {
                    OrderDetail order = getTableView().getItems().get(getIndex());

                    order.setOrderDetailMenuAmount(order.getOrderDetailMenuAmount() + 1);
                    int index = listOrder.indexOf(order);
                    if (index != -1) {
                        OrderDetail updatedOrder = listOrder.get(index);
                        updatedOrder.setOrderDetailMenuAmount(order.getOrderDetailMenuAmount());
                        // Update any other properties if necessary
                        listOrder.set(index, updatedOrder);
                    }
                    cartTable.refresh();
                    calculateTotalPrice();
                });

                minusButton.setOnAction(event -> {
                    OrderDetail order = getTableView().getItems().get(getIndex());
                    if (order.getOrderDetailMenuAmount() == 1) {
                        orders.remove(order);
                    } else {

                        order.setOrderDetailMenuAmount(order.getOrderDetailMenuAmount() - 1);
                    }

                    int index = listOrder.indexOf(order);
                    if (index != -1) {
                        OrderDetail updatedOrder = listOrder.get(index);
                        updatedOrder.setOrderDetailMenuAmount(order.getOrderDetailMenuAmount());
                        // Update any other properties if necessary
                        listOrder.set(index, updatedOrder);
                    }
                    cartTable.refresh();
                    calculateTotalPrice();
                });

                deleteButton.setOnAction(event -> {
                    OrderDetail order = getTableView().getItems().get(getIndex());
                    // Handle edit button action for the specific menu item
                    // System.out.println("Edit button clicked for: " +
                    // order.getOrderDetailMenu().getMenuNama());

                    orders.remove(order);

                    int index = listOrder.indexOf(order);
                    if (index != -1) {
                        OrderDetail updatedOrder = listOrder.get(index);
                        listOrder.remove(updatedOrder);
                    }
                    // orders.clear();
                    // orders.addAll(listOrder);
                    cartTable.refresh();
                    calculateTotalPrice();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    HBox buttonsBox = new HBox(plusButton, minusButton, deleteButton);
                    buttonsBox.setSpacing(5.0);
                    setGraphic(buttonsBox);
                    setText(null);
                }
            }
        });

        menuNameCol.setPrefWidth(180); // Set the preferred width of the Name column to 200 pixels
        menuQuantityCol.setPrefWidth(90); // Set the preferred width of the Amount column to 100 pixels
        menuPriceCol.setPrefWidth(90); // Set the preferred width of the Price column to 100 pixels

        cartTable.getColumns().add(menuNameCol);
        cartTable.getColumns().add(menuQuantityCol);
        cartTable.getColumns().add(menuPriceCol);
        cartTable.getColumns().add(menuActionCol);

        cartTable.setPadding(new Insets(0, 20, 0, 20));

        cartTable.setItems(orders);

        double totalPrice = 0.0;
        for (OrderDetail order : cartTable.getItems()) {
            double price = menuPriceCol.getCellObservableValue(order).getValue().doubleValue()
                    * menuQuantityCol.getCellObservableValue(order).getValue().doubleValue();
            totalPrice += price;
        }
        total.setText("Total: " + Double.toString(totalPrice));
        // ? optional
        // TableColumn<String, String> menuTotalCol =new TableColumn<>("Total");
        // menuTotalCol.setCellValueFactory(new PropertyValueFactory<>("Total"));

        // * how to get totalPrice

        // System.out.println("Total Price: " + totalPrice);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(cartLabel, cartTable);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().add(total);
        vbox.setAlignment(Pos.CENTER_LEFT);

        AnchorPane anchorPane = new AnchorPane();

        // Create buttons and set their positions using anchors
        Button menuPageButton = new Button("Menu List");
        menuPageButton.prefWidth((stage.getWidth() / 2) - 10.0);
        AnchorPane.setBottomAnchor(menuPageButton, 10.0);
        AnchorPane.setLeftAnchor(menuPageButton, 10.0);
        AnchorPane.setRightAnchor(menuPageButton, (double) stage.getWidth() / 2 + 5);
        menuPageButton.setOnAction(action -> {
            MenuPage menuPageScene = new MenuPage(stage);
            menuPageScene.show();
        });

        Button orderButton = new Button("Order");
        AnchorPane.setBottomAnchor(orderButton, 10.0);
        AnchorPane.setLeftAnchor(orderButton, (double) stage.getWidth() / 2 + 5);
        AnchorPane.setRightAnchor(orderButton, 10.0);
        orderButton.setOnAction(event -> {
            OrderPage orderPageScene = new OrderPage(stage);
            orderPageScene.show();
        });

        //
        orderButton.prefWidthProperty().bind(anchorPane.widthProperty().divide(2));

        // Add buttons to the AnchorPane
        anchorPane.getChildren().addAll(menuPageButton, orderButton);

        BorderPane mainPane = new BorderPane();
        // mainPane.setTop(menuLabel);
        // mainPane.setCenter(menuContainer);
        mainPane.setTop(vbox);
        mainPane.setBottom(anchorPane);

        Scene scene = new Scene(mainPane, stage.getWidth(), stage.getHeight());

        stage.setScene(scene);
    }
}
