/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piezas;

/**
 *
 * @author reyga
 */
public class Pieza {
    private final int n1,n2;
    
    public Pieza(int n1, int n2){
        this.n1=n1;
        this.n2=n2;
    }
    
    @Override
    public String toString(){
        StringBuilder toret= new StringBuilder();
        toret.append("[ ");
        if (n1==0)
            toret.append(" ");
        else
            toret.append(n1);
        toret.append(" | ");
        if (n2==0)
            toret.append(" ");
        else
            toret.append(n2);
        toret.append(" ]");
        return toret.toString();
    }
}
