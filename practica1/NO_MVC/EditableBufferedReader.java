package practica1.NO_MVC;
import java.io.*;
//import java.util.*;

class key{
    public static final int ESC = 27;  // '\033'
    public static final int ESC_ = Integer.MIN_VALUE;
    public static final int LEFT = Integer.MIN_VALUE+1;
    public static final int RIGHT = Integer.MIN_VALUE+2;
    public static final int START = Integer.MIN_VALUE+3;
    public static final int FINAL = Integer.MIN_VALUE+4;
    public static final int INSERT = Integer.MIN_VALUE+5; 
    public static final int DELETE = Integer.MIN_VALUE+6;
    public static final int BACKSPACE = 127;
    public static final int ENTER = 13;
}

class EditableBufferedReader extends BufferedReader{
    
    private Line linia;
    
    public EditableBufferedReader(Reader in) {
        super(in);
        this.linia = new Line();
    }

    public void setRaw() throws IOException, InterruptedException{
        String[] cmd = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor();
    }
    public void unSetRaw() throws IOException, InterruptedException{
        String[] cmd = {"/bin/sh", "-c", "stty echo cooked </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor(); 
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
    
        if ((caracter = super.read()) != key.ESC){       
            return caracter;
        }

        switch(caracter = super.read()){
            case '[':
                switch(caracter = super.read()){
                    case 'C': 
                        return caracter = key.RIGHT;
                    //caracter = RIGHT;
                    case 'D':
                        return caracter = key.LEFT;
                        //caracter = LEFT;
                    case 'F':
                        return caracter = key.FINAL;
                        //caracter = FINAL;
                    case 'H':
                        return caracter = key.START;
                        //caracter = START;
                    case '3':
                        if ((caracter = super.read()) == '~') return caracter = key.DELETE;
                        else return caracter = '3';   
                    case '2':               
                        if ((caracter = super.read()) == '~') return caracter = key.INSERT;
                        else return caracter = '2';
                    default:
                        return caracter;
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
        char caracter;

        try {
            setRaw();
            while((caracter = (char) this.read()) != key.ENTER){
                    switch (caracter) {
                        //case LEFT:
                        case (char) key.LEFT:
                            updateViewFletxaEsquerre(this.linia);
                            this.linia.left();                        
                        break;              
                        case (char) key.RIGHT:
                            updateViewFletxaDreta(this.linia);
                            this.linia.right();
                        break;
                        case (char) key.INSERT:
                            this.linia.switchInsert();
                        break;
                        case (char) key.DELETE:
                            updateViewDelete(this.linia);
                            this.linia.suprCar();
                        break;
                        case (char) key.BACKSPACE:
                            updateViewBackspace(this.linia);
                            this.linia.backspaceCar();
                        break;
                        case (char) key.START:
                            updateViewStart(this.linia);
                            this.linia.startLine();
                        break;
                        case (char) key.FINAL:
                            updateViewFinal(this.linia);
                            this.linia.endLine();
                        break;                            
                        default:
                            this.linia.addCar(caracter);
                            updateViewAddCar(this.linia, caracter);
                            //System.out.print((char)caracter);
                        break;
                    }
            }

            unSetRaw();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }         
        return this.linia.toString();
    }

    public void updateViewFletxaDreta (Line linia){
        if (linia.getPos() < linia.getLength()){
            System.out.print("\033[C");
        }
    }

    public void updateViewFletxaEsquerre (Line linia){
        if (linia.getPos() > 0){
            System.out.print("\033[D");
        }
    }

    public void updateViewDelete (Line linia){
        if ((linia.getPos() < linia.getLength()-1) && (linia.getLength()>0)){
            System.out.print("\033[C");
            System.out.print("\033[P");
            System.out.print("\033[D");
        }
    }

    public void updateViewBackspace (Line linia){
        if ((linia.getPos() > 0) && (linia.getPos() <= linia.getLength()) && (linia.getLength()>0)){
            System.out.print("\033[D");
            System.out.print("\033[P");
        }
    }

    public void updateViewStart (Line linia){
        System.out.print("\033[" + linia.getPos() +"D");
    }

    public void updateViewFinal (Line linia){
        System.out.print("\033["+(linia.getLength()-linia.getPos())+"C");
    }

    public void updateViewInsert (Line linia){
        if (linia.getPos() > 0){
            System.out.print("\033[D");
        }
    }

    public void updateViewAddCar(Line linia, char car){
        if(linia.getInsert() || linia.getPos() == linia.getLength()){
            System.out.print(car);
        }
        else{
            System.out.print("\033[@" + car);
        }
    }
}