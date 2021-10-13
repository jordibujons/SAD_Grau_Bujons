package practica1;
import java.io.*;
//import java.util.*;

class key{
    public static final int ESC = '\033';  // '\033'
    public static final int ESC_ = 170;
    public static final int LEFT = 171;
    public static final int RIGHT = 172;
    public static final int START = 173;
    public static final int FINAL = 174;
    public static final int INSERT = 175; 
    public static final int DELETE = 176;
    public static final int BACKSPACE = 127;
    public static final int ENTER = 10;
    public static final int RAW = 43;  //boto + '\053'
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
    private boolean raw = false;



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

        if (caracter != key.ESC){       //if(caracter != '\033')
            //return caracter;
        }          
        else if (caracter == key.ESC){
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
        int caracter = 0;

        try {
            this.setRaw();
            do{
                caracter = this.read();
                if(caracter >= key.ESC_){
                    switch (caracter) {
                        case key.LEFT:
                            this.linia.left();                        
                            break;
                        case key.RIGHT:
                            this.linia.right();
                            break;
                        case key.INSERT:
                            this.linia.switchInsert();
                            break;
                        case key.DELETE:
                            this.linia.suprCar();
                            break;
                        case key.BACKSPACE:
                            this.linia.backspaceCar();
                            break;
                        case key.START:
                            this.linia.startLine();
                            break;
                        case key.FINAL:
                            this.linia.endLine();
                            break;                            
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                }else if(caracter != key.ENTER){
                    this.linia.addCar(caracter);
                }
            }while(caracter != key.ENTER);            
        } catch (Exception e) {}
      return this.linia.toString();
    }
}