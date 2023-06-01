package mdbudget.scenes.client;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mdbudget.interfaces.Showable;
import mdbudget.scenes.BaseScene;

public class OrderPage extends BaseScene implements Showable{

    public OrderPage(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Scene scene = new Scene(new Group(), stage.getWidth(),stage.getHeight());
        stage.setScene(scene);
    }
    
}
