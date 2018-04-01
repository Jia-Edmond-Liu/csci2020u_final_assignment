package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;
import java.util.regex.Pattern;

public class Song {
    private String songName;
    private MediaPlayer song;
    private String artist = "";
    private String album = "";
    private File track;
    private Image albumCover;

    public Song(File file){
        this.track = file;
         String temp[] = file.getName().split(Pattern.quote("."));
         this.songName = temp[0];
        this.song = new MediaPlayer(new Media(file.toURI().toString()));
        song.setVolume(0.5);
        song.play();
    }
    public Song(){

    }

    public MediaPlayer getSong(){
        return song;
    }

    public void editDetails(){
        Stage edit = new Stage();
        edit.setTitle("Change Song Details");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));

        TextField songField = new TextField();
        gridPane.setMargin(songField, new Insets(10));
        songField.setPromptText(songName);
        gridPane.add(songField,0,0);

        TextField artistField = new TextField();
        gridPane.setMargin(artistField, new Insets(10));
        if (artist.isEmpty()) {
            artistField.setPromptText("Artist");
        } else {
            artistField.setPromptText(artist);
        }
        gridPane.add(artistField,0,1);


        TextField albumField = new TextField();
        gridPane.setMargin(albumField, new Insets(10));
        if (album.isEmpty()) {
            albumField.setPromptText("Album");
        } else {
            albumField.setPromptText(album);
        }
        gridPane.add(albumField,0,2);

        Label cover=  new Label("Album Art");
        gridPane.setMargin(cover, new Insets(10));
        Button uploadPic = new Button("Select File");
        gridPane.setMargin(uploadPic, new Insets(10));
        gridPane.add(cover,0,3);
        gridPane.add(uploadPic, 1, 3);
        gridPane.setHgap(5);
        gridPane.setVgap(10);
        uploadPic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Pictures", ".png", ".jpeg",".jpg"));
                File art = fileChooser.showOpenDialog(edit);
                Image albumArt = new Image(art.toURI().toString());
            }
        });

        Button submit = new Button("Save");
        gridPane.add(submit,0,4);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                songName = songField.getText();
                artist = artistField.getText();
                album = albumField.getText();
                edit.close();
            }
        });

        edit.setScene(new Scene(gridPane));
        edit.show();
    }

    public String getAlbum() {
        return album;
    }

    public String getSongName() {
        return songName;
    }

    public Image getAlbumCover(){
        return albumCover;
    }

    public String getArtist() {
        return artist;
    }

    public void uploadTrack(){

    }

}
