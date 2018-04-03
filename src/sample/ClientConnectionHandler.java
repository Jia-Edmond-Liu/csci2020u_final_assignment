package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnectionHandler implements Runnable {
    public static String ROOT = "Server";
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

    private void cmdUPLOAD_MEDIA(String fileName) throws IOException{
        File file = new File("Client",fileName);
        String extension=  fileName.substring(fileName.lastIndexOf(".") + 1);
        try {
            OutputStream out = socket.getOutputStream();
            DataOutputStream dout = new DataOutputStream(out);
            dout.writeUTF(extension);
            out.flush();

            FileInputStream in = new FileInputStream(file);
            BufferedInputStream bin = new BufferedInputStream(in);
            byte[] bytes = new byte[16*1024];
            bin.read(bytes,0,bytes.length);
            out.write(bytes,0,bytes.length);
            out.close();
            dout.close();
            in.close();

        }
        catch(IOException e){
            System.out.println("IO Error");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



    //THESE FUNCTIONS ARE USED FOR SENDING TEXT FILES THROUGH CHAT
    public void cmdUPLOAD_TEXT(String fileName) throws IOException{
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String response;
            File tempFile = new File(ROOT, fileName);

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

    public void cmdDOWNLOAD_MEDIA(String fileName) throws IOException{
        int count;
        try{
            //Socket socket = new Socket(Main.getHost(), Main.getPort());
            InputStream in = socket.getInputStream();
            byte[] bytes = new byte[16*1024];
            DataInputStream din = new DataInputStream(socket.getInputStream());
            String extension = din.readUTF();
            String path =  ROOT + "/Music/" + fileName + "." + extension;
            File file = new File(path);
            OutputStream out = new FileOutputStream(file);
            while((count = in.read(bytes))>0){
                out.write(bytes,0,count);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    private void cmdDownload_TEXT(String fileName) throws IOException{
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