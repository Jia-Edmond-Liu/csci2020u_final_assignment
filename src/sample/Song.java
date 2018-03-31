package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Song {
    private String songName;
    private MediaPlayer song;
    private String artist;
    private String album;
    private File track;

    public Song(File file){
        this.track = file;
        this.songName = file.getName();
        this.song = new MediaPlayer(new Media(file.getPath()));
    }

    public void uploadTrack(){

    }

}
