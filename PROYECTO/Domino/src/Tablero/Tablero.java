/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;
import piezas.*;

/**
 *
 * @author reyga
 */
public class Tablero {
    private Partida<Pieza> partida;
    
    public Tablero(){
        partida= new Partida<>();
    }
    
    public void insertarPrincipio(Pieza pieza){
        partida.insertarPrincipio(pieza);
    }
    
    public void insertarFinal(Pieza pieza){
        partida.insertarFinal(pieza);
    }
    
    public int getNumNodos(){
        return partida.getNumNodos();
    }
    
    public int getPrimera(){
        return partida.getPrimera().getN1();
    }
    
    public int getUltima(){
        return partida.getUltima().getN2();
    }
    
    public String toString(){
        return partida.toString();
    }
}
