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
            socket.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cmdDIR(){
        String out = "";
        File baseDir = new File(ROOT);
        File fileList[] = baseDir.listFiles();
        for (int i = 0; i<fileList.length; i++){
            out += fileList[i].getName();
            if (i != fileList.length - 1){
                out += " ";
            }
        }
        socket_out.print(out);
        socket_out.flush();
    }



    private void cmdUPLOAD(String fileName) throws IOException{
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String response;
            File tempFile = new File("[INSERT LOCATION TO UPLOAD HERE", fileName);
            if (!tempFile.exists()){
                tempFile.createNewFile();
            }
            else{
                tempFile.delete();
                tempFile.createNewFile();
            }
            System.out.print(tempFile.getParent());
            PrintWriter socket_out = new PrintWriter(tempFile);
            while((response = in.readLine()) != null){
                socket_out.println(response);
            }
            socket_out.close();
            out.close();
            in.close();
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    private void cmdDownload(String fileName) throws IOException{
        String out = "", line = "";
        File file = new File(ROOT, fileName);
        BufferedReader in = new BufferedReader(new FileReader(file));
        while((line = in.readLine()) != null){
            out += line + "\n";
        }
        socket_out.print(out);
        socket_out.flush();
    }

}