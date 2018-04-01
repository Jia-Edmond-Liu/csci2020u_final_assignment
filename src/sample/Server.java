package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server extends Thread{
    private ServerSocket serverSocket;
    private ObservableList<Thread> threads = FXCollections.observableArrayList();

    public Server() throws IOException {
       this.serverSocket = new ServerSocket(Main.getPort());
    }

    @Override
    public void run() {
        try{
            this.handle();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void quit(){
        try{
            serverSocket.close();
        }
        catch(IOException e){
            System.out.println("Closed Socket");
        }
    }

    public void handle() throws IOException{
        try{
            int clientNum = 0;
            Vector<Thread> thread = new Vector<Thread>();
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                thread.add(clientNum, new Thread(new ClientConnectionHandler(socket)));
                thread.get(clientNum).start();
                clientNum++;
                }
            }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
