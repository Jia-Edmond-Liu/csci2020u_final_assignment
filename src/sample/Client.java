package sample;

import java.net.Socket;

public class Client {
    private String displayName;
    private Socket socket;

    public Client(Socket socket){
        this.socket = socket;
    }

}
