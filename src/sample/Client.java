package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Client {
    private String displayName;
    private Song lastPlayed;
    private static File userData = new File("Server/userData");
    private HashMap<String, String> userMap = Main.getUserMap();

    public Client(String displayName){
        this.displayName = displayName;

    }

    public void setLastPlayed(Song lastPlayed) throws FileNotFoundException {
        this.lastPlayed = lastPlayed;
        userMap.put(displayName, lastPlayed.getSongName());

        PrintWriter writer = new PrintWriter(userData);
        writer.println(userMap);
        writer.flush();
        writer.close();
    }
}
