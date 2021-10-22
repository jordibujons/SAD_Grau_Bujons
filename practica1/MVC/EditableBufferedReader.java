package practica1.MVC;
import java.io.*;

//import java.util.*;

class EditableBufferedReader extends BufferedReader{
    
    private Line linia;
    //private Console console;
    
    public EditableBufferedReader(Reader in) {
        super(in);
        this.linia = new Line();
        //this.console = new Console();
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
    
        if ((caracter = super.read()) != Constants.ESC){       
            return caracter;
        }

        switch(caracter = super.read()){
            case '[':
                switch(caracter = super.read()){
                    case 'C': 
                        return caracter = Constants.RIGHT;
                    //caracter = RIGHT;
                    case 'D':
                        return caracter = Constants.LEFT;
                        //caracter = LEFT;
                    case 'F':
                        return caracter = Constants.FINAL;
                        //caracter = FINAL;
                    case 'H':
                        return caracter = Constants.START;
                        //caracter = START;
                    case '3':
                        if ((caracter = super.read()) == '~') return caracter = Constants.DELETE;
                        else return caracter = '3';   
                    case '2':               
                        if ((caracter = super.read()) == '~') return caracter = Constants.INSERT;
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
            return Constants.START + ch - '1'
    */




    public String readLine() throws IOException {
        char caracter;

        try {
            setRaw();
            while((caracter = (char) this.read()) != Constants.ENTER){
                    switch (caracter) {
                        //case LEFT:
                        case (char) Constants.LEFT:
                            updateViewFletxaEsquerre(this.linia);
                            this.linia.left();                        
                        break;              
                        case (char) Constants.RIGHT:
                            updateViewFletxaDreta(this.linia);
                            this.linia.right();
                        break;
                        case (char) Constants.INSERT:
                            this.linia.switchInsert();
                        break;
                        case (char) Constants.DELETE:
                            updateViewDelete(this.linia);
                            this.linia.suprCar();
                        break;
                        case (char) Constants.BACKSPACE:
                            updateViewBackspace(this.linia);
                            this.linia.backspaceCar();
                        break;
                        case (char) Constants.START:
                            updateViewStart(this.linia);
                            this.linia.startLine();
                        break;
                        case (char) Constants.FINAL:
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
            System.out.print(Constants.RIGHT_ANSI);
        }
    }

    public void updateViewFletxaEsquerre (Line linia){
        if (linia.getPos() > 0){
            System.out.print(Constants.LEFT_ANSI);
        }
    }

    public void updateViewDelete (Line linia){
        if ((linia.getPos() < linia.getLength()-1) && (linia.getLength()>0)){
            System.out.print(Constants.RIGHT_ANSI);
            System.out.print(Constants.ERASE_ANSI);
            System.out.print(Constants.LEFT_ANSI);
        }
    }

    public void updateViewBackspace (Line linia){
        if ((linia.getPos() > 0) && (linia.getPos() <= linia.getLength()) && (linia.getLength()>0)){
            System.out.print(Constants.LEFT_ANSI);
            System.out.print(Constants.ERASE_ANSI);
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
            System.out.print(Constants.LEFT_ANSI);
        }
    }

    public void updateViewAddCar(Line linia, char car){
        if(linia.getInsert() || linia.getPos() == linia.getLength()){
            System.out.print(car);
        }
        else{
            System.out.print(Constants.NO_INSERT_ANSI + car);  //no ModeInsert
        }
    }
}