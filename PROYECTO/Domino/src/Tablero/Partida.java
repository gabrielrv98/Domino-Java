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
    private Nodo nuevo;
    private int numNodos;
    
    public Partida(){
        primero = new Nodo(null,null,null);
        ultimo = new Nodo(null,null,primero);
        primero.setSig(ultimo);
        numNodos=0;
    }
    
    public int getNumNodos(){
        return numNodos;
    }
    public void insertarPrincipio(Pieza pieza){
        Nodo nuevo= new Nodo(pieza,primero.getSig(),primero);
        primero.getSig().setAnt(nuevo);
        primero.setSig(nuevo);
        this.nuevo=nuevo;
        numNodos++;
    }
    public void insertarFinal(Pieza pieza){
        Nodo nuevo= new Nodo(pieza, ultimo,ultimo.getAnt());
        ultimo.getAnt().setSig(nuevo);
        ultimo.setAnt(nuevo);
        this.nuevo=nuevo;
        numNodos++;
    }
    public int getPrimera(){
        return primero.getSig().getValor().getN1();
    }
    public int getUltima(){
        return ultimo.getAnt().getValor().getN2();
    }
    
    @Override
    public String toString(){
        StringBuilder toret= new StringBuilder();
        toret.append("----------------------------------------\n");
        if(numNodos==0){
            toret.append("No hay fichas en el tablero.");
        }
        else{
            Nodo actual=primero.getSig();
            int cont=0;
            toret.append("->");
            for (int i = 0; i < numNodos; i++) {
                cont++;
                if(actual==nuevo){
                    toret.append("\033[34m");
                    toret.append(actual.getValor());
                    toret.append("\033[30m");
                }
                else toret.append(actual.getValor());
                if(cont==8){
                    cont=0;
                    toret.append("\n");
                }
                if(actual.getSig()!=ultimo)
                    toret.append(" - ");
                actual=actual.getSig();
            }
            toret.append(" <-");
        }
        toret.append("\n----------------------------------------\n");
        return toret.toString();
    }
    
}
