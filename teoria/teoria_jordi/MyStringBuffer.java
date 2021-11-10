package teoria.teoria_jordi;

import java.util.Arrays;

public class MyStringBuffer {

    char[] str;
    int limit;
    static final int CHUNK = 10; //constant per indicar tamany inicial de l'array

    public MyStringBuffer(){
        str = new char[CHUNK];
        //limit = 0; //ho posem comentat pq un int per si sol ja s'inicialitza a 0
    }

    public MyStringBuffer insert(int offset, char ch){ //afegim char (fem espai i afegim)
        if(offset > limit || offset < 0){
            throw new IndexOutOfBoundsException();
        }
        if(limit == str.length){
            char[] tmp = str;
            /*str = new char[str.length*2];                     //primera manera que ho hem fet (rudimentari)
            System.arraycopy(tmp, 0, str, 0, tmp.length);*/
            str = Arrays.copyOf(tmp,str.length*2);              //segona manera amb aquest mètode més nou fem el mateix
        }
        for(int i = limit; i > offset; i--){
            str[i] = str[i - 1];
        }
        str[offset] = ch;
        limit++;
        return this;
    }

    public MyStringBuffer deleteCharAt(int index){ //borrem char (directament recol·loquem tots els chars de la dreta)
        if(index >= limit || index < 0){
            throw new IndexOutOfBoundsException();
        }
        for(int i = index; i < limit - 1; i++){
            str[i] = str[i + 1];
        }
        limit--;
        return this;
    }

    public MyStringBuffer setCharAt(int index, char ch){ //canviem el char de la posició que ens indiquen
        if(index >= limit || index < 0){
            throw new IndexOutOfBoundsException();
        }
        str[index] = ch;
        return this;
    }

    public int length(){
        return limit;
    }

    public String toString(){
        return new String(str, 0, limit);
    }

    public static void main(String[] args) { //NO M'HA DONAT TEMPS DE COPIAR-LO SENCER
        MyStringBuffer buf = new MyStringBuffer();

        buf.insert(0, 'a').insert(1, 'b').
    }
    
}
