package lk.uok;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
//
//public class AppInitializer extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("view/DashBoardForm.fxml"));
//        primaryStage.setTitle("Thoga Kade");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.
                load(this.getClass().
                        getResource("view/DashBoardForm.fxml"))));
        primaryStage.show();
    }
}
