package practica2;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread {
    private Socket socket;
    private MyServerSocket server;
    private PrintWriter writer;

    public Server(Socket socket, MyServerSocket server){
        this.socket = socket;
        this.server = server;
    }

    public void run(){
        try{
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output,true);

            printUsers();

            String userName = reader.readLine();
            server.addUserName(userName,this);

            String serverMessage = "Nou usuari conectat: "+userName;
            server.broadcast(serverMessage,userName);

            String clientMessage;

            do{
                clientMessage = reader.readLine();
                serverMessage = "["+userName+"]: "+clientMessage;
                server.broadcast(serverMessage,userName);
            }while(clientMessage != null);

            server.removeUser(userName,this);
            socket.close();

            serverMessage = userName + " ha abandonat la sessió.";
            server.broadcast(serverMessage, userName);

        }catch(IOException ex){
            System.out.println("Error en el servidor: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    void printUsers(){
        if(server.hasUsers()){
            writer.println("Usuaris conectats: "+server.getUserNames());
        }else{
            writer.println("No hi ha usuaris conectats.");
        }
    }

    void sendMessage(String message){
        writer.println(message);
    }
}
