package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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

        /* LOGIN SCREEN MAYBE SPLASH SCREEN TEMP TEMP
        File art = new File("assets/black.jpg");
        ImageView imgView = new ImageView(new Image(art.toURI().toString()));
        BorderPane t = new BorderPane(imgView);
        Stage splash = new Stage();
        splash.setScene(new Scene(t,666,527));
        splash.show();
        Thread.sleep(2500);
        splash.close();
        */
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
