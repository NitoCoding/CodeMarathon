/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package mdbudget;

import mdbudget.models.Menu;
import mdbudget.models.User;
import mdbudget.controllers.MenuController;
import mdbudget.controllers.UserController;
// import mdbudget.controllers.userController;

import java.sql.SQLException;
// import mdbudget.controllers.*;
// import mdbudget.models.*;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class App extends Application {
    Stage mainStage;
    final int width = 400;
    final int height = 800;
    // TODO change arraylist Integer to MenuItem or Menu models after init sqlite
    ArrayList<Menu> listOrder;

    public static void main(String[] args) {
        System.out.println("Launching...");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainStage = primaryStage;
        mainStage.setTitle("Aplikasi Ganti Nama");

        loginPage();

        mainStage.show();
    }

    // public void menuPage(){
    // Button button = new Button("Pindah Page");
    // button.setOnAction(action -> {
    // loginPage();
    // });
    // Scene scene = new Scene(button, 400, 600);
    // //
    // scene.getStylesheets().add(getClass().getResource("/styles/styleApp.css").toExternalForm());

    // mainStage.setScene(scene);
    // }

    public void loginPage() {
        final int logoWidth = 200;
        final int logoHeight = 150;

        Label loginLabel = new Label("Login");

        ImageView brandLogo = new ImageView(new Image(getClass().getResource("/images/mcdonalds.png").toString(),
                logoWidth, logoHeight, false, false));

        StackPane brandContainer = new StackPane(brandLogo);

        TextField username = new TextField();
        PasswordField password = new PasswordField();

        VBox formContainer = new VBox();
        formContainer.getChildren().addAll(username, password);
        formContainer.setSpacing(15);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(action -> {
            String formUsername = username.getText();
            String formPassword = password.getText();

            User user = UserController.loginUser(formUsername, formPassword);

            if (user != null) {
                menuPage();
            }
            // loginUser(formUsername,formPassword);
        });
        Button registerButton = new Button("register");
        registerButton.setOnAction(action -> {
            // TODO register user to database
            String formUsername = username.getText();
            String formPassword = password.getText();
            UserController.registerUser(formUsername, formPassword);
            username.setText("");
            password.setText("");
        });

        Button adminLoginButton = new Button("Login Admin");
        adminLoginButton.setOnAction(action -> {
            // TODO make function that check if user is success to login
            // TODO redirect to managementPage() if success login;

        });

        HBox notLoginContainer = new HBox();
        notLoginContainer.getChildren().addAll(adminLoginButton, registerButton);
        notLoginContainer.setAlignment(Pos.BASELINE_CENTER);
        notLoginContainer.setSpacing(10);
        // StackPane stackPane = new StackPane();
        // stackPane.getChildren().add(brandLogo);

        VBox layout = new VBox();
        layout.getChildren().addAll(loginLabel, brandContainer, formContainer, loginButton, notLoginContainer);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(20);

        Scene scene = new Scene(layout, width, height);

        mainStage.setScene(scene);
    }

    // private void loginUser(String formUsername, String formPassword) {
    // }

    public void menuPage() {
        final int itemLogoWidth = 25;
        final int itemLogoHeight = 25;

        Label menuLabel = new Label("Menu");

        FlowPane menuContainer = new FlowPane();
        menuContainer.setHgap(20);
        menuContainer.setVgap(10);
        menuContainer.setAlignment(Pos.BASELINE_CENTER);

        ArrayList<Menu> listMenu = MenuController.getAllData();
        System.out.println(listMenu);
        Button[] data = new Button[listMenu.size()];
        int i = 0;
        for (Menu menu : listMenu) {
            VBox buttonView = new VBox();
            Label menuNama = new Label(menu.menuNama);
            Image gambar = new Image(getClass().getResource("/images/mcdonalds.png").toString(),
                    itemLogoWidth, itemLogoHeight, false, false);

            ImageView menuGambar = new ImageView(gambar);

            buttonView.getChildren().addAll(menuGambar, menuNama);
            buttonView.setAlignment(Pos.BASELINE_CENTER);

            data[i] = new Button();
            data[i].setGraphic(buttonView);
            menuContainer.getChildren().add(data[i]);
        }

        VBox vbox = new VBox();
        vbox.getChildren().addAll(menuLabel, menuContainer);
        vbox.setAlignment(Pos.BASELINE_CENTER);

        AnchorPane anchorPane = new AnchorPane();

        // Create buttons and set their positions using anchors
        Button orderButton = new Button("Button 1");
        AnchorPane.setBottomAnchor(orderButton, 10.0);
        AnchorPane.setLeftAnchor(orderButton, 10.0);
        AnchorPane.setRightAnchor(orderButton, 10.0);
        orderButton.setOnAction(action -> {
            // if(listOrder.size() > 0){
            //     cartPage();
            // }
            cartPage();
        });

        // Add buttons to the AnchorPane
        anchorPane.getChildren().addAll(orderButton);

        BorderPane mainPane = new BorderPane();
        // mainPane.setTop(menuLabel);
        // mainPane.setCenter(menuContainer);
        mainPane.setTop(vbox);
        mainPane.setBottom(anchorPane);

        Scene scene = new Scene(mainPane, width, height);

        mainStage.setScene(scene);
    }

    public Object getGreeting() {
        return null;
    }

    public void cartPage() {
        // TODO still need datamodel and some adjustment to table

        Label cartLabel = new Label("Cart");

        TableView<Menu> cartTable = new TableView<>();

        TableColumn<Menu, String> menuNameCol = new TableColumn<>("Name");
        menuNameCol.setCellValueFactory(new PropertyValueFactory<>("menuNama"));

        TableColumn<Menu, Integer> menuQuantityCol = new TableColumn<>("Amount");
        menuQuantityCol.setCellValueFactory(new PropertyValueFactory<>("menuHarga"));

        TableColumn<Menu, Integer> menuPriceCol = new TableColumn<>("Price");
        menuPriceCol.setCellValueFactory(new PropertyValueFactory<>("menuHarga"));

        // ? optional
        // TableColumn<String, String> menuTotalCol =new TableColumn<>("Total");
        // menuTotalCol.setCellValueFactory(new PropertyValueFactory<>("Total"));

        // * how to get totalPrice
        // double totalPrice = 0.0;
        // for (Person person : tableView.getItems()) {
        // Double price = priceColumn.getCellObservableValue(person).getValue();
        // if (price != null) {
        // totalPrice += price;
        // }
        // }

        // System.out.println("Total Price: " + totalPrice);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(cartLabel, cartTable);
        vbox.setAlignment(Pos.TOP_CENTER);

        AnchorPane anchorPane = new AnchorPane();

        // Create buttons and set their positions using anchors
        Button menuPageButton = new Button("Menu List");
        menuPageButton.prefWidth((width / 2) - 10.0);
        AnchorPane.setBottomAnchor(menuPageButton, 10.0);
        AnchorPane.setLeftAnchor(menuPageButton, 10.0);
        AnchorPane.setRightAnchor(menuPageButton, (double) width / 2 + 5);
        menuPageButton.setOnAction(action -> {
            menuPage();
        });

        Button orderButton = new Button("Order");
        AnchorPane.setBottomAnchor(orderButton, 10.0);
        AnchorPane.setLeftAnchor(orderButton, (double) width / 2 + 5);
        AnchorPane.setRightAnchor(orderButton, 10.0);

        //
        orderButton.prefWidthProperty().bind(anchorPane.widthProperty().divide(2));

        // Add buttons to the AnchorPane
        anchorPane.getChildren().addAll(menuPageButton, orderButton);

        BorderPane mainPane = new BorderPane();
        // mainPane.setTop(menuLabel);
        // mainPane.setCenter(menuContainer);
        mainPane.setTop(vbox);
        mainPane.setBottom(anchorPane);

        Scene scene = new Scene(mainPane, width, height);

        mainStage.setScene(scene);
    }

    // public void start() {
    // // Create an AnchorPane
    // AnchorPane anchorPane = new AnchorPane();

    // // Create a Button
    // Button button = new Button("Click me");
    // Button button1 = new Button("Click me");

    // // Add the Button to the AnchorPane
    // anchorPane.getChildren().addAll(button,button1);

    // // Set the anchors to center the Button
    // AnchorPane.setBottomAnchor(button, 10.0);
    // AnchorPane.setLeftAnchor(button, 10.0);
    // AnchorPane.setRightAnchor(button, 205.0);
    // AnchorPane.setBottomAnchor(button1, 10.0);
    // AnchorPane.setLeftAnchor(button1, 205.0);
    // AnchorPane.setRightAnchor(button1, 10.0);

    // // System.out.println(an);

    // // Create a Scene with the AnchorPane
    // Scene scene = new Scene(anchorPane, 400, 300);

    // // Set the Scene to the primary stage
    // mainStage.setScene(scene);
    // mainStage.show();
}

// // public static void main(String[] args) {
// // launch(args);
// // }
