package mdbudget.scenes.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mdbudget.controllers.UserController;
import mdbudget.interfaces.Showable;
import mdbudget.scenes.BaseScene;
import mdbudget.utils.ImageGenerator;

public class LandingPage extends BaseScene implements Showable {

    final int logoWidth = 150;
    final int logoHeight = 150;

    public LandingPage(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Label loginLabel = new Label("Login");
        loginLabel.setStyle("fx-font-size: 20px;-fx-font-weight: bold;-fx-aligment: center");

        ImageView brandLogo = new ImageView(ImageGenerator.generate("mcbudg.png", logoWidth, logoHeight));

        StackPane brandContainer = new StackPane(brandLogo);

        VBox usernameField = new VBox();
        Label usernameLabel =  new Label("username : ");
        TextField username = new TextField();
        username.setStyle("-fx-max-width: 300px; -fx-max-height: 50px;");
        usernameField.getChildren().addAll(usernameLabel,username);
        
        VBox passwordField = new VBox();
        Label passwordLabel =  new Label("password : ");
        PasswordField password = new PasswordField();
        password.setStyle("-fx-max-width: 300px; -fx-max-height: 50px;");
        passwordField.getChildren().addAll(passwordLabel,password);

        // password.setStyle("-fx-pref-width: 200px; -fx-pref-height: 30px;");

        VBox formContainer = new VBox();
        formContainer.getChildren().addAll(usernameField,passwordField);
        formContainer.setAlignment(Pos.TOP_CENTER);
        formContainer.setSpacing(15);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(action -> {
            String formUsername = username.getText();
            String formPassword = password.getText();

            int user = UserController.loginUser(formUsername, formPassword);

            if (user != 0) {
                MenuPage menuPageScene = new MenuPage(stage);
                menuPageScene.show();
            }

            // loginUser(formUsername,formPassword);
        });

        Button registerButton = new Button("Login");
        registerButton.setOnAction(action -> {
            String formUsername = username.getText();
            String formPassword = password.getText();

            int user = UserController.loginUser(formUsername, formPassword);

            if (user != 0) {
                MenuPage menuPageScene = new MenuPage(stage);
                menuPageScene.show();
            }

            // loginUser(formUsername,formPassword);
        });

        Button adminLoginButton = new Button("Login Admin");
        adminLoginButton.setOnAction(action -> {
            // TODO make function that check if user is success to login
            // TODO redirect to managementPage() if success login;

        });

        HBox notLoginContainer = new HBox();
        notLoginContainer.getChildren().addAll(adminLoginButton);
        notLoginContainer.setAlignment(Pos.BASELINE_CENTER);
        notLoginContainer.setSpacing(10);
        // StackPane stackPane = new StackPane();
        // stackPane.getChildren().add(brandLogo);

        VBox layout = new VBox();
        layout.getChildren().addAll(loginLabel, brandContainer, formContainer, loginButton, notLoginContainer);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(20);

        Scene scene = new Scene(layout, stage.getWidth(), stage.getHeight());

        stage.setScene(scene);
    }

}
