/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;
import input.Excepciones;
import piezas.*;
import java.util.*;
import Tablero.*;
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
        Partida partida = new Partida();
        int carro=1;
        int actual=carro;
        boolean puedeJugar=true;
        int cont=0;//borrar
        int turno;
        do {
            System.out.println("juega: "+actual);
            puedeJugar=jugada(todas,jugadores[actual],partida);
            if(jugadores[actual].getNPiezas()==0)
                System.out.println("El Jugador "+actual+ " ha ganado, gg.");//ha Ganado.
            cont++;//borrar
            if(cont==2)//borrar
                puedeJugar=false;
            actual= turno(actual,jugadores.length-1);
            
        } while (puedeJugar);
    }
    
    public static  boolean jugada(Monton monton,Mano jug, Partida partida){
        System.out.println(jug);
        int opcion;
        int maxOpciones=2;
        System.out.println("Eligue que hacer:"
                    + "\n1.-Jugar una ficha."
                    + "\n2.-Pasar");
            if(monton.getNPiezasMonton()>0){
                System.out.println("3.-Coger una ficha del monton.");
                maxOpciones=3;
            }
        do {
            opcion=Excepciones.introducirNumero("Opcion: ");
        } while (opcion>maxOpciones || opcion<0);
        switch (opcion){
            case 1: anhadirFicha(jug,partida);
                break;
            case 2: //pasar(); 
                break;
            case 3: //cogerDelMonton(jug,partida);
                break;
                
        }
        
        return true;
    }
    
    public static void anhadirFicha(Mano jug, Partida partida){
        System.out.println(jug);
        int opcion=Excepciones.introducirNumero("Que pieza deseas jugar: ");
        Pieza pieza=jug.getUnaPieza(opcion-1);
        int n1=pieza.getN1();
        int n2=pieza.getN2();
        if(partida.getNumNodos()==0){
            partida.
        }
    }
    
    public static  int turno(int actual, int numJug){
        if (actual==numJug) 
            actual=0;
        else 
            actual++;
        return actual;
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
        for (int i = 0; i < jugadores.length; i++) {
            for (int j = 0; j < jugadores[i].getPIEZAS_MANO(); j++) {
                pos=(int) (Math.random()*todas.getNPiezasTotales());
                if(esta(pos, todas)){
                    pieza= todas.getUnaPieza(pos);
                    jugadores[i].setUnaPieza(pieza);
                    todas.eliminarPiezaMonton(pieza);
                }
                else j--;
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
