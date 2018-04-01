package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private String displayName;
    private Song lastPlayed;
    File userData;

    public Client(String displayName){
        this.displayName = displayName;
        File users[] = new File("Server/Users").listFiles();
        for (File user : users){
            if (user.getName().equals(displayName)){
                userData = user;
            } else {
                userData = new File(displayName);
            }
        }
    }

    public void setLastPlayed(Song lastPlayed) throws FileNotFoundException {
        this.lastPlayed = lastPlayed;
        PrintWriter writer = new PrintWriter(userData);
        writer.println(lastPlayed.getSongName());
    }
}
