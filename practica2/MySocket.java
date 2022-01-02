package practica2;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocket extends Socket{

    private String hostName, userName;
    private int port;
    

    public MySocket(String host, int port) {
        this.hostName = host;
        this.port = port;
    }

    public void execute(){
        try {
            Socket socket = new Socket(hostName, port);

            System.out.println("Connected to the chat server");
    
            new WriteThread(socket, this).start();
            new ReadThread(socket, this).start();
            
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch(IOException e){
            System.out.println("I/O Error: " + e.getMessage());
        }
    }   


    public void SetUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public static void main(String[] args) {
        if(args.length < 2){
            System.out.println("Please try again \n");
            System.out.println("Syntax: java MySocket <hostname> <port-number>");
            System.exit(0);
        }
        String hostName = args[0];
        int port = Integer.parseInt(args[1]);

        MySocket client = new MySocket(hostName, port);
        client.execute();

    }

}



