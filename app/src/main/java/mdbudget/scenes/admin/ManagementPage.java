package mdbudget.scenes.admin;

import java.util.ArrayList;
import java.util.List;

// import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
// import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
// import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import mdbudget.controllers.MenuController;
import mdbudget.controllers.OrderController;
import mdbudget.controllers.UserController;
import mdbudget.interfaces.Showable;
import mdbudget.models.BaseModel;
import mdbudget.models.Menu;
import mdbudget.models.Order;
// import mdbudget.models.OrderDetail;
import mdbudget.models.User;
import mdbudget.scenes.BaseScene;
import mdbudget.scenes.client.LoginPage;

public class ManagementPage extends BaseScene implements Showable {
    private ArrayList<BaseModel> dataTable;
    // private ArrayList<Order> listOrders;
    // private ArrayList<OrderDetail> listOrderDetails;

    public ManagementPage(Stage stage) {
        super(stage);
        // this.listMenus = MenuController.getAllData();
        // this.listOrders = OrderController.getAllData();
    }

    @Override
    public void show() {
        GridPane headerContainer = new GridPane();

        Label cartLabel = new Label("Management");
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

        GridPane contentContainer = new GridPane();
        contentContainer.setPadding(new Insets(0, 5, 0, 5));
        contentContainer.setVgap(20);
        contentContainer.setAlignment(Pos.TOP_LEFT);

        GridPane selectorContainer = new GridPane();
        selectorContainer.setHgap(20);

        Label labelSelector = new Label("Choose table to look");

        ComboBox<String> tableSelector = new ComboBox<>();

        selectorContainer.add(labelSelector, 0, 0);
        selectorContainer.add(tableSelector, 1, 0);
        tableSelector.setStyle(
                "-fx-background-color: #F2911F;" +
                        "-fx-border-color: none;" +
                        "-fx-background-radius: 10;" +
                        "-fx-font-family: 'Jaldi';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: 400;" +
                        "-fx-line-height: 34px;" +
                        "-fx-text-fill: white;");
        tableSelector.setMinWidth(115);
        tableSelector.setMinHeight(45);

        tableSelector.getItems().add("Orders Table");
        tableSelector.getItems().add("Menus Table");
        tableSelector.getItems().add("Users Table");

        GridPane tableContainer = new GridPane();
        // tableContainer.setPadding(new Insets(0, 5, 0, 5));
        TableView<BaseModel> modelTable = new TableView<>();
        modelTable.prefWidthProperty().bind(tableContainer.widthProperty());
        GridPane.setHgrow(tableContainer, Priority.ALWAYS);
        tableContainer.setHgap(20);
        tableContainer.setAlignment(Pos.TOP_CENTER);
        tableContainer.add(modelTable, 0, 0);

        contentContainer.add(selectorContainer, 0, 0);
        contentContainer.add(tableContainer, 0, 1);

        tableSelector.setOnAction((event) -> {
            int selectedIndex = tableSelector.getSelectionModel().getSelectedIndex() + 1;

            tableContainer.getChildren().clear();

            switch (selectedIndex) {
                case 1:
                    dataTable = OrderController.getAllDataOrders();
                    break;

                case 2:
                    dataTable = MenuController.getAllDataMenus();
                    break;

                case 3:
                    dataTable = UserController.getAllDataUsers();
                    break;

                default:
                    break;
            }

            // System.out.println(selectedIndex);
            // System.out.println(dataTable);

            if (dataTable != null && !dataTable.isEmpty() && dataTable.get(0) != null) {
                if (dataTable.get(0) instanceof Menu) {
                    List<TableColumn<Menu, ?>> columns = new ArrayList<>();

                    TableColumn<Menu, String> menuNameCol = new TableColumn<>("Name");
                    menuNameCol.setCellValueFactory(new PropertyValueFactory<>("menuNama"));
                    columns.add(menuNameCol);

                    TableColumn<Menu, Integer> menuPriceCol = new TableColumn<>("Price");
                    menuPriceCol.setCellValueFactory(new PropertyValueFactory<>("menuHarga"));
                    columns.add(menuPriceCol);

                    TableColumn<Menu, String> menuPicCol = new TableColumn<>("Picture");
                    menuPicCol.setCellValueFactory(new PropertyValueFactory<>("menuGambar"));
                    columns.add(menuPicCol);

                    menuNameCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(3));
                    menuPriceCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(3).subtract(5));
                    menuPicCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(3));

                    ObservableList<Menu> menuData = FXCollections.observableArrayList();
                    for (BaseModel model : dataTable) {
                        menuData.add((Menu) model);
                    }

                    TableView<Menu> tableView = createTableView(menuData, columns);
                    tableContainer.add(tableView, 0, 0, 2, 1);
                    tableView.prefWidthProperty().bind(tableContainer.widthProperty());
                } else if (dataTable.get(0) instanceof Order) {
                    List<TableColumn<Order, ?>> columns = new ArrayList<>();

                    TableColumn<Order, String> orderUserCol = new TableColumn<>("Name");
                    orderUserCol.setCellValueFactory(
                            param -> new SimpleStringProperty(param.getValue().getOrderUser().getUserNama()));
                    columns.add(orderUserCol);

                    TableColumn<Order, Integer> orderTotalCol = new TableColumn<>("Total");
                    orderTotalCol.setCellValueFactory(new PropertyValueFactory<>("orderTotal"));
                    columns.add(orderTotalCol);

                    TableColumn<Order, String> orderDateCol = new TableColumn<>("Date");
                    orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
                    columns.add(orderDateCol);

                    orderUserCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(3));
                    orderTotalCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(3).subtract(5));
                    orderDateCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(3));

                    ObservableList<Order> orderData = FXCollections.observableArrayList();
                    for (BaseModel model : dataTable) {
                        orderData.add((Order) model);
                    }

                    TableView<Order> tableView = createTableView(orderData, columns);
                    tableContainer.add(tableView, 0, 0, 2, 1);
                    tableView.prefWidthProperty().bind(tableContainer.widthProperty());

                    tableView.setOnMouseClicked(events -> {
                        if (events.getClickCount() == 1) {
                            Order selectedOrder = tableView.getSelectionModel().getSelectedItem();
                            if (selectedOrder != null) {
                                int orderId = selectedOrder.getOrderId(); // Get the ID of the selected order
                                DetailOrderPanel panel = new DetailOrderPanel(orderId);
                                panel.showAndWait();
                            }
                        }
                    });
                } else if (dataTable.get(0) instanceof User) {
                    List<TableColumn<User, ?>> columns = new ArrayList<>();

                    TableColumn<User, String> userNamaCol = new TableColumn<>("Name");
                    userNamaCol.setCellValueFactory(new PropertyValueFactory<>("userNama"));
                    columns.add(userNamaCol);

                    TableColumn<User, Integer> userPassCol = new TableColumn<>("Password");
                    userPassCol.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
                    columns.add(userPassCol);

                    TableColumn<User, String> userRoleCol = new TableColumn<>("Role");
                    userRoleCol.setCellValueFactory(new PropertyValueFactory<>("userRole"));
                    columns.add(userRoleCol);

                    userNamaCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(3));
                    userPassCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(3).subtract(5));
                    userRoleCol.prefWidthProperty().bind(tableContainer.widthProperty().divide(3));

                    // Create and populate ObservableList<Order> with data
                    ObservableList<User> orderData = FXCollections.observableArrayList();
                    for (BaseModel model : dataTable) {
                        orderData.add((User) model);
                    }

                    TableView<User> tableView = createTableView(orderData, columns);
                    tableContainer.add(tableView, 0, 0, 2, 1);

                    tableView.prefWidthProperty().bind(tableContainer.widthProperty());
                }
            }
        });

        GridPane stickyButtonContainer = new GridPane();

        Button logoutButton = new Button("Log out");
        logoutButton.setStyle(
                "-fx-min-height: 45px; " +
                        "-fx-min-width: 220px; " +
                        "-fx-background-color: #D96161;" +
                        "-fx-border-color: none;" +
                        "-fx-background-radius: 10;" +
                        "-fx-font-family: 'Jaldi';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: 400;" +
                        "-fx-line-height: 34px;" +
                        "-fx-text-fill: white;");
        logoutButton.setOnAction(event -> {
            LoginPage loginPageScene = new LoginPage(stage);
            loginPageScene.show();
        }

        );
        stickyButtonContainer.setHgap(25);
        stickyButtonContainer.setAlignment(Pos.CENTER);
        stickyButtonContainer.setPadding(new Insets(10));
        // GridPane.setMargin(logoutButton, new Insets(0, 25, 0, 0));
        stickyButtonContainer.add(logoutButton, 0, 0);

        BorderPane layout = new BorderPane();
        layout.setTop(headerContainer);
        layout.setCenter(contentContainer);
        layout.setBottom(stickyButtonContainer);

        Scene scene = new Scene(layout, stage.getWidth(), stage.getHeight());

        stage.setScene(scene);
    }

    public <T extends BaseModel> TableView<T> createTableView(ObservableList<T> data, List<TableColumn<T, ?>> columns) {
        TableView<T> tableView = new TableView<>();

        tableView.getColumns().addAll(columns);

        tableView.setItems(data);

        return tableView;
    }
}
