/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Pasar constantes a Ajustes (carro...)**
 * Arreglar añadir ficha***
 * falta meter ia*
 */
package domino;
import input.Excepciones;
import piezas.*;
import java.util.*;
import Tablero.*;
import Settings.Ajustes;
/**
 *
 * @author grvidal
 */
public class Domino {
    /**
     * Metodo main, desde aqui se le llaman a los metodos correspondientes 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Ajustes ajustes= new Ajustes();
        System.out.println("\t\tBienvenido a appDomino G&G");
        intro();
        Monton todas= new Monton();
        Mano[] jugadores=new Mano[nJugadores()];
        ajustes.setAyuda(Excepciones.introducirBoolean("Quieres utilizar la ayuda?"));
        //meter para jugar vs ia;
        int maxPiezasMano=todas.getNPiezasTotales()-(Ajustes.PIEZAS_MANO*(jugadores.length-1));
        System.out.println("Jugadores humanos: "+jugadores.length);
        for (int i = 0; i < jugadores.length; i++) {
            String aux=("Introduce el nombre del jugador "+(i+1)+": ");
            aux=Excepciones.introducirCadena(aux);
            jugadores[i]=new Mano(aux,maxPiezasMano);
        }
        Juego.visualizarTodasLasPiezas(todas);
        Juego.establecerMano(todas,jugadores);
        System.out.println("\nCada jugador tiene "+jugadores[0].getNPiezas()+" piezas.");
        if(todas.getNPiezasMonton()!=0)
            System.out.println("Y en el monton quedan "+todas.getNPiezasMonton());
        else
            System.out.println("No quedan piezas en el monton");
        Partida partida = new Partida();
        int carro=Juego.seleccionarCarro(jugadores);//editar
        int actual=carro;
        boolean fin;
        System.out.println("--------------------------------------");
        do {//La partida ha empezado
            System.out.println("\n-->Juega: "+jugadores[actual].getNombre());
            //comprobar si puedo seguir
            if (partida.getNumNodos()!=0) {
                if(todas.getNPiezasMonton()>0){//aun puede coger cartas
                    jugadores[actual].setPuedeJugar(true);
                } 
                else {
                    jugadores[actual].setPuedeJugar(comprobar(jugadores[actual],partida));
                }//se comprueba si puede
            }
            System.out.println("El jugador "+jugadores[actual].getNombre()+" puede jugar: "+
                    jugadores[actual].getPuedeJugar());
            Juego.jugada(todas,jugadores[actual],partida,ajustes);
            if(jugadores[actual].getNPiezas()==0){
                 fin=true;
            }
            else {
                fin=!Juego.sePuedeSeguir(jugadores);//falta testeo
                actual= turno(actual,jugadores.length-1);
            }
        } while (!fin);
        Juego.quienHaGanado(jugadores, actual,carro);
        Juego.tablaPuntos(jugadores);
    }
    
    /**
     * 
     * @param jug Jugadores en la partida
     * @param partida Partida en curso
     * @return devuelve FALSE si el jugador no puede jugar ninguna opcion/TRUE si la pieza puede ser colocada
     */
    public static boolean comprobar(Mano jug,Partida partida){//falta testeo
        int n=0;
        while(n<jug.getNPiezas() && !coincidencias(partida,jug.getUnaPieza(n)))
            n++;
        return !(n==jug.getNPiezas());
        
    }
    /**
     * 
     * @param partida Partida actual    
     * @param pieza Pieza a comprobar si tiene coincidencias
     * @return TRUE si la ficha se puede situar en alguno de los extremos del tablero
     */
    public static boolean coincidencias(Partida partida, Pieza pieza){
        return   pieza.getN1()==partida.getPrimera() || pieza.getN1()==partida.getUltima()
                ||pieza.getN2()==partida.getPrimera() || pieza.getN2()==partida.getUltima();
        
    }
    
    /**
     * Se le asigna al jugador que llamase al metodo una pieza del monton de piezas aleatoria
     * @param jug Jugadores en la partida   
     * @param monton Todas las piezas de la partida
     */
    public static void cogerDelMonton(Mano jug,Monton monton){
        int pos=(int) (Math.random()*monton.getNPiezasMonton());
        Pieza pieza= monton.getUnaPiezaMonton(pos);
        jug.setUnaPieza(pieza);
        monton.eliminarPiezaMonton(pieza);
        System.out.println("Cogiendo pieza del monton...");
    }
    
    /**
     * 
     * @param actual Jugador actual
     * @param numJug numero de Jugadores (el primero es 0)
     * @return el numero del siguiente jugador que ha de jugar
     */
    public static  int turno(int actual, int numJug){
        if (actual==numJug) 
            actual=0;
        else 
            actual++;
        return actual;
    }
     /**
      * Confirma el numero de jugadores entre MAX_JUGADORES y MIN_JUGADORES (Declarado en Ajustes).
      * @return El numero de jugadores que van a jugar
      */
    public static int nJugadores(){ 
        Scanner e= new Scanner (System.in);
        String aux;
        int toret;
        do {
            System.out.println("El domino se juega con un minimo de 2 jugadores y un maximo de 4.");
            toret= Excepciones.introducirNumero("Introduce el numero de jugadores: ");
            
        } while (toret>Ajustes.MAX_JUGADORES || toret<Ajustes.MIN_JUGADORES);
        
    return toret;
    }
    
    /**
     * Caracteres que dan la bienvenida al/ los usuari@/s
     */
    public static void intro(){
        System.out.println("*************************************************");
        System.out.println("*Se aceptan donaciones en forma de puntos extra.*");
        System.out.println("*************************************************");
    }
}
