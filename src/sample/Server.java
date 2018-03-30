package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends  Thread{
    private ServerSocket serverSocket;

    public Server() throws IOException {
       this.serverSocket = new ServerSocket(Main.getPort());
    }

    @Override
    public void run() {
        super.run();
        while (true){
            try {
                Socket socket = new Socket(Main.getHost(), Main.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
