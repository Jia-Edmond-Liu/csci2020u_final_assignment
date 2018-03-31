package sample;

        import javafx.fxml.FXML;
        import javafx.scene.control.ListView;
        import javafx.scene.control.Slider;
        import javafx.scene.image.ImageView;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;

        import java.io.File;

public class Controller {
    Stage primaryStage = Main.getStage();

    @FXML ImageView albumCover;
    @FXML ListView friendList;
    @FXML Slider volume;
    @FXML Slider songProg;

    public void initialize(){

    }

    @FXML public void saveSong(){

    }

    @FXML public void uploadSong(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio","*.mp3"));
        File track = fileChooser.showOpenDialog(primaryStage);

        Song song = new Song(track);
        song.uploadTrack();
    }

    @FXML public void setArtist(){

    }
    @FXML public void setAlbum(){

    }
    @FXML public void setArt(){

    }
    @FXML public void setName(){

    }



    @FXML public void exit(){

    }

    @FXML public void share(){

    }

    @FXML public void goBack(){

    }
    @FXML public void playPause(){

    }
    @FXML public void goForward(){

    }
}
