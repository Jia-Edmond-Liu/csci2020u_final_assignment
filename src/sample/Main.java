package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;

public class Main extends Application {
    static Stage primaryStage;
    private static int port = 1209;
    private static String hostName = "127.0.0.1";
    private static Client client;
    private static HashMap<String, String> users = new HashMap<>();

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

        // LOGIN SCREEN MAYBE SPLASH SCREEN TEMP TEMP
        File art = new File("assets/black.jpg");
        ImageView imgView = new ImageView(new Image(art.toURI().toString()));
        imgView.setFitHeight(250);
        Button submit = new Button("Begin");
        Label display = new Label("Display Name:");
        TextField textField = new TextField();
        textField.setPromptText("Enter Display Name");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(50));
        gridPane.add(imgView,0,0,3,1);
        gridPane.add(display,0,1);
        gridPane.add(textField, 1, 1);
        gridPane.add(submit,0,2, 3, 2);
        submit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Stage splash = new Stage();
        splash.setTitle("Set Name");
        splash.setScene(new Scene(gridPane,500,450));
        splash.show();
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                splash.close();
                client = new Client(textField.getText());
                primaryStage.show();
            }
        });
    }

    public static HashMap<String, String> getUserMap(){
        return users;
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

    public static Client getClient() {
        return client;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
