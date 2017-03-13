/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;
import domino.Domino;
import input.Excepciones;
import piezas.*;


/**
 *
 * @author reyga
 */
public class Partida {
    private final int DOBLE_PARA_SALIR=6;
    private Nodo principio;
    private Nodo ultima;
    private int numElem;
    
    public Partida(Mano[] jugadores){
        principio= new Nodo(null,null,ultima);
        ultima= new Nodo(null,principio,null);
        principio.setSig(ultima);
        numElem=0;
        
        Pieza DobleSeis= new Pieza(DOBLE_PARA_SALIR,DOBLE_PARA_SALIR);
        int j=0;
        int pos=0;
        /*
        while(j<jugadores.length){
            int i=0;
            while(i<jugadores[j].getNPiezas() && (jugadores[j].getPieza(i)!=DobleSeis) ){
               if (jugadores[j].getPieza(i)==DobleSeis){
                    System.out.println(jugadores[j].getPieza(i));
                    System.out.println(DobleSeis);
                }
                i++;
            }
            if(i==jugadores[j].getNPiezas()){
                j++;
                System.out.println(j);
            }
                
            else if (jugadores[j].getPieza(i)==DobleSeis){
                System.out.println("Encontrado.");
                System.out.println("jugador "+j);
                System.out.println(jugadores[j]);
                j=10;
            }
        }
        */
        System.out.println("fin");
    }
    
    public String toString(){
        StringBuilder toret= new StringBuilder();
        if(numElem==0)
            toret.append("El tablero esta vacio.");
        else{
            toret.append("-> ");
            Nodo actual=principio.getSig();
            for (int i = 0; i < numElem; i++) {
                toret.append(actual);
                toret.append("-");
                actual=actual.getSig();
            }
            toret.append(" <-");
        }
        return toret.toString();
            
    }
    
}
