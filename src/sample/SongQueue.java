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
        //Controller.setQueue(songNameList); //null????
        //not sure if constructor is necessary at the moment
        //nothing to initialize really
    }

    public ObservableList<String> getSongNameList() {
        return songNameList;
    }

    public String getQueuedSongs(){
        String output = "";
        for(int i = 0; i < queuedSongs.size();i++){
            if(i < queuedSongs.size() - 1){
                output += queuedSongs.get(i) + ";";
            }
            else{
                output += queuedSongs.get(i);
            }
        }
        return output;
    }

    public void addSong(Song song){
        queuedSongs.add(song.getTrack());
    }

    public void removeSong(Song song){
        int index = queuedSongs.indexOf(song.getTrack());
        queuedSongs.remove(index);
    }
}
