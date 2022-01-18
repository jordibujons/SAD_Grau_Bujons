package teoria;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import practica2.MySocket;

public class EchoClient {

    public static void main(String[] args) {
        // input thread
        MySocket sc = new MySocket(args[0], Integer.parseInt(args[1]));

        new Thread() {
            public void run() {
                BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
                String linia;
                try {
                    
                } catch (Exception e) {
                    while ((linia = kbd.readLine()) != null) {
                        sc.println(linia);
                    }
                }
                // close sc for waiting (tancar connexió del thread input del client al servidor) nose si té a veure pero ctrl+d tanca la connexió
            }
        }.start();

        // output thread

        MySocket sc = new MySocket(args[0], Integer.parseInt(args[1]));

        new Thread() {
            public void run() {

                String linia;
                while ((linia = sc.readLine()) != null) {
                    System.out.println(linia);
                }
            }
        }.start();

    }
}

//per executar al terminal CLIENT: java EchoClient localhost 50000
//                         SERVIDOR: java EchoServer 50000