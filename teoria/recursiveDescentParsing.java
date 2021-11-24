package teoria;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.commons.lang3.mutable;
/*
line ::= EOL | EOF | expression
expression::= term | term('+'|'-')expression
term ::=
*/ 
public class recursiveDescentParsing{
    
    static void RTError(String str){
        System.err.println(str);
        System.exit(1);
    }
    public static void main(String[] args) {
        if (args.length != 0) {
            RTError("usage: calc");
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    }

    boolean parseLine(BufferedReader in){
        MutableDouble r = new MutableDouble();
        String line = null;
        Scanner sc = null;

        try {
            line = in.readLine();
            sc = new Scanner(line);
        } catch (Exception e) {
        }
        if (parseEOF(sc)) {
            return false;
        }else if(parseEOL(sc)){
            return true;
        }else if(parseExression(sc, r)){
            System.out.println(r);
            if(!parseEOL(sc)){
                System.out.println("\033[31mERROR: EOL expected\033[0m");
                return true;
            }
            return true;
        }else{
            return true;
        }
    }

    boolean parseExression(Scanner sc, MutableDouble r){
        MutableDouble r1 = new MutableDouble();
        char[] ch = new char[1];
        if(!parseTerm(sc, r))
            return false;
        while(parseOp(sc, "+-", ch)){
            if(!parseTerm(sc, r1)){
                return false;
            }
            if(ch[0]=='+'){
                r.add(r1.doubleValue());
            }else{
                r.subtract(r1.doubleValue());
            }
        }
        return true;
        
    }

    boolean parseTerm(Scanner sc, MutableDouble r){
        MutableDouble r1 = new MutableDouble();
        char[] ch = new char[1];
        if(!parseFactor(sc, r))
            return false;
        while(parseOp(sc, "*/", ch)){
            if(!parseFactor(sc, r1)){
                return false;
            }
            if(ch[0]=='*'){
                r.setValue(r.doubleValue() * r1.doubleValue());
            }else{
                r.setValue(r.doubleValue() / r1.doubleValue());            }
        }
        return true;
        
    }
    boolean parseFactor(Scanner sc, MutableDouble r){
        if (parse(sc, '(')) {
            if (!parseExression(sc, r)) {
                return false;
            }
        
            if(!parse(sc, ')')){
                System.err.println("\033[31mERROR: ) expected\033[0m");
                return false;
            }
            return true;
        }
        if(!parseDouble(sc, r)){
            System.err.println("\033[31mERROR: invalid floating point number\033[0m");
            return false;
        }
        return true;
    }


    boolean parseDouble(Scanner sc, MutableDouble r){
        String oper = sc.findWithinHorizon("\\G\\p{javaWhitespace}*[+-]?\\d*\\.?\\d+(?:+[eE][+-]?\\d+)?", 0);
        if(oper == null){

        }
        
    }
    boolean parseOp(Scanner sc, String str, char[] ch){
        String op = sc.findWithinHorizon("\\G\\p{javaWhitespace}*([" + str + "])", 0);
        if(op==null)
            return false;
        ch[0]=sc.match().group(1).charAt(0);
        return true;       
    }

    boolean parse(Scanner sc, char ch){
        String op = sc.findWithinHorizon("\\G\\p{javaWhitespace}*[" + ch + "]", 0);
        return op != null;
    }
    boolean parseEOL(Scanner sc){
        try {
            sc.skip("^\\p{javaWhitespace}*$");
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
    boolean parseEOF(Scanner sc){
        return sc == null;
    }
}