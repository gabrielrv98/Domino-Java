/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;
import input.Excepciones;
import piezas.Pieza;

/**
 *
 * @author OrenadorOmega
 * @param <E> Será un pieza.
 */
public class Partida<E> {
    private Nodo<E> primero;
    private Nodo<E> ultimo;
    private Nodo<E> nuevo;
    private int numNodos;
    
    public Partida(){
        E aux= (E) new Pieza(0,0);
        primero = new Nodo<>(aux,null,null);
        ultimo = new Nodo<>(aux,null,primero);
        primero.setSig(ultimo);
        numNodos=0;
    }
    
    public int getNumNodos(){
        return numNodos;
    }
    /**
     * Inserta la pieza al principio
     * @param pieza pieza insertada
     */
    public void insertarPrincipio(E pieza){
        Nodo<E> insercion= new Nodo<>(pieza,primero.getSig(),primero);
        primero.getSig().setAnt(insercion);
        primero.setSig(insercion);
        this.nuevo=insercion;
        numNodos++;
    }
    /**
     * Inserta la pieza al final
     * @param pieza pieza insertada
     */
    public void insertarFinal(E pieza){
        Nodo insercion= new Nodo(pieza, ultimo,ultimo.getAnt());
        ultimo.getAnt().setSig(insercion);
        ultimo.setAnt(insercion);
        this.nuevo=insercion;
        numNodos++;
    }
    /**
     * 
     * @return Devuelve el valor con el que se puede unir una pieza por la izq
     */
    public E getPrimera(){
        return primero.getSig().getValor();
    }
    /**
     * 
     * @return Devuelve el valor con el que se puede unir una pieza por la derecha
     */
    public E getUltima(){
        return ultimo.getAnt().getValor();
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
