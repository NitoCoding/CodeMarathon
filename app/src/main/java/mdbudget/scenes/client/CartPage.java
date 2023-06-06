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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import mdbudget.controllers.OrderController;
import mdbudget.interfaces.Showable;
import mdbudget.models.OrderDetail;
import mdbudget.scenes.BaseScene;

public class CartPage extends BaseScene implements Showable {
    private ArrayList<OrderDetail> listOrder;
    private int userId;
    private int total;

    CartPage(Stage stage, ArrayList<OrderDetail> listOrder, int userId) {
        super(stage);
        this.listOrder = listOrder;
        this.userId = userId;
        this.total = 0;
    }

    @Override
    public void show() {
        GridPane headerContainer = new GridPane();

        Label cartLabel = new Label("Checkout List");
        cartLabel.setStyle(
                "-fx-font-family: 'Jacques Francois';" +
                        "-fx-font-size: 40px;" +
                        "-fx-font-weight: 400;" +
                        "-fx-line-height: 53px;" +
                        "-fx-letter-spacing: 0em;" +
                        "-fx-text-alignment: center;" +
                        "-fx-text-fill: #385748;"
        // "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 0px, 4px, 4px);"
        );

        headerContainer.add(cartLabel, 0, 0);
        headerContainer.setAlignment(Pos.TOP_CENTER);

        // System.out.println("Number of orders: " + listOrder.size());
        GridPane tableContainer = new GridPane();

        tableContainer.setPadding(new Insets(0, 5, 0, 5));
        // tableContainer.setHgap(20);
        tableContainer.setAlignment(Pos.TOP_CENTER);

        ObservableList<OrderDetail> orders = FXCollections.observableArrayList();

        orders.addAll(listOrder);

        Label totalPriceLabel = new Label();

        // System.out.println("Number of orders: " + orders.size());

        TableView<OrderDetail> cartTable = new TableView<>();
        // cartTable.getStyleClass().add(getClass().getResource("/style/style.css").toExternalForm());
        // cartTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        // System.out.println(cartTable.widthProperty());
        // cartTable.setId("my-table");

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
                            * menuQuantityCol.getCellObservableValue(order).getValue();
                    if (price != null) {
                        totalPrice += price;
                    }
                }
                total = (int) totalPrice;
                totalPriceLabel.setText("Total: " + Double.toString(totalPrice));
            }

            private Button plusButton = new Button("+");
            private Button minusButton = new Button("-");
            private Button deleteButton = new Button("!");

            {
                plusButton.setPrefWidth(30);
                plusButton.setPrefHeight(30);
                minusButton.setPrefWidth(30);
                minusButton.setPrefHeight(30);
                deleteButton.setPrefWidth(30);
                deleteButton.setPrefHeight(30);

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
                    // System.out.println(order.getOrderDetailMenuAmount());
                    // System.out.println(orders);
                    order.setOrderDetailMenuAmount(order.getOrderDetailMenuAmount() - 1);

                    if (order.getOrderDetailMenuAmount() == 0) {
                        orders.remove(order);
                    }

                    int index = listOrder.indexOf(order);
                    if (index != -1) {
                        OrderDetail updatedOrder = listOrder.get(index);
                        updatedOrder.setOrderDetailMenuAmount(order.getOrderDetailMenuAmount());
                        // Update any other properties if necessary
                        if (updatedOrder.getOrderDetailMenuAmount() != 0) {
                            listOrder.set(index, updatedOrder);
                        } else {
                            // System.out.println(listOrder.get(index));
                            listOrder.remove(index);
                        }

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

        // System.out.println(tableContainer.widthProperty().divide(4).doubleValue());
        menuNameCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(4).add(25));
        menuQuantityCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(4).subtract(20));
        menuPriceCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(4).subtract(20));
        menuActionCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(4));

        cartTable.prefWidthProperty().bind(tableContainer.widthProperty());

        menuNameCol.setResizable(false);
        menuQuantityCol.setResizable(false);
        menuPriceCol.setResizable(false);
        menuActionCol.setResizable(false);

        cartTable.getColumns().add(menuNameCol);
        cartTable.getColumns().add(menuQuantityCol);
        cartTable.getColumns().add(menuPriceCol);
        cartTable.getColumns().add(menuActionCol);

        // cartTable.setPadding(new Insets(0, 20, 0, 20));

        cartTable.setItems(orders);

        double totalPrice = 0.0;
        for (OrderDetail order : cartTable.getItems()) {
            double price = menuPriceCol.getCellObservableValue(order).getValue().doubleValue()
                    * menuQuantityCol.getCellObservableValue(order).getValue().doubleValue();
            totalPrice += price;
            total = (int) totalPrice;
        }
        totalPriceLabel.setText("Total: " + Double.toString(totalPrice));

        tableContainer.add(cartTable, 0, 0);
        tableContainer.add(totalPriceLabel, 0, 1);

        GridPane stickyButtonContainer = new GridPane();

        Button menuPageButton = new Button("Back to Menu");
        menuPageButton.setStyle(
                "-fx-min-height: 45px; " +
                        "-fx-min-width: 170px; " +
                        "-fx-background-color: #58585B;" +
                        "-fx-border-color: none;" +
                        "-fx-background-radius: 10;" +
                        "-fx-font-family: 'Jaldi';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: 400;" +
                        "-fx-line-height: 34px;" +
                        "-fx-text-fill: white;");
        menuPageButton.setOnAction(event -> {
            // listOrder.clear();
            MenuPage menuPageScene = new MenuPage(stage, listOrder, userId);
            menuPageScene.show();
        }

        );
        // GridPane.setMargin(logoutButton, new Insets(0, 25, 0, 0));
        stickyButtonContainer.add(menuPageButton, 0, 0);

        // Create buttons and add them to the gridPane
        Button orderButton = new Button("Order");
        orderButton.setStyle(
                "-fx-min-height: 45px; " +
                        "-fx-min-width: 170px; " +
                        "-fx-background-color: #58585B;" +
                        "-fx-border-color: none;" +
                        "-fx-background-radius: 10;" +
                        "-fx-font-family: 'Jaldi';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: 400;" +
                        "-fx-line-height: 34px;" +
                        "-fx-text-fill: white;");
        // orderButton.setOnAction(event -> {
        // CartPage cartPageScene = new CartPage(stage, listOrder,userId);
        // cartPageScene.show();
        // });

        orderButton.setOnAction(event -> {
            try {
                if (listOrder.size() > 0) {
                    boolean status = OrderController.addOrder(listOrder, total, userId);
                    if (status) {
                        MenuPage menuPageScene = new MenuPage(stage, userId);
                        menuPageScene.show();
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        });
        // GridPane.setMargin(checkoutButton, new Insets(0, 0, 0, 25));
        stickyButtonContainer.add(orderButton, 1, 0);

        // Set the alignment and padding for the gridPane
        stickyButtonContainer.setHgap(25);
        stickyButtonContainer.setAlignment(Pos.CENTER);
        stickyButtonContainer.setPadding(new Insets(10));

        // Set the constraints for the button
        // GridPane.setHalignment(orderButton, HPos.CENTER);

        // Add the gridPane to the BorderPane
        BorderPane layout = new BorderPane();
        layout.setTop(headerContainer);
        layout.setCenter(tableContainer);
        layout.setBottom(stickyButtonContainer);

        Scene scene = new Scene(layout, stage.getWidth(), stage.getHeight());

        stage.setScene(scene);
    }
}
