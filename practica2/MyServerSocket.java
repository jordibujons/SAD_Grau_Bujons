package Practica2;

import java.io.*;
import java.net.*;

public class MyServerSocket extends ServerSocket {
    private ServerSocket serverSocket;
    private MySocket socket;

    public MyServerSocket(int port) throws IOException {

        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public MySocket accept() {
        try {
            this.socket = new MySocket(serverSocket.accept());

            return socket;
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return null;
    }

    @Override
    public void close() {
        try {
            this.serverSocket.close();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
