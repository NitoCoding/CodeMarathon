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

    final int logoWidth = 200;
    final int logoHeight = 150;

    public LandingPage(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Label loginLabel = new Label("Login");

        ImageView brandLogo = new ImageView(ImageGenerator.generate("burger.png", logoWidth, logoHeight));

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
