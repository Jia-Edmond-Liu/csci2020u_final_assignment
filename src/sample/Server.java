package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends  Thread{
    private ServerSocket serverSocket;
    private ObservableList<Thread> threads = FXCollections.observableArrayList();

    public Server() throws IOException {
       this.serverSocket = new ServerSocket(Main.getPort());
    }

    @Override
    public void run() {
        super.run();
        while (true){
            try {
                Socket socket = serverSocket.accept();

                //Thread client = new Thread((Runnable) new Client(socket));
                //threads.add(client);
                //client.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
