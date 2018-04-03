package sample;

import javafx.collections.ObservableList;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Client {
    private String displayName;
    private Song lastPlayed;
    private static File userData = new File("Server/Data/userdata.txt");
    private HashMap<String, String> userMap = Main.getUserMap();
    private SongQueue songQueue;

    public Client(String displayName){
        this.displayName = displayName;
        File[] dir = new File("Server/Music").listFiles();
        songQueue=new SongQueue(dir);
    }

    public void setLastPlayed(Song lastPlayed) throws IOException {
        this.lastPlayed = lastPlayed;
        userMap.put(displayName, lastPlayed.getSongName());

        PrintWriter writer = new PrintWriter("Userdata.txt");
        writer.write(String.valueOf(userMap));

        ClientConnectionHandler cch = new ClientConnectionHandler(new Socket(Main.getHost(), Main.getPort()));
        cch.cmdUPLOAD_TEXT("Userdata.txt");
    }

    public ObservableList<String> getSongNameList() {
        return songQueue.getSongNameList();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
