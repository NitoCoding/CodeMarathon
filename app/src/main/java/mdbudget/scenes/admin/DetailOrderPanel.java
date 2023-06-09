package mdbudget.scenes.admin;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import mdbudget.controllers.OrderController;
import mdbudget.models.BaseModel;
import mdbudget.models.OrderDetail;

public class DetailOrderPanel extends Dialog<Void> {
    public DetailOrderPanel(int id) {
        setTitle("Detail Order");
        setHeaderText(null);

        ArrayList<BaseModel> dataRaw = OrderController.getDetailById(id);

        System.out.println(dataRaw);

        ArrayList<OrderDetail> dataDetail = new ArrayList<>();

        for (BaseModel model : dataRaw) {
            if (model instanceof OrderDetail) {
                dataDetail.add((OrderDetail) model);
            }
        }

        ObservableList<OrderDetail> data = FXCollections.observableArrayList(dataDetail);

        TableView<OrderDetail> tableView = new TableView<>();
        tableView.setPrefWidth(400);
        tableView.setPrefHeight(300);

        TableColumn<OrderDetail, String> menuColumn = new TableColumn<>("Menu");
        TableColumn<OrderDetail, Integer> amountColumn = new TableColumn<>("Amount");
        TableColumn<OrderDetail, Integer> priceColumn = new TableColumn<>("Price");

        menuColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));
        amountColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3).subtract(5));
        priceColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

        menuColumn.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getOrderDetailMenu().getMenuNama()));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("orderDetailMenuAmount"));
        priceColumn.setCellValueFactory(param -> {
            int price = param.getValue().getOrderDetailMenu().getMenuHarga()
                    * param.getValue().getOrderDetailMenuAmount();
            return new SimpleIntegerProperty(price).asObject();
        });

        tableView.getColumns().addAll(menuColumn, amountColumn, priceColumn);
        tableView.setItems(data);

        GridPane gridPane = new GridPane();
        GridPane.setHgrow(tableView, Priority.ALWAYS);
        GridPane.setVgrow(tableView, Priority.ALWAYS);
        gridPane.add(tableView, 0, 0);

        getDialogPane().setContent(gridPane);

        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Button closeButton = (Button) getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.setOnAction(e -> close());
    }

}
