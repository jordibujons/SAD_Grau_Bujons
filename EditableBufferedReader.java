import java.io.*;

class EditableBufferedReader extends BufferedReader{

    public EditableBufferedReader(Reader in) {
        super(in);
        System.out.println("hola");
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
    //probant sincronitzacio github
    
}

