/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;
import input.Excepciones;
import piezas.Monton;
import piezas.Mano;
import java.util.*;
/**
 *
 * @author grvidal
 */
public class Domino {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\t\tBienvenido a appDomino G&G");
        intro();
        Monton todas= new Monton();
        //int jugadores1=nJugadores();
        Mano[] jugadores=new Mano[nJugadores()];
        //meter para jugar vs ia;
        System.out.println("Jugadores humanos: "+jugadores.length);
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i]=new Mano();
        }
        visualizarTodasLasPiezas(todas);
        System.out.println("\nCada jugador tiene "+jugadores[0].getNPiezas()+" piezas.");
        if((todas.getPiezasTotales()%jugadores.length)!=0)
            System.out.println("Y en el monton quedan= "+todas.getPiezasTotales()%jugadores.length);
        
        
        
    }
    
    public static int nJugadores(){ //Confirma el numero de jugadores entre 2 y 4.
        Scanner e= new Scanner (System.in);
        String aux;
        int toret;
        do {
            System.out.println("El domino se juega con un minimo de 2 jugadores y un maximo de 4.");
            toret= Excepciones.introducirNumero("Introduce el numero de jugadores: ");
            
        } while (toret>4 || toret<2);
        
    return toret;
    }
    
    public static void intro(){
        System.out.println("*************************************************");
        System.out.println("*Se aceptan donaciones en forma de puntos extra.*");
        System.out.println("*************************************************");
    }
    
    public static void visualizarTodasLasPiezas(Monton todas){
        
            System.out.println("\nVisualizando todas las piezas: ");
            System.out.println(todas);
    }
}
