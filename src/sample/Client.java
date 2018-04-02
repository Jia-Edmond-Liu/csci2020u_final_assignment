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

    public void setLastPlayed(Song lastPlayed) throws FileNotFoundException {
        this.lastPlayed = lastPlayed;
        userMap.put(displayName, lastPlayed.getSongName());

        Socket socket = null;
        try {
            socket = new Socket(Main.getHost(), Main.getPort());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            if (userData.exists()){
                userData.delete();
            }
            userData.createNewFile();

            String line;
            PrintWriter socket_out = new PrintWriter(userData);
            writer.println(userMap);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
