import java.io.DataInputStream;
import java.io.InputStream;
import java.io.EOFException;
import java.io.IOException;
//import org.apache.poi.util.*;

public class ReadInts {
    public static void main(String[] args) throws IOException{

        MyDataInputStream in = new MyDataInputStream(System.in);
        int i = 0;
        
        while(true){
            try{
                i = in.readIntLittle();
            }catch(EOFException e){
                break;
            }
        }
        System.out.println(i); 
    }

    static class MyDataInputStream extends DataInputStream {

        public MyDataInputStream(InputStream in) {
            super(in);
        }
        
        short readShortLittle() throws IOException{     //short son 2bytes
            short r;  
            
            r = (short) in.read(); 
            if(r == -1) throw new EOFException(); 
            r |= in.read() << 8;      //desplacem 8 bits a l'esquerre. Tambe podriem multiplicar per 2^8, és el mateix però menys eficient
            return r;                 // |= --> és una OR

    
        }
        int readIntLittle() throws IOException{     //int son 4bytes
            int r;

            r = readShortLittle();
            r |= readShortLittle() << 16;
            return r;

            /*--------------------UTILITZANT BIBLIOTECA-----------------------

            int r=0;
            try{
                r =LittleEndian.readInt(in);
            catch (LittleEndianBufferUnderrunException e){
                throw new EOFException();
            }
            return r; 

            */
            
        }
    
        long readLongLittle() throws IOException{   //Long son 8bytes
            long r;

            r = readIntLittle();
            r |= readIntLittle() << 32;
            return r;
            
        }
    }
}
