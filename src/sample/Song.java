package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Pattern;

public class Song {
    private String songName;
    private MediaPlayer song;
    private String artist;
    private File track;
    private static Image albumArt;
    File art= new File("assets/defaultCover.png");
    private static boolean closed = false;

    public Song(File file){
        albumArt = new Image(art.toURI().toString());

       /* to set the album art initially but messed with default cover
        String fileName = file.getName().substring(0,file.getName().indexOf("."));
        try {
                    art = new File("assets/" + fileName + ".png");
                    System.out.println(art.getPath());
                }
             catch(Exception e){
                        System.out.println("File doesnt exist.");
                        e.printStackTrace();
        }
        */
        this.track = file;
         String temp[] = file.getName().split(Pattern.quote("."));
         this.songName = temp[0];
         this.artist = "Unknown Artist";
        this.song = new MediaPlayer(new Media(file.toURI().toString()));
        song.setVolume(0.5);
        //song.play();
    }

    public MediaPlayer getSong(){
        return song;
    }

    public void editDetails(){
        Stage edit = new Stage();
        edit.setTitle("Change Song Details");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        closed=false;
        TextField songField = new TextField();
        gridPane.setMargin(songField, new Insets(10));
        if (songName.isEmpty()) {
            songField.setPromptText("Artist");
        } else {
            songField.setPromptText(artist);
        }

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
                fileChooser.setInitialDirectory(new File("."));
                art = fileChooser.showOpenDialog(edit);
            }
        });

        Button submit = new Button("Save");
        gridPane.add(submit,0,4);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("???");
                songName = songField.getText();
                artist = artistField.getText();
                System.out.println(art.toURI().toString());
                setAlbumArt(new Image(art.toURI().toString()));
                setClosed(true);
                edit.close();
            }
        });

        edit.setScene(new Scene(gridPane));
        edit.show();
    }

    public String getSongName() {
        return songName;
    }

    public Image getAlbumArt(){
        return albumArt;
    }

    public void setAlbumArt(Image albumArt) {
        this.albumArt = albumArt;
    }

    public String getArtist() {
        return artist;
    }

    public File getTrack() {
        return track;
    }

    public void uploadTrack() throws IOException {
        Socket socket = new Socket(Main.getHost(), Main.getPort());
        ClientConnectionHandler cch = new ClientConnectionHandler(socket);
        //Debug line
        System.out.println("Sending cmdDownload_Media");
        cch.cmdDOWNLOAD_MEDIA(songName);
    }

    public static boolean getClosed() {
        return closed;
    }

    public void setClosed(boolean status){
        closed = status;

    }
}
