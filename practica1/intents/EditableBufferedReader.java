package practica1.intents;

import java.io.*;
import java.util.*;


class key{
    public static final int ESC = 27;  // '\033'
    public static final int ESC_ = 3;
    public static final int LEFT = 68;
    public static final int RIGHT = 67;
    public static final int START = 65;
    public static final int FINAL = 66;
    public static final int INSERT = 50; 
    public static final int DELETE = 51;
    public static final int BACKSPACE = 127;
    public static final int ENTER = 13;
}

class EditableBufferedReader extends BufferedReader{    
    
    private Line linia;
    //private int pos, len; //pos=posició, len=llargada

    // private static final int ESC = '\033';  // '\033'
    // private static final int ESC_ = 170;
    // private static final int LEFT = 171;
    // private static final int RIGHT = 172;
    // private static final int START = 173;
    // private static final int FINAL = 174;
    // private static final int INSERT = 175; 
    // private static final int DELETE = 176;
    // private static final int BACKSPACE = 127;
    // private static final int ENTER = 10;
    // private static final int RAW = 43;  //boto + '\053'
    



  




    public EditableBufferedReader(Reader in) {
        super(in);
        //this.linia = new Line();
    }

    private void setRaw() throws IOException, InterruptedException{
        //this.raw=true;
        //String[] cmd = {"/bin/sh", "-c", "stty raw -echo </dev/tty"};
        //Runtime.getRuntime().exec(cmd).waitFor();
        List<String> comm = Arrays.asList("/bin/sh", "-c", "stty -echo raw </dev/tty");
        ProcessBuilder p = new ProcessBuilder(comm);
        try {
            p.start();
        } catch (IOException ex) {
            System.out.println("Error introducing Raw mode");
        }
    }
    private void unSetRaw() throws IOException, InterruptedException{
        //this.raw=false;
        //String[] cmd = {"/bin/sh", "-c", "stty -raw echo </dev/tty"};
        //Runtime.getRuntime().exec(cmd).waitFor(); 
        List<String> comm = Arrays.asList("/bin/sh", "-c", "stty echo cooked </dev/tty");
        ProcessBuilder p = new ProcessBuilder(comm);
        try {
            p.start();
        } catch (IOException ex) {
            System.out.println("Error introducing Raw mode");
        }
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

        if (caracter != key.ESC){       //if(caracter != '\033')
            //return caracter;
        }          
        else if (caracter == key.ESC){  //comprovar que el seguent caracter es un corchet esqeurre
            caracter = super.read();
            switch(caracter = super.read()){
                case 'C': 
                    caracter = key.RIGHT;
                case 'D':
                    caracter = key.LEFT;
                case 'F':
                    caracter = key.FINAL;
                case 'H':
                    caracter = key.START;
                case '3':
                    if ((caracter = super.read()) == '~') caracter = key.DELETE;
                    else caracter = '3';   
                case '2':               
                    if ((caracter = super.read()) == '~') caracter = key.INSERT;
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
       Line linia = new Line();
        int caracter;
       try {
            setRaw();
            
        
            while((caracter = this.read()) != key.ENTER){
                //caracter = this.read();
                //if(caracter >= key.ESC_){ 
                //if(caracter<=key.DELETE){         // és innecessari if i el else if, mirar com treurels
                    switch (caracter) {
                        case key.LEFT:
                        linia.left();                        
                        break;
                        case key.RIGHT:
                        linia.right();
                        break;
                        case key.INSERT:
                        linia.switchInsert();
                        break;
                        case key.DELETE:
                        linia.suprCar();
                        break;
                        case key.BACKSPACE:
                        linia.backspaceCar();
                        break;
                        case key.START:
                        linia.startLine();
                        break;
                        case key.FINAL:
                        linia.endLine();
                        break;                            
                        default:
                        //System.out.print("Invalid input");
                        linia.addCar(caracter);
                        System.out.print((char)caracter);
                        break;
                    }
                //}else{
                //}else if(caracter != key.ENTER){
                //linia.addCar(caracter);
                
                //}
            }
            //}while(caracter != key.ENTER);
            unSetRaw();
        } catch (InterruptedException e) {
             e.printStackTrace();
        }         
        return linia.toString();
    }
}