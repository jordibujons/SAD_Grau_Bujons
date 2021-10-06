package practica1;
import java.io.*;
//import java.util.*;

class EditableBufferedReader extends BufferedReader{
    
    private Line linia;
    //private int pos, len; //pos=posició, len=llargada

    private static final int ESC = 170; // '\033'
    private static final int LEFT = 171;
    private static final int RIGHT = 172;
    private static final int START = 173;
    private static final int FINAL = 174;
    private static final int INSERT = 175; 
    private static final int DELETE = 176;
    private static final int BACKSPACE = 127;
    private static final int ENTER = 10;




    public EditableBufferedReader(Reader in) {
        super(in);
        this.linia = new Line();
        // this.pos = 0;
        // this.len = 0;
    }

    public static void setRaw () throws IOException{
        String[] cmd = {"sh", "-c", "stty -echo raw</dev/tty"};
        Runtime.getRuntime().exec(cmd);
    }
    public static void unSetRaw () throws IOException{
        String[] cmd = {"sh", "-c", "stty echo cooked</dev/tty"};
        Runtime.getRuntime().exec(cmd); 
    }


    //fletxa dreta --> ^[[C
    //fletxa esquerre --> ^[[D
    //escape --> ^[             -->es considera un únic caracter, no 2, ja que ^ és de control
    //final --> ^[[F  (fn+fletxa dreta)     
    //inici -->  ^[[H (fn+fletxa esquerre)
    //insertar --> ^[[2~
    //delete --> ^[[3~

    public int read() throws IOException{
        int caracter = 0;
        caracter = super.read();

        if (caracter != ESC){       //if(caracter != '\033')
            return caracter;
        }          
        else if (caracter == ESC){
            caracter = super.read();
            switch(caracter = super.read()){
                case 'C': 
                    caracter = RIGHT;
                case 'D':
                    caracter = LEFT;
                case 'F':
                    caracter = FINAL;
                case 'H':
                    caracter = START;
                case '3':
                    if ((caracter = super.read()) == '~') caracter = DELETE;
                    else caracter = '3';   
                case '2':               
                    if ((caracter = super.read()) == '~') caracter = INSERT;
                    else caracter = '2';
            }
        }   
        return caracter;
    }




    public String readLine() throws IOException {
        int caracter = 0;

        do{
            caracter = this.read();
            if(caracter >= ESC){
                switch (caracter) {
                    case LEFT:
                        this.linia.left();                        
                        break;
                    case RIGHT:
                        this.linia.right();
                        break;
                    case INSERT:
                        this.linia.switchInsert();
                        break;
                    case DELETE:
                        this.linia.suprCar();
                        break;
                    case BACKSPACE:
                        this.linia.backspaceCar();
                        break;
                    case START:
                        this.linia.startLine();
                        break;
                    case FINAL:
                        this.linia.endLine();
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }
        }while(caracter != ENTER);
        
        return this.linia.toString();
    }
}