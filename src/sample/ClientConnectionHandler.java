package sample;

import java.io.*;
import java.net.Socket;

public class ClientConnectionHandler implements Runnable {
    public static String ROOT = "[INSERT ROOT HERE]/";
    private Socket socket;
    private PrintWriter socket_out;

    public ClientConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream inStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = socket.getOutputStream();
            socket_out = new PrintWriter(outStream);
            String request = null;
            while(request == null){
                request = in.readLine();
            }
            String[] requestParts = request.split(" ");
            String command = requestParts[0];
            /*
            Insert call commands here
            Based off A2 ClientHandler
             */
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}