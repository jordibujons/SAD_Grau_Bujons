import java.io.*;
import java.io.DataInputStream;
import java.io.EOFException;

public class ReadInts {
    public static void main(String[] args){

        DataInputStream in = new DataInputStream(System.in);
        int i = 0;
        
        while(true){
            try{
                i = in.readInt();
            }catch(EOFException e){
                break;
            }
        }
        System.out.println(i); 
    }
}