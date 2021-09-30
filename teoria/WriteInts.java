import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Scanner;

public class WriteInts {

    public static void main (String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        DataOutputStream out = new DataOutputStream(System.out);

        int i = 0;
        while(sc.hasNextInt()){
            i = sc.nextInt();
            out.writeInt(i);
        }
        out.flush();
        //out.close(); si fem el close fa implicit el flush. Necessitem fer flush pq sino no s'escriu el fitxer amb el out.writeInt()
    }
}
