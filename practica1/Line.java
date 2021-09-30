package practica1;

import java.io.*;
import java.util.*;

public class Line {

    ArrayList<Integer> line;
    private int cursor, length;
    private boolean insert;

    public Line(){
        this.cursor = 0;
        this.length = 0;
        this.insert = false;
        this.line = new ArrayList<>();
    }

    public int getPos(){
        return this.cursor;
    }

    public boolean getInsert(){
        return insert;
    }

    public void switchInsert(){
        this.insert = !this.insert; 
    }

    public void deleteCar(){        //suprimir car de l'esquerra
                
    }

    public void suprCar(){          //suprimir car de la dreta

    }

    public void startLine(){
        this.cursor = 0;

    }

    public void endLine(){
        this.cursor = this.line.size();

    }


}
