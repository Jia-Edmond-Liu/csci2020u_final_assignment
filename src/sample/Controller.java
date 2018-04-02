package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller implements Runnable{
    Stage primaryStage = Main.getStage();
    Song song = new Song(new File("test.mp3")); //temp file to begin
    private static Client client = Main.getClient();

    boolean playing = false; //to test whether the button plays or pauses audio
    @FXML ImageView albumCover; //might remove altogether
    @FXML Label artistLabel;
    @FXML Label songLabel;
    @FXML ListView friendList;
    @FXML ListView queue;
    @FXML Slider volume;
    @FXML Slider songProg;
    @FXML Button playButton;

    public void initialize(){
        refreshDetails();
        //if (song.getStatus())
        songProg.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (songProg.isValueChanging()){
                    song.getSong().seek(new Duration(songProg.getValue()/100.0));
                }
            }
        });

        volume.setValue(song.getSong().getVolume());
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                song.getSong().setVolume(volume.getValue());
            }
        });
    }

    public void refreshDetails(){
        artistLabel.setText(song.getArtist());
        songLabel.setText(song.getSongName());
        albumCover.setImage(song.getAlbumArt());
    }

    //idea is to have a listener for the song's progress

    //upload a song to the server
    @FXML public void uploadSong() throws IOException {
        //choose a song to upload
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio","*.mp3"));
        File track = fileChooser.showOpenDialog(primaryStage);

        //set the global song object
        song = new Song(track);
        songLabel.setText(song.getSongName());
        song.uploadTrack();
        run();
    }

    @FXML public void editDetails(){
        song.editDetails();
        refreshDetails();
    }

    //TO DO doesnt work
    @FXML public void exit() throws IOException {
        Client t = new Client("temp");
        t.setLastPlayed(song);
        System.out.println(client.getDisplayName());
        primaryStage.close();
    }

    @FXML public void goBack(){
        if (song.getSong().getCurrentTime().toMillis()>100) {
            song.getSong().seek(new Duration(0));
        }
    }

    @FXML public void playPause(){
        if (playing) {
            song.getSong().pause();
            playing = false;
            playButton.setText("Play");
        } else {
            song.getSong().play();
            playing= true;
            playButton.setText("Pause");
        }
    }

    @FXML public void changeUser(){
        Main.showSplashScreen();
        Main.getStage().close();
    }

    @FXML public void addFriend(){

    }

    @FXML public void goForward(){

    }

    @Override
    public void run() {

    }
}
