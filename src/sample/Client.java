package sample;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class Client {
    private String displayName;
    private Song lastPlayed;
    private static File userData = new File("Server/Data/userdata.txt");
    private HashMap<String, String> userMap = Main.getUserMap();

    public Client(String displayName){
        this.displayName = displayName;

    }

    public void setLastPlayed(Song lastPlayed) throws IOException {
        this.lastPlayed = lastPlayed;
        userMap.put(displayName, lastPlayed.getSongName());

        PrintWriter writer = new PrintWriter("Userdata.txt");

        ClientConnectionHandler cch = new ClientConnectionHandler(new Socket(Main.getHost(), Main.getPort()));
        cch.cmdUPLOAD_TEXT("Userdata.txt");
    }

    public String getDisplayName() {
        return displayName;
    }
}
