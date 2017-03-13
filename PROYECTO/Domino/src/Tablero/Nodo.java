/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;
import piezas.Pieza;
/**
 *
 * @author reyga
 */
public class Nodo {
    private Pieza pieza;
    private Nodo siguiente;
    private Nodo anterior;
    
    public Nodo(Pieza p, Nodo ant, Nodo sig){
        pieza=p;
        siguiente=sig;
        anterior=ant;
    }
    public Nodo getSig(){
        return siguiente;
    }
    public Nodo getAnt(){
        return anterior;
    }
    public void setSig(Nodo sig){
        siguiente=sig;
    }
    public void setAnt(Nodo ant){
        anterior=ant;
    }
    
}
