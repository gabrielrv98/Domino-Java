/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;
import input.Excepciones;
import piezas.*;
import java.util.*;
import Tablero.Partida;
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
        Mano[] jugadores=new Mano[nJugadores()];
        //meter para jugar vs ia;
        System.out.println("Jugadores humanos: "+jugadores.length);
        for (int i = 0; i < jugadores.length; i++) {
            String aux=("Introduce el nombre del jugador "+(i+1)+":");
            aux=Excepciones.introducirCadena(aux);
            jugadores[i]=new Mano(aux);
        }
        visualizarTodasLasPiezas(todas);
        establecerMano(todas,jugadores);
        System.out.println("\nCada jugador tiene "+jugadores[0].getNPiezas()+" piezas.");
        if(todas.getNPiezasMonton()!=0)
            System.out.println("Y en el monton quedan "+todas.getNPiezasMonton());
        else
            System.out.println("No quedan piezas en el monton");
        System.out.println("El jugador1 ( "+ jugadores[0].getNombre()+" ) tiene en la mano: ");
        System.out.println(jugadores[0]); 
        System.out.println("sus puntos son: "+jugadores[0].SumaPuntos());
        Partida partida= new Partida(jugadores);
        int carro=1;
        boolean tienenFichas=true;
        boolean puedenJugar=false;//cambiar esto
        //carro es el primero en jugar:
        do{
            System.out.println(partida.toString());
        }while(tienenFichas && puedenJugar);
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
    
    public static void   establecerMano(Monton todas,Mano[] jugadores){
        int pos;
        Pieza pieza;
        Pieza DobleSeis= new Pieza(6,6);
        for (int i = 0; i < jugadores.length; i++) {
            for (int j = 0; j < jugadores[i].getPIEZAS_MANO(); j++) {
                pos=(int) (Math.random()*todas.getNPiezasTotales());
                if(esta(pos, todas)){
                    if(i==2 && j==4)
                        pieza=todas.getUnaPieza(27);
                    else
                        pieza= todas.getUnaPieza(pos);
                    jugadores[i].setUnaPieza(pieza);
                    todas.eliminarPiezaMonton(pieza);
                    if(i==2 && j==4)
                        if(jugadores[i].getPieza(j)==DobleSeis)
                         System.out.println("esta aqui jug "+i+ "carta "+j);
                }
                else 
                    j--;
            }
            
            
        }
    }
    public static boolean esta(int pieza,Monton todas){//saber si una pieza esta en el monton
        boolean toret;
        int n=0;
        piezas.Pieza busq=todas.getUnaPieza(pieza);
        piezas.Pieza[] monton=todas.getPiezasMonton();
        while(n<todas.getNPiezasTotales() && busq!=monton[n])
            n++;
        if(n==todas.getNPiezasMonton()){
            toret=false;
        }
        else
            toret=true;
        return toret;
        
    }
}
