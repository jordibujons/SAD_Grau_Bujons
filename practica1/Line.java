package practica1;

import java.io.*;

public class Line {

    private int cursor, length;
    private boolean insert;

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


}
