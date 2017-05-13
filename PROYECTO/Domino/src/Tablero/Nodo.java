/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;
import piezas.*;

/**
 *
 * @author OrenadorOmega
 * @param <E> posiblemente pieza
 */
public class  Nodo<E>{// extends Object{
    private E valor;
    private Nodo<E> siguiente;
    private Nodo<E> anterior;
    
    public Nodo (E v, Nodo<E> sig, Nodo<E> ant){
        valor=v;
        siguiente=sig;
        anterior=ant;
    }
    
    public E getValor() {
        return valor;
    }

    public Nodo<E> getSig() {
        return siguiente;
    }

    public void setSig(Nodo<E> siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo<E> getAnt() {
        return anterior;
    }

    public void setAnt(Nodo<E> anterior) {
        this.anterior = anterior;
    }

    
}
