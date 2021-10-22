package practica1.MVC;


public class Constants {
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

    public static final String LEFT_ANSI = "\033[D";
    public static final String RIGHT_ANSI = "\033[C";
    public static final String ERASE_ANSI = "\033[P"; //la P borra el caracter on estic
    public static final String NO_INSERT_ANSI = "\033[@";
    public final static String BACKSPACE_ANSI = LEFT_ANSI + ERASE_ANSI;
}
