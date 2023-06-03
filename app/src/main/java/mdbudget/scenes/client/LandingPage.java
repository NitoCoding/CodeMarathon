package mdbudget.scenes.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mdbudget.controllers.UserController;
import mdbudget.interfaces.Showable;
import mdbudget.scenes.BaseScene;
import mdbudget.scenes.admin.ManagementPage;
import mdbudget.utils.ImageGenerator;

public class LandingPage extends BaseScene implements Showable {

    private final int logoWidth = 150;
    private final int logoHeight = 150;
    private int userId;

    public LandingPage(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        GridPane headerContainer = new GridPane();

        Label loginLabel = new Label("Login");
        loginLabel.setStyle(
            "-fx-font-family: 'Jacques Francois';" +
            "-fx-font-size: 40px;" +
            "-fx-font-weight: 400;" +
            "-fx-line-height: 53px;" +
            "-fx-letter-spacing: 0em;" +
            "-fx-text-alignment: center;" +
            "-fx-text-fill: #385748;"
            // "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 0px, 4px, 4px);"
        );

        headerContainer.add(loginLabel,0,0);
        headerContainer.setAlignment(Pos.TOP_CENTER);

        GridPane logoContainer = new GridPane();

        ImageView brandLogo = new ImageView(ImageGenerator.generate("burger.png", logoWidth, logoHeight));

        logoContainer.add(brandLogo,0,0);
        // StackPane brandContainer = new StackPane(brandLogo);
        logoContainer.setAlignment(Pos.CENTER);
        //

        GridPane formContainer = new GridPane();
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setHgap(10);
        formContainer.setVgap(12);

        Label usernameLabel = new Label("Username :");
        TextField username = new TextField();
        // username.setMaxWidth(250);
        username.setMinWidth(250);
        username.setMinHeight(30);;

        Label passwordLabel = new Label("Password :");
        PasswordField password = new PasswordField();
        password.setMinWidth(250);
        password.setMinHeight(30);

        formContainer.add(usernameLabel, 0, 0);
        formContainer.add(username, 0, 1);
        formContainer.add(passwordLabel, 0, 2);
        formContainer.add(password, 0, 3);

        GridPane buttonContainer = new GridPane();

        Button loginButton = new Button("Login");
        loginButton.setStyle(
            "-fx-background-color: #58585B;" +
            "-fx-border-color: none;" +
            "-fx-background-radius: 10;"+
            "-fx-font-family: 'Jaldi';" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: 400;" +
            "-fx-line-height: 34px;" +
            "-fx-text-fill: white;"
        );
        loginButton.setMinWidth(115);
        loginButton.setMinHeight(45);
        loginButton.setOnAction(action -> {
            String formUsername = username.getText();
            String formPassword = password.getText();
            System.out.println(formUsername + " " + formPassword);

            userId = UserController.loginUser(formUsername, formPassword);
            System.out.println(userId);

            if (userId != 0) {
                MenuPage menuPageScene = new MenuPage(stage, userId);
                menuPageScene.show();
            }

            // loginUser(formUsername,formPassword);
        });

        Button registerButton = new Button("Register");
        registerButton.setStyle(
            "-fx-background-color: #58585B;" +
            "-fx-border-color: none;" +
            "-fx-background-radius: 10;"+
            "-fx-font-family: 'Jaldi';" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: 400;" +
            "-fx-line-height: 34px;" +
            "-fx-text-fill: white;"
        );
        registerButton.setMinWidth(115);
        registerButton.setMinHeight(45);
        registerButton.setOnAction(action -> {
            String formUsername = username.getText();
            String formPassword = password.getText();

            int user = UserController.registerUser(formUsername, formPassword);

            if (user != 0) {
                
            }

            // loginUser(formUsername,formPassword);
        });

        Button adminLoginButton = new Button("Login Admin");
        adminLoginButton.setStyle(
            "-fx-background-color: #58585B;" +
            "-fx-border-color: none;" +
            "-fx-background-radius: 10;"+
            "-fx-font-family: 'Jaldi';" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: 400;" +
            "-fx-line-height: 34px;" +
            "-fx-text-fill: white;"
        );
        adminLoginButton.setMinWidth(250);
        adminLoginButton.setMinHeight(45);
        adminLoginButton.setOnAction(action -> {
            String formUsername = username.getText();
            String formPassword = password.getText();

            int user = UserController.loginUserAdmin(formUsername, formPassword);

            if (user != 0) {
                ManagementPage managementPageScene = new ManagementPage(stage);
                managementPageScene.show();
            }
        });

        buttonContainer.add(loginButton, 0, 0);
        buttonContainer.add(registerButton, 1, 0);
        buttonContainer.add(adminLoginButton, 0, 1, 2, 1);

        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setHgap(20);
        buttonContainer.setVgap(10);

        GridPane layout = new GridPane();
        layout.add(headerContainer,0,0);
        layout.add(logoContainer,0,1);
        layout.add(formContainer,0,2);
        layout.add(buttonContainer,0,3);
        // layout.getChildren().addAll(loginLabel, brandContainer, formContainer, buttonContainer);
        // layout.setAlignment(Pos.TOP_CENTER);
        // layout.setSpacing(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setVgap(20);

        Scene scene = new Scene(layout, stage.getWidth(), stage.getHeight());

        stage.setScene(scene);
    }

}
