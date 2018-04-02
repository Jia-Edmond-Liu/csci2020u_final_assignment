package sample;
import java.util.ArrayList;

public class SongQueue {
    protected static ArrayList<String> queuedSongs = new ArrayList<String>();

    public SongQueue(){
        //not sure if constructor is necessary at the moment
        //nothing to initialize really
    }

    public String getQueuedSongs(){
        String output = "";
        for(int i = 0; i < queuedSongs.size();i++){
            if(i < queuedSongs.size() - 1){
                output = queuedSongs.get(i) + ";";
            }
            else{
                output = queuedSongs.get(i);
            }
        }
        return output;
    }

    public void addSong(Song song){
        queuedSongs.add(song.getSongName());
    }

    public void removeSong(Song song){
        int index = queuedSongs.indexOf(song.getSongName());
        queuedSongs.remove(index);
    }
}
