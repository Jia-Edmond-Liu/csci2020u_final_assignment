package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    static Stage primaryStage;
    private static int port = 1209;
    private static String hostName = "127.0.0.1";

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = (Parent)loader.load();
        root.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        Controller controller = (Controller)loader.getController();

        primaryStage.setTitle("Fresh Music Stream");
        this.primaryStage = primaryStage;

        primaryStage.setScene(new Scene(root));

        //Server server = new Server();
        //server.start();

        primaryStage.show();
    }

    public static int getPort(){
        return port;
    }

    public static String getHost(){
        return hostName;
    }

    public static Stage getStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
