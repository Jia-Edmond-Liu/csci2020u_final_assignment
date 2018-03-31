package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller implements Runnable{
    Stage primaryStage = Main.getStage();
    Song song = new Song();

    @FXML ImageView albumCover;
    @FXML Label artistLabel;
    @FXML Label songLabel;
    @FXML ListView friendList;
    @FXML Slider volume;
    @FXML Slider songProg;
    @FXML Button playButton;

    public void initialize(){

    }

    @Override
    public void run() {
        double timeLeft =  song.getSong().getTotalDuration().toMillis() - song.getSong().getCurrentTime().toMillis();
        while (song.getSong().getCurrentTime().toMillis()>0) {
            System.out.println(song.getSong().getCurrentTime().toMillis());
            songProg.setValue(song.getSong().getCurrentTime().toMillis());
        }
    }

    @FXML public void saveSong(){

    }

    //upload a song to the server
    @FXML public void uploadSong(){
        //choose a song to upload
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio","*.mp3"));
        File track = fileChooser.showOpenDialog(primaryStage);

        //set the global song object
        song = new Song(track);
        song.uploadTrack();
        run();
    }

    @FXML public void editDetails(){
        song.editDetails();
        songLabel.setText(song.getSongName());
        artistLabel.setText(song.getArtist());
        albumCover.setImage(song.getAlbumCover());
    }

    @FXML public void exit(){

    }

    @FXML public void share(){

    }

    @FXML public void goBack(){

    }
    @FXML public void playPause(){
        boolean playing = false;
        if (playing) {
            song.getSong().play();
            playing = true;
            playButton.setText("Pause");
        } else {
            song.getSong().pause();
            playing= false;
            playButton.setText("Play");
        }
    }
    @FXML public void goForward(){

    }

}
