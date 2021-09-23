import java.io.*;
import java.util.*;

class EditableBufferedReader extends BufferedReader{
    
    private Line linia;
    private int pos, len; //pos=posició, len=llargada

    private static final char ESC = ;
    private static final int LEFT = ;
    private static final int RIGHT = ;
    private static final int START = ;
    private static final int FINAL = ;
    private static final int INSERT = ; //????
    private static final int DELETE = ;



    public EditableBufferedReader(Reader in) {
        super(in);
        this.linia = new Line();
        this.pos = 0;
        this.len = 0;
    }

    public static void setRaw (){
        String[] cmd = {"sh", "-c", "stty -echo raw</dev/tty"};
        Runtime.getRuntime().exec(cmd);
    }
    public static void unSetRaw (){
        String[] cmd = {"sh", "-c", "stty echo cooked</dev/tty"};
        Runtime.getRuntime().exec(cmd); 
    }


    // fletxa dreta --> ^[[C
    //fletxa esquerre --> ^[[D
    //escape --> ^[
    //final --> ^[[F  (fn+fletxa dreta)     
    //inici -->  ^[[H (fn+fletxa esquerre)
    //insertar --> ?¿?¿?¿
    //delete --> ^[[3~

    public int read() throws IOException{
        int caracter = 0;
        caracter = super.read();

        if (caracter != ESC){} //retorna caracter
        else if (caracter == ESC){
            switch(caracter = super.read()){
                case 'C': 
                    caracter = RIGHT;
                case 'D':
                    caracter = LEFT;
                case 'F':
                    caracter = FINAL;
                case 'H':
                    caracter = START;
                case '3':                   //falta mirar què passa amb ~
                    caracter = DELETE;      
                case '?????':               //?????
                    caracter = INSERT;



            }


        }   
    }




    public String readLine() {
        return null;
    } 
    
    
}