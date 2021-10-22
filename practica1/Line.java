package practica1;

//import java.io.*;
import java.util.*;


public class Line {

    ArrayList<Character> line;    //ha de ser de tipus Character
    private int cursor;
    private boolean insert;

    public Line(){
        this.cursor = 0;
        //this.length = 0;        //length no se si val la pena utilitzarla pq amb els metodes set i add ja es modifica size()
        this.insert = false;
        this.line = new ArrayList<>();
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
        //this.length--;       
        }
    }

    public void suprCar(){          //suprimir car de la dreta (tecla supr)    
        if((this.cursor < this.line.size()-1) && (this.line.size()>0)){
            this.line.remove(this.cursor +1);
            //this.length--;
        }  
    }

    public void startLine(){
        this.cursor = 0;
    }

    public void endLine(){
        this.cursor = this.line.size();
    }

    public void right(){
        if(this.cursor < this.line.size()){
            this.cursor++;
        }
    }

    public void left(){
        if(this.cursor > 0){
            this.cursor--;
        }
    }

    public void addCar(char car){
        if(this.insert){            //tecla insert premuda --> si insert == true
            if(this.cursor < this.line.size()){
                this.line.set(this.cursor, car);
            }else{
                this.line.add(this.cursor, car);
                this.cursor++;
            }
        }else{                      //tecla insert no premuda --> si insert == false
            this.line.add(this.cursor, car);
            this.cursor++;
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
}
