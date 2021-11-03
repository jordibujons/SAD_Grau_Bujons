package teoria.teoria_jordi;

public class EchoServer {
    public static void main(String[] args) {
        MyServerSocket ss = new MyServerSocket(Integer.parseInt(args[0]));
        while(true){
            MySocket s = ss.accept();
            new Thread() {
                public void run(){
                    String line;
                    while((line = s.readLine()) != null){
                        s.println(line);
                    }
                }
            }.start();
        }
    }
        
}
