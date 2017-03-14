/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;
import piezas.Pieza;

/**
 *
 * @author OrenadorOmega
 */
public class Partida {
    private Nodo primero;
    private Nodo ultimo;
    private int numNodos;
    
    public Partida(){
        primero = new Nodo(null,null,null);
        ultimo = new Nodo(null,null,primero);
        numNodos=0;
    }
    
    public int getNumNodos(){
        return numNodos;
    }
    public void insertarPrincipio(Pieza pieza){
        Nodo nuevo= new Nodo(pieza,primero.getSig(),primero);
        primero.getSig().setAnt(nuevo);
        primero.setSig(nuevo);
        numNodos++;
    }
    public void insertarFinal(Pieza pieza){
        Nodo nuevo= new Nodo(pieza, ultimo,ultimo.getAnt());
        ultimo.getAnt().setSig(nuevo);
        ultimo.setAnt(nuevo);
        numNodos++;
    }
    
    @Override
    public String toString(){
        StringBuilder toret= new StringBuilder();
        Nodo actual=primero.getSig();
        toret.append("->");
        for (int i = 0; i < numNodos; i++) {
            toret.append(actual.getValor());
            if(actual.getSig()!=ultimo)
                toret.append(" - ");
            actual=actual.getSig();
        }
        toret.append(" <-");
        return toret.toString();
    }
    
}
