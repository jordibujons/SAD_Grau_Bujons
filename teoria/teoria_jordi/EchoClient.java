package teoria.teoria_jordi;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EchoClient {
    public static void main(String[] args){
        MySocket sc = new MySocket(args[0], Integer.parseInt(args[1])); //FALTA CREAR CLASSE MySocket (FORMA PART P2)
        //Input thread
        new Thread() {
            public void run(){
                String line;
                BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
                while((line = kbd.readLine()) != null){
                    sc.println(line);
                }
                //close sc for writing
            }
        }.start();

        //Output thread
        new Thread() {
            public void run(){
                String line;
                while((line = sc.readLine()) != null){
                    System.out.println(line);
                }
            }
        }.start();
    }
    
}
