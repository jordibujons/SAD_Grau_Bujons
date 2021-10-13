import java.io.*;

public class ComptadorColumnes{
    public static void main(String[] args) {
        try{
            String[] command = {"/bin/sh","-c","tput cols 2>/dev/tty"}; //"tput cols" ens retorna el numero de columnes
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();      //esperem que executi la comanda
            InputStreamReader in = new InputStreamReader(process.getInputStream());
            BufferedReader br = new BufferedReader(in);
            int numero_columnes = Integer.parseInt(br.readLine());
            System.out.println(numero_columnes);
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
