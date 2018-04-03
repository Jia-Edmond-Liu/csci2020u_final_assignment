package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SongQueue {
    protected static ObservableList<File> queuedSongs;
    ObservableList<String> songNameList;


    public SongQueue(File dir[]){
        queuedSongs = FXCollections.observableArrayList(dir);
        Collections.shuffle(queuedSongs);
        String filenames[] = new String[dir.length];
        for (int i =0;i<dir.length;i++){
            filenames[i] = dir[i].getName();
        }
        songNameList = FXCollections.observableArrayList(filenames);
    }

    public ObservableList<String> getSongNameList() {
        return songNameList;
    }

    public void addSong(Song song){
        queuedSongs.add(song.getTrack());
    }

    public void removeSong(Song song){
        int index = queuedSongs.indexOf(song.getTrack());
        queuedSongs.remove(index);
    }
}
