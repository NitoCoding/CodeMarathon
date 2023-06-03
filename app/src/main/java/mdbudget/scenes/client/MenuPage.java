package mdbudget.scenes.client;

import java.util.ArrayList;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mdbudget.controllers.MenuController;
import mdbudget.interfaces.Showable;
import mdbudget.models.BaseModel;
import mdbudget.models.Menu;
import mdbudget.models.OrderDetail;
import mdbudget.scenes.BaseScene;
import mdbudget.utils.ImageGenerator;

public class MenuPage extends BaseScene implements Showable {
    private ArrayList<OrderDetail> listOrder;
    private ArrayList<BaseModel> listMenu;
    private int userId;

    public MenuPage(Stage stage, int userId) {
        super(stage);
        this.listOrder = new ArrayList<>();
        this.listMenu = MenuController.getAllDataMenus();
        this.userId = userId;
    }

    public MenuPage(Stage stage, ArrayList<OrderDetail> listOrder, int userId) {
        super(stage);
        this.listOrder = listOrder;
        this.listMenu = MenuController.getAllDataMenus();
        this.userId = userId;
    }

    @Override
    public void show() {
        final int itemLogoWidth = 75;
        final int itemLogoHeight = 75;
        
        GridPane headerContainer = new GridPane();

        Label menuLabel = new Label("Menu");
        menuLabel.setStyle(
            "-fx-font-family: 'Jacques Francois';" +
            "-fx-font-size: 40px;" +
            "-fx-font-weight: 400;" +
            "-fx-line-height: 53px;" +
            "-fx-letter-spacing: 0em;" +
            "-fx-text-alignment: center;" +
            "-fx-text-fill: #385748;"
        );

        headerContainer.add(menuLabel,0,0);
        headerContainer.setAlignment(Pos.TOP_CENTER);

        GridPane menuContainer = new GridPane();
        menuContainer.setAlignment(Pos.TOP_CENTER);
        menuContainer.setHgap(10);
        menuContainer.setVgap(10);

        Button[] data = new Button[listMenu.size()];
        int i = 0;
        int row = 0;
        int col = 0;
        ArrayList<Menu> menuList = new ArrayList<>();
        for (BaseModel model : listMenu) {
            if (model instanceof Menu) {
                menuList.add((Menu) model);
            }
        }
        for (Menu menu : menuList) {
            System.out.println(menu.getMenuGambar());
            ImageView menuGambar = new ImageView(ImageGenerator.generate(menu.getMenuGambar()+".png", itemLogoWidth, itemLogoHeight));
            Label menuNama = new Label(menu.getMenuNama());
            menuNama.setWrapText(true);

            GridPane buttonView = new GridPane();
            buttonView.add(menuGambar, 0, 0);
            buttonView.add(menuNama, 0, 1);
            buttonView.setAlignment(Pos.CENTER);
            buttonView.setVgap(5);

            GridPane.setHalignment(menuGambar, HPos.CENTER);
            GridPane.setValignment(menuGambar, VPos.CENTER);

            data[i] = new Button();
            data[i].setGraphic(buttonView);
            data[i].setStyle(
                    "-fx-pref-height: 140px; " +
                            "-fx-pref-width: 120px; " +
                            "-fx-background-color: transparent; " +
                            "-fx-background-radius: 15px;" +
                            "-fx-border-radius: 15px; " +
                            "-fx-border-width: 2px; " +
                            "-fx-border-color: #000000;");

            menuContainer.add(data[i], col, row);

            col++;
            if (col == 2) {
                col = 0;
                row += 2;
            }

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
                    data[currentIndex].setStyle(data[currentIndex].getStyle() + "-fx-border-color: #000000;");
                } else {
                    OrderDetail newOrder = new OrderDetail(menu);
                    listOrder.add(newOrder);
                    data[currentIndex].setStyle(data[currentIndex].getStyle() + "-fx-border-color: #00ff00;");
                }
            });
            i++;
        }

        GridPane stickyButtonContainer = new GridPane();

        Button logoutButton = new Button("Log out");
        logoutButton.setStyle(
            "-fx-max-height: 45px; " +
            "-fx-min-width: 100px; " +
            "-fx-background-color: #D96161;" +
            "-fx-border-color: none;" +
            "-fx-background-radius: 10;"+
            "-fx-font-family: 'Jaldi';" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: 400;" +
            "-fx-line-height: 34px;" +
            "-fx-text-fill: white;"
        );
        logoutButton.setOnAction(event -> {
            LandingPage landingPageScene = new LandingPage(stage);
            landingPageScene.show();
        });

        stickyButtonContainer.add(logoutButton,0,0);

        Button checkoutButton = new Button("Checkout");
        checkoutButton.setStyle(
            "-fx-min-height: 45px; " +
            "-fx-min-width: 220px; " +
            "-fx-background-color: #58585B;" +
            "-fx-border-color: none;" +
            "-fx-background-radius: 10;"+
            "-fx-font-family: 'Jaldi';" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: 400;" +
            "-fx-line-height: 34px;" +
            "-fx-text-fill: white;"
        );
        checkoutButton.setOnAction(event -> {
            try {
                if (checkoutButton != null) {
                    CartPage cartPageScene = new CartPage(stage, listOrder, userId);
                    cartPageScene.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        stickyButtonContainer.add(checkoutButton, 1, 0);

        stickyButtonContainer.setHgap(25);
        stickyButtonContainer.setAlignment(Pos.CENTER);
        stickyButtonContainer.setPadding(new Insets(10));

        BorderPane layout = new BorderPane();
        layout.setTop(headerContainer);
        layout.setCenter(menuContainer);
        layout.setBottom(stickyButtonContainer);

        Scene scene = new Scene(layout, stage.getWidth(), stage.getHeight());

        stage.setScene(scene);
    }
}
