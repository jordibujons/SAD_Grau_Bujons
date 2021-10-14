package practica1;
import java.io.*;
//import java.util.*;

class key{
    // public static final int ESC = '\033';  // '\033'
    // public static final int ESC_ = Integer.MIN_VALUE;
    // public static final int LEFT = Integer.MIN_VALUE+1;
    // public static final int RIGHT = Integer.MIN_VALUE+2;
    // public static final int START = Integer.MIN_VALUE+3;
    // public static final int FINAL = Integer.MIN_VALUE+4;
    // public static final int INSERT = Integer.MIN_VALUE+5; 
    // public static final int DELETE = Integer.MIN_VALUE+6;
    // public static final int BACKSPACE = 127;
    // public static final int ENTER = 10;
}

class EditableBufferedReader extends BufferedReader{
    
    private Line linia;
    //private int pos, len; //pos=posició, len=llargada

    private static final int ESC = '\033';  // '\033'
    private static final int ESC_ = 170;
    private static final int LEFT = 171;
    private static final int RIGHT = 172;
    private static final int START = 173;
    private static final int FINAL = 174;
    private static final int INSERT = 175; 
    private static final int DELETE = 176;
    private static final int BACKSPACE = 127;
    private static final int ENTER = 10;
    private static final int RAW = 43;  //boto + '\053'
    



    public EditableBufferedReader(Reader in) {
        super(in);
        this.linia = new Line();
    }

    public void setRaw () throws IOException, InterruptedException{
        //this.raw=true;
        String[] cmd = {"sh", "-c", "stty -echo raw </dev/tty"};
        Runtime.getRuntime().exec(cmd);
    }
    public void unSetRaw () throws IOException, InterruptedException{
        //this.raw=false;
        String[] cmd = {"sh", "-c", "stty echo cooked </dev/tty"};
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
        int caracter;
        caracter = super.read();

        if (caracter != ESC){
        //if (caracter != key.ESC){       //if(caracter != '\033')
            //return caracter;
        }
        else if (caracter == ESC){          
       // else if (caracter == key.ESC){  //comprovar que el seguent caracter es un corchet esqeurre
            caracter = super.read();
            switch(caracter = super.read()){
                case 'C': 
                   //caracter = key.RIGHT;
                   caracter = RIGHT;
                case 'D':
                    //caracter = key.LEFT;
                    caracter = LEFT;
                case 'F':
                    //caracter = key.FINAL;
                    caracter = FINAL;
                case 'H':
                    //caracter = key.START;
                    caracter = START;
                case '3':
                    if ((caracter = super.read()) == '~') caracter = DELETE;
                    //if ((caracter = super.read()) == '~') caracter = key.DELETE;
                    else caracter = '3';   
                case '2':               
                    if ((caracter = super.read()) == '~') caracter = INSERT;
                    //if ((caracter = super.read()) == '~') caracter = key.INSERT;
                    else caracter = '2';
            }
        }   
        return caracter;
    }
    /*
        pel case 3 i 2, és més eficient fer:
            if((ch = super.read()) != '~')
                return ch
            return key.START + ch - '1'
    */




    public String readLine() throws IOException {
        int caracter = 0;

       // try {
            //this.setRaw();
        
            do{
                caracter = this.read();
                if(caracter >= ESC_){
                //if(caracter >= key.ESC_){           // és innecessari if i el else if, mirar com treurels
                    switch (caracter) {
                        case LEFT:
                        //case key.LEFT:
                        this.linia.left();                        
                        break;
                        case RIGHT:              
                        //case key.RIGHT:
                        this.linia.right();
                        break;
                        case INSERT:
                       //case key.INSERT:
                        this.linia.switchInsert();
                        break;
                        case DELETE:
                        //case key.DELETE:
                        this.linia.suprCar();
                        break;
                        case BACKSPACE:
                        //case key.BACKSPACE:
                        this.linia.backspaceCar();
                        break;
                        case START:
                        //case key.START:
                        this.linia.startLine();
                        break;
                        case FINAL:
                        //case key.FINAL:
                        this.linia.endLine();
                        break;                            
                        default:
                        System.out.println("Invalid input");
                        break;
                    }
                }else if(caracter != ENTER){

               // }else if(caracter != key.ENTER){
                    this.linia.addCar(caracter);
                }
            }while(caracter != ENTER);
           // }while(caracter != key.ENTER);
           
            //this.unSetRaw();
    //    } catch (InterruptedException e) {
      //      e.printStackTrace();
        //}         
        return this.linia.toString();
    }
}