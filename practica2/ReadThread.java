package practica2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread{
    private BufferedReader br;
    private Socket socket;
    private MySocket client;

    public ReadThread(Socket socket, MySocket client){
        this.socket = socket;
        this.client = client;

        try {
            InputStream iStream = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(iStream));
        } catch (IOException e) {
            System.out.println("Error getting input stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            try {
                String resp = br.readLine();
                System.out.println("\n" + resp);

                // prints the username despres d'ensenyar el missatge del server
                if (client.getUserName() != null) {
                    System.out.print(client.getUserName());
                }
            } catch (IOException e) {
                System.out.println("Error reading from server: " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
    }




    
}
