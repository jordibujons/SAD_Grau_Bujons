package practica1.MVC;

//import java.io.*;
import java.util.*;


public class Line  extends Observable{

    ArrayList<Character> line;
    private int cursor;
    private boolean insert;
    private String[] accio;
    private StringBuilder lineSB;

    public Line(){
        this.cursor = 0;
        this.insert = false;
        this.line = new ArrayList<>();
        this.accio = new String[2];
    }

    public StringBuilder getLine(){
        return this.lineSB;
    }

    public int getPos(){
        return this.cursor;
    }

    public int getLength(){
        return this.line.size();
    }

    public boolean getInsert(){
        return insert;
    }

    public void switchInsert(){
        this.insert = !this.insert; 
    }

    public void backspaceCar(){        //suprimir car de l'esquerra (tecla bakcspace)
        if((this.cursor > 0) && (this.cursor <= this.line.size()) && (this.line.size()>0)){ 
        this.line.remove(this.cursor -1);  
        this.left();   
        accio[0] = "true";
        accio[1] = Constants.ERASE_ANSI;
        this.setChanged();
        this.notifyObservers(accio);       
        }
    }

    public void suprCar(){          //suprimir car de la dreta (tecla supr)    
        if((this.cursor < this.line.size()-1) && (this.line.size()>0)){
            this.line.remove(this.cursor +1);
            accio[0] = "true";
            accio[1] = Constants.ERASE_ANSI;
            this.setChanged();
            this.notifyObservers(accio);
        }  
    }

    public void startLine(){
        int posicio_actual = this.cursor;
        this.cursor = 0;
        accio[0] = "true";
        accio[1] = "\033[" + posicio_actual +"D";
        this.setChanged();
        this.notifyObservers(accio);
    }

    public void endLine(){
        if (this.cursor < this.line.size()){
            int posicio_actual = this.line.size() - this.cursor;
            this.cursor = this.line.size();
            accio[0] = "true";
            accio[1] = "\033["+posicio_actual+"C"; 
            this.setChanged();
            this.notifyObservers(accio);
        }
    }

    public void right(){
        if(this.cursor < this.line.size()){
            this.cursor++;
            accio[0] = "true";
            accio[1] = Constants.RIGHT_ANSI;
            this.setChanged();
            this.notifyObservers(accio);
        }
    }

    public void left(){
        if(this.cursor > 0){
            this.cursor--;
            accio[0] = "true";
            accio[1] = Constants.LEFT_ANSI;
            this.setChanged();
            this.notifyObservers(accio);
        }
        
    }

    public void addCar(char car){
        if(this.insert){            //tecla insert premuda --> si insert == true
            if(this.cursor < this.line.size()){
                this.line.set(this.cursor, car);
                this.cursor++;
                accio[0]="true";
                accio[1] = "" + car;
                this.setChanged();
                this.notifyObservers(accio);
            }else{ //OJO QUE AIXO POTSER NO ESTÀ BÉ
                this.line.add(this.cursor, car);
                this.cursor++;
                accio[0]="true";
                accio[1] = "" + car;
                this.setChanged();
                this.notifyObservers(accio);
                
            }
        }else{                      //tecla insert no premuda --> si insert == false
            this.line.add(this.cursor, car);
            this.cursor++;
            accio[0] = "true";//"insertChar";
            accio[1] = Constants.NO_INSERT_ANSI+car;  /*ANSI.INSERT + */
            this.setChanged();
            this.notifyObservers(accio);
        }
    }

    public String toString(){
        String str = "";
        int i = 0;
        int aux = 0;
        for(i=0; i<this.line.size(); i++){
            aux = this.line.get(i);
            str += (char)aux;
        }
        return str;
    }

    public void invalidInput(){
        accio[0]="false";
        this.setChanged();
        this.notifyObservers(accio);
    }
}
