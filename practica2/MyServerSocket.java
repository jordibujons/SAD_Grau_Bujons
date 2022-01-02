package practica2;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyServerSocket {
    private int port;
    public static ConcurrentHashMap<String,Server> clients = new ConcurrentHashMap<String,Server>();

    public MyServerSocket(int port){
        this.port = port;
    }

    public void execute(){
        try(ServerSocket serverSocket = new ServerSocket(port)){

            System.out.println("Chat server escoltant port "+port);

            while(true){
                //Esperem fins que un client nou es conecta
                Socket socket = serverSocket.accept();
                System.out.println("Nou usuari conectat");

                Server newUser = new Server(socket,this);

                newUser.start();
            }
        }catch(IOException ex){
            System.out.println("Error en el servidor: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //Comprobem que el admin inicialitza be el servidor
        if(args.length < 1){
            System.out.println("Siusplau, torna-ho a intentar");
            System.out.println("Syntax: java MyServerSocket <port-number>");
            System.exit(0);
        }

        //parse el port al servidor
        int port = Integer.parseInt(args[0]);

        //inicialitzar el servidor
        MyServerSocket server = new MyServerSocket(port);
        server.execute();
    }

    void broadcast(String message, String excludeUser){
        for(String key : this.clients.keySet()){
            if(!key.equals(excludeUser)){
                this.clients.get(key).sendMessage(message);
            }
        }
    }

    void addUserName(String userName, Server server){
        this.clients.put(userName, server);
    }

    void removeUser(String userName, server aUser){
        clients.remove(userName);
        System.out.println("L'usuari "+userName+" ha abandonat la sessió.");
    }

    Set<String> getUserNames(){
        Set<String> userNames = new HashSet<>();
        for(String key : this.clients.keySet()){
            userNames.add(key);
        }
        return userNames;
    }

    boolean hasUsers(){
        return !this.clients.isEmpty();
    }
}
