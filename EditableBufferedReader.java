import java.io.*;

class EditableBufferedReader extends BufferedReader{

    public EditableBufferedReader(Reader in) {
        super(in);
    }

    public static void setRaw (){
       //posa el terminal en mode raw bhigjfsekadlwfdnfjsgiuhef
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
    
    
}

