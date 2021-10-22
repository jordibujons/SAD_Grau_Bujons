package practica1.MVC;
import java.io.*;

//import java.util.*;

class EditableBufferedReader extends BufferedReader{
    
    private Line linia;
    private Console console;
    
    public EditableBufferedReader(Reader in) {
        super(in);
        this.linia = new Line();
        this.console = new Console();
        this.linia.addObserver(this.console);
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
                    case 'D':
                        return caracter = Constants.LEFT;
                    case 'F':
                        return caracter = Constants.FINAL;
                    case 'H':
                        return caracter = Constants.START;
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
                        case (char) Constants.LEFT:
                            this.linia.left();                        
                        break;              
                        case (char) Constants.RIGHT:
                            this.linia.right();
                        break;
                        case (char) Constants.INSERT:
                            this.linia.switchInsert();
                        break;
                        case (char) Constants.DELETE:
                            this.linia.suprCar();
                        break;
                        case (char) Constants.BACKSPACE:
                            this.linia.backspaceCar();
                        break;
                        case (char) Constants.START:
                            this.linia.startLine();
                        break;
                        case (char) Constants.FINAL:
                            this.linia.endLine();
                        break;                            
                        default:
                            this.linia.addCar(caracter);
                        break;
                    }
            }

            unSetRaw();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }         
        return this.linia.toString();
    }
}

    