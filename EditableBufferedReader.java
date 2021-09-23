import java.io.*;
import java.util.*;

class EditableBufferedReader extends BufferedReader{

    public EditableBufferedReader(Reader in) {
        super(in);
        this.pos = 0;
        this.len = 0;
        this.line = new line();
    }

    public static void setRaw (){
        String[] cmd = {"sh", "-c", "stty -echo raw</dev/tty"};
        Runtime.getRuntime().exec(cmd);
    }
    public static void unSetRaw (){
        //treu el terminal de mode raw
    }
    public int read(){
        return 0;
        
    }
    public String readLine() {
        return null;
    } 
    
    // fletxa dreta --> ^[[C
    //fletxa esquerre --> ^[[D
    //escape --> ^[
    //final --> ^[[F  (fn+fletxa dreta)     
    //inici -->  ^[[H (fn+fletxa esquerre)
    //insertar --> 
    //delete --> ^[[3~
    
}

