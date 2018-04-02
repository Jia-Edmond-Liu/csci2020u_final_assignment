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

    private void cmdUPLOAD_MEDIA(String fileName) throws IOException{
        File file = new File("[INSERT LOCATION TO UPLOAD HERE]",fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        else{
            file.delete();
            file.createNewFile();
        }
        String extension=  fileName.substring(fileName.lastIndexOf(".") + 1);
        try
        {
            OutputStream out = socket.getOutputStream();
            DataOutputStream dout = new DataOutputStream(out);
            dout.writeUTF(extension);
            out.flush();

            FileInputStream in = new FileInputStream(file);
            BufferedInputStream bin = new BufferedInputStream(in);
            byte[] bytes = new byte[(int)file.length()];
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
    private void cmdUPLOAD_TEXT(String fileName) throws IOException{
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