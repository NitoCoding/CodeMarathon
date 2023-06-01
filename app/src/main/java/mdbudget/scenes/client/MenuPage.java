package mdbudget.scenes.client;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mdbudget.controllers.MenuController;
import mdbudget.interfaces.Showable;
import mdbudget.models.Menu;
import mdbudget.models.OrderDetail;
import mdbudget.scenes.BaseScene;
import mdbudget.utils.ImageGenerator;


public class MenuPage extends BaseScene implements Showable {
    private ArrayList<OrderDetail> listOrder;

    public MenuPage(Stage stage){
        super(stage);
        this.listOrder = new ArrayList<>();
    }

    public MenuPage(Stage stage, ArrayList<OrderDetail> listOrder){
        super(stage);
        this.listOrder = listOrder;
    }

    @Override
    public void show(){
        final int itemLogoWidth = 25;
        final int itemLogoHeight = 25;

        Label menuLabel = new Label("Menu");

        FlowPane menuContainer = new FlowPane();
        menuContainer.setHgap(20);
        menuContainer.setVgap(10);
        menuContainer.setAlignment(Pos.BASELINE_CENTER);

        System.out.println(listOrder);

        ArrayList<Menu> listMenu = MenuController.getAllData();
        // System.out.println(listMenu);
        Button[] data = new Button[listMenu.size()];
        int i = 0;
        for (Menu menu : listMenu) {
            VBox buttonView = new VBox();
            Label menuNama = new Label(menu.getMenuNama());
            menuNama.setWrapText(true);
            // final String gambarMenu = menu.menuGambar;

            ImageView menuGambar = new ImageView(ImageGenerator.generate("burger.png", itemLogoWidth, itemLogoHeight));
            // ImageView menuGambar = new ImageView(ImageGenerator.generate(menu.getMenuGambar().toString(), itemLogoWidth, itemLogoHeight));

            buttonView.getChildren().addAll(menuGambar, menuNama);
            buttonView.setAlignment(Pos.BASELINE_CENTER);

            data[i] = new Button();
            data[i].setGraphic(buttonView);
            menuContainer.getChildren().add(data[i]);
            
            final int currentIndex = i;

            data[i].setOnAction(event -> {
                boolean existsInOrder = false;
                for (OrderDetail order : listOrder) {
                    Menu test = order.getOrderDetailMenu();
                    if (test.equals(menu)) {
                        existsInOrder = true;
                        listOrder.remove(order);
                        break;
                    }
                }


                if (existsInOrder) {
                    // Order already exists in the list
                    data[currentIndex].setStyle("-fx-background-color: none;");
                } else {
                    OrderDetail newOrder = new OrderDetail(menu);
                    listOrder.add(newOrder);
                    data[currentIndex].setStyle("-fx-background-color: green;");
                }
            });
            i++;
        }

        VBox vbox = new VBox();
        vbox.getChildren().addAll(menuLabel, menuContainer);
        vbox.setAlignment(Pos.BASELINE_CENTER);

        AnchorPane anchorPane = new AnchorPane();

        // Create buttons and set their positions using anchors
        Button orderButton = new Button("order List");
        AnchorPane.setBottomAnchor(orderButton, 10.0);
        AnchorPane.setLeftAnchor(orderButton, 10.0);
        AnchorPane.setRightAnchor(orderButton, 10.0);
        orderButton.setOnAction(action -> {
            // if(listOrder.size() > 0){
            // cartPage();
            // }
            // listOrder = new ArrayList<>();
            CartPage cartPageScene = new CartPage(stage, listOrder);
            cartPageScene.show();
        });

        // Add buttons to the AnchorPane
        anchorPane.getChildren().addAll(orderButton);

        BorderPane mainPane = new BorderPane();
        // mainPane.setTop(menuLabel);
        // mainPane.setCenter(menuContainer);
        mainPane.setTop(vbox);
        mainPane.setBottom(anchorPane);

        Scene scene = new Scene(mainPane, stage.getWidth(), stage.getHeight());

        stage.setScene(scene);
    }
}