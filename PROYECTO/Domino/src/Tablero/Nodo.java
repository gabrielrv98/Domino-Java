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
 */
public class Nodo {
    private Pieza valor;
    private Nodo siguiente;
    private Nodo anterior;
    
    public Nodo(Pieza v, Nodo sig, Nodo ant){
        valor=v;
        siguiente=sig;
        anterior=ant;
    }

    public Pieza getValor() {
        return valor;
    }

    public Nodo getSig() {
        return siguiente;
    }

    public void setSig(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnt() {
        return anterior;
    }

    public void setAnt(Nodo anterior) {
        this.anterior = anterior;
    }

    
}
