package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.text.html.ListView;
import java.io.InputStream;
import java.util.Observable;


public class Controller {
    @FXML
    ImageView albumCover;
    @FXML
    ListView listView;
    @FXML
    Slider slider;


    ObservableList<String> musicFolder = FXCollections.observableArrayList();

    public void initialize(){

    }

    @FXML public void saveSong(){

    }

    @FXML public void exit(){

    }
}
