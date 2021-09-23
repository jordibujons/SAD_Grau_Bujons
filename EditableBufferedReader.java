import java.io.*;

import jdk.tools.jlink.internal.SymLinkResourcePoolEntry;

class EditableBufferedReader extends BufferedReader{

    public EditableBufferedReader(Reader in) {
        super(in);
        System.out.println("klk");
    }

    public static void setRaw (){
       
    }
    public static void unSetRaw (){
        
    }
    public int read(){
        return 0;
        
    }
    public String readLine() {
        return null;
    } 
    
    
}

