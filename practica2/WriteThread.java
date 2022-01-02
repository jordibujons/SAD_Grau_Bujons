package practica2;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteThread extends Thread {
    private PrintWriter writer;
    private MySocket client;
    private Socket socket;

    public WriteThread(Socket socket, MySocket client){
        this.socket = socket;
        this.client = client;

        try{
            OutputStream oStream = socket.getOutputStream();
            writer = new PrintWriter(oStream, true);

        }catch(IOException e){
            System.out.println("Error getting output stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run(){
        Console console = System.console();

        String userName = console.readLine("\nEnter your name: ");
        client.SetUserName(userName);
        writer.println(userName);

        String text;
        
        do {
            //text = console.readLine("[" + userName + "]: ");
            text = console.readLine(userName);
            writer.println(text);
            
        } while (text != null);

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error writing to server: " + e.getMessage());
        }
    }  
}
