/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
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
        int maxPiezasMano=todas.getNPiezasTotales()-(Ajustes.PIEZAS_MANO*(jugadores.length-1));
        ajustes.setAyuda(Excepciones.introducirBoolean("Quieres utilizar la ayuda?"));//Inicializar el modo ayuda
        int IAs=establecerIA(jugadores);//Declara el numero de IAs
        establecerJugadores(jugadores, IAs, maxPiezasMano);//Establece los jugadores con sus nombres
        Juego.visualizarTodasLasPiezas(todas);//Visualiza las piezas
        Juego.establecerMano(todas,jugadores);//Establece las manos a todos los jugadores
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
    
    public static void establecerJugadores(Mano[] jug, int IAs, int maxPiezasMano) throws Exception{
        String aux;
        for (int i = 0; i < (jug.length-IAs); i++) {
            aux=("Introduce el nombre del jugador "+(i+1)+": ");
            aux=Excepciones.introducirCadena(aux);
            jug[i]=new Mano(aux,maxPiezasMano);
        }
        for (int i = (jug.length-IAs); i < jug.length; i++) {
            aux=("IA "+(i+1));
            jug[i]=new Mano(aux,maxPiezasMano);
            jug[i].setIA(true);
        }
    }
    
    public static int establecerIA(Mano [] jug){
        int op;
        System.out.println("De los "+jug.length+" jugadores cuantos quieres qu sean IA?");
        do {
            op=input.Excepciones.introducirNumero("-> ");
        } while (op>jug.length-1 || op<0);
        switch (op) {
            case 0:
                System.out.println("No habra nin jugador como IA");
                break;
            case 1:
                System.out.println("El ultimo jugador será la IA");
                break;
            default:
                System.out.println("Los "+op+" ultimos jugadores serán IA");
                break;
        }
        return op;
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
