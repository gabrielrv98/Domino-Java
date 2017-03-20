/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;

import Settings.Ajustes;
import Tablero.Partida;
import static domino.Domino.cogerDelMonton;
import input.Excepciones;
import piezas.*;

/**
 *
 * @author OrenadorOmega
 */
public class Juego {
    
    /**
     *  Se visualizan todas las piezas
     * @param todas  Todas las piezas con las que se van a jugar
     */
    public static void visualizarTodasLasPiezas(Monton todas){
        
            System.out.println("\nVisualizando todas las piezas: ");
            System.out.println(todas);
    }
    /**
     * Se le asignan un numero de piezas a todos los jugadores
     * @param todas Principalmente las piezas del monton
     * @param jugadores Jugadores presentes en la partida
     */
    public static void   establecerMano(Monton todas,Mano[] jugadores){
        int pos;
        Pieza pieza;
        for (int i = 0; i < jugadores.length; i++) {
            for (int j = 0; j < Ajustes.PIEZAS_MANO; j++) {
                if(todas.getNPiezasMonton()>0){
                   pos=(int) (Math.random()*todas.getNPiezasMonton());
                    pieza=todas.getUnaPiezaMonton(pos);
                    jugadores[i].setUnaPieza(pieza);
                    todas.eliminarPiezaMonton(pieza); 
                }
                
            }
        }
    }
    
    /**
     * 
     * @param monton todas las piezas y las piezas del monton
     * @param jug Jugadores presentes
     * @param partida Partida en curso
     * @param ajustes Ajustes variables (modo ayuda)
     */
    public static  void jugada(Monton monton,Mano jug, Partida partida, Ajustes ajustes){
        if (ajustes.getAyuda()) 
            System.out.println(jug.ayuda(partida));
        else
            System.out.println(jug);
        System.out.println(partida);
        final int MAX_VECES_COGER =1;
        int opcion;
        int maxOpciones;
        int vecesCogidas=0;
        boolean continuar;
        do {
            continuar=true;
            Excepciones.cambiarColorRojo("\t\tEligue que hacer:");
            System.out.println("(1-"+jug.getNPiezas()+")-Poner una ficha en el tablero.");
            System.out.println((jug.getNPiezas()+1)+".- Pasar");
            maxOpciones=(jug.getNPiezas()+1);
            if(monton.getNPiezasMonton()>0 && vecesCogidas<MAX_VECES_COGER){
                System.out.println((jug.getNPiezas()+2)+".- Coger una ficha del monton.");
                maxOpciones=(jug.getNPiezas()+2);
            }
            do {
                opcion=Excepciones.introducirNumero("\nOpcion: ");
            } while (opcion>maxOpciones || opcion<=0);
            
            if(0<opcion && opcion<=jug.getNPiezas()){
                
                System.out.println("poniendo ficha...");
                continuar = anhadirFicha(jug,partida, opcion);//pone una ficha
            }
            else if(opcion==(jug.getNPiezas()+1)){
                Excepciones.cambiarColorRojo("Pasando...");//Pasa turno
            }
            else if(opcion==(jug.getNPiezas()+2)){//Coge una ficha del monton, y vuelve a ofrecer la opcion 1 y 2
                cogerDelMonton(jug,monton);
                        System.out.println(jug);
                        System.out.println(partida);
                        System.out.println("has cogido del monton");
                        vecesCogidas++;
                        continuar=false;
            }
            else Excepciones.cambiarColorRojo("no entro en ninguno, avisar al administrador");
        } while (!continuar);
            System.out.println("Turno para el siguiente");
    }
    
    /**
     * 
     * @param jug Jugadores en la partida   
     * @param partida Partida en curso
     * @param ficha ficha que se va a añadir
     * @return TRUE si la pieza fue asignada, FALSE si la pieza no tenia coincidencias con ninguna del tablero
     */
    public static boolean anhadirFicha(Mano jug, Partida partida,int ficha){
        boolean anhadida=true;
    
        Pieza pieza=jug.getUnaPieza(ficha-1);//tengo la pieza selecionada
        if(partida.getNumNodos()==0){
            partida.insertarPrincipio(pieza);
            jug.eliminarPieza(pieza);
        }
        else{//ya hay alguna pieza colocada
                    int n1=pieza.getN1();
                    int n2=pieza.getN2();//valores de las piezas seleccionadas del jugador
                    if(n1==n2 || partida.getPrimera()==partida.getUltima()){//alguna pieza es dolbe
                        
                        if(n1==n2){//es una pieza doble
                            if(n1!=partida.getPrimera() && n1!= partida.getUltima()){
                                System.out.println("La pieza no tiene coincidencias.");
                                anhadida=false;
                            }
                            else{
                                if(n1==partida.getPrimera()){
                                    partida.insertarPrincipio(pieza);
                                }
                                else partida.insertarFinal(pieza);
                                jug.eliminarPieza(pieza);
                            }
                        }
                        else {//la pieza sobre el tablero es doble
                            if(n1==partida.getPrimera() || n2==partida.getPrimera()){
                                if (n1==partida.getPrimera()) {//coincide con el numero de la izq
                                    int pos;
                                    System.out.println("Donde quieres colocar la pieza: "
                                            + "\n1.-Izquierda."
                                            + "\n2.-Derecha.");
                                    do {
                                        pos=Excepciones.introducirNumero("Lugar:");
                                    } while (pos>2||pos<1);
                                    if(pos==1){
                                        pieza.invertirPieza();
                                        partida.insertarPrincipio(pieza);
                                    }
                                    else partida.insertarFinal(pieza);
                                }
                                else if(n2==partida.getPrimera()) {//coincide con el numero de la derecha
                                    int pos;
                                    System.out.println("Donde quieres colocar la pieza: "
                                            + "\n1.-Izquierda."
                                            + "\n2.-Derecha.");
                                    do {
                                        pos=Excepciones.introducirNumero("Lugar:");
                                    } while (pos>2||pos<1);
                                    if(pos==2){
                                        pieza.invertirPieza();
                                        partida.insertarFinal(pieza);
                                    }
                                    else partida.insertarPrincipio(pieza);
                                }
                                jug.eliminarPieza(pieza);
                            }
                            else{
                                System.out.println("La pieza no tiene coincidencias.");
                                anhadida=false;
                            }
                                
                        }
                    }
                    //ninguna pieza es doble
                    else if (n1==partida.getPrimera() || n1== partida.getUltima()
                            || n2==partida.getPrimera() || n2== partida.getUltima() ){//si se cumple la pieza se puede colocar en algun lugar
                        if(n1==partida.getPrimera() || n1== partida.getUltima()){//n1 a1 / n2 a2 y n1 a2 n2 a1
                            if(n1==partida.getPrimera()){
                                pieza.invertirPieza();
                                partida.insertarPrincipio(pieza);
                            }
                            else partida.insertarFinal(pieza);
                        }
                        else{
                            if(n2==partida.getUltima()){
                                pieza.invertirPieza();
                                partida.insertarFinal(pieza);
                            }
                            else partida.insertarPrincipio(pieza);
                        }
                        jug.eliminarPieza(pieza);
                    }
                    else {
                        System.out.println("La pieza no tiene coincidencias.");
                        anhadida=false;
                    }       
        }
        return anhadida;
    }
    /** 
     * Analiza la variable puedeJugar de cada jugador
     * @param jugadores Jugadores de la partida
     * @return  Devulve TRUE si hay un jugadore que su variable puedeJugar es true
     *
     */   
    public static boolean sePuedeSeguir(Mano [] jugadores){
        boolean toret;
        int n=0;
        while(n<jugadores.length && !jugadores[n].getPuedeJugar())
            n++;
        if(n==jugadores.length){
            toret=false;
        }
            
        else{
            toret=true;
        }
        return toret;
    }
    
    /**
     * Primero mira si al ultimo jugador le quedaban fichas y luego  quien tiene menor puntos
     * @param jugadores Los jugadores de la partida
     * @param actual El ultimo jugador que ha jugado
     * @param carro El jugador que inicia la partida, y tiene ventaja a la hora de la puntuacion
     */
    public  static void quienHaGanado(Mano[] jugadores, int actual,int carro){
        if(jugadores[actual].getNPiezas()!=0){
            boolean dosIguales=false;
            actual=0;
            for (int i = 1; i < jugadores.length; i++) {
                if(jugadores[i].getPuntuacion()==jugadores[actual].getPuntuacion()){//El jug i y el actual tienen la misma putnuacion
                    if(i==carro){//el jugador i es el carro
                         actual=i;
                         dosIguales=false;
                    }
                    else if(actual==carro){//el jugador actual es el carro
                        dosIguales=false;
                    }
                    else{//ninguno es el carro y hay que acudir a un metodo para nombrar a los dos ganadores.
                        dosIguales=true;
                    }
                }
                if(jugadores[i].getPuntuacion()<jugadores[actual].getPuntuacion()){//el jugador i tiene menor puntuacion que el jugador actual
                    actual=i;
                    dosIguales=false;
                }
            }
            if(dosIguales){
                System.out.println("pene");
                Excepciones.cambiarColorAzul("Y los GANADORES SON....");
                for (int i = 0; i < jugadores.length; i++) {
                    if (jugadores[i].getPuntuacion()==jugadores[actual].getPuntuacion()) {
                        //System.out.println("\t\t\u001B[34mEl jugador nº- "+(i+1)+": "+jugadores[i].getNombre());
                        Excepciones.cambiarColorAzul("\t\tEl jugador nº- "+(i+1)+": "+jugadores[i].getNombre());
                    }
                }
                System.out.println("\u001B[30m");
            }
            else{
                Excepciones.cambiarColorAzul("Y el GANADOR ES....");
                //System.out.println("\t\t\u001B[34mEl jugador nº- "+(actual+1)+": "+jugadores[actual].getNombre()+"\u001B[30m");
                Excepciones.cambiarColorAzul("\t\tEl jugador nº- "+(actual+1)+": "+jugadores[actual].getNombre());
            }
        }
        else {
            Excepciones.cambiarColorAzul("Y el GANADOR ES....");
            //System.out.println("\t\t\u001B[34mEl jugador nº- "+(actual+1)+": "+jugadores[actual].getNombre()+"\u001B[30m");
            Excepciones.cambiarColorAzul("\t\tEl jugador nº- "+(actual+1)+": "+jugadores[actual].getNombre());
        }
    }
      /**
     * Muestra todos los jugadores junto con sus fichas y sus puntos
     * @param jug Jugadores en la partida
     */
    public static void tablaPuntos(Mano[] jug){
        StringBuilder toret= new StringBuilder();
        for (int i = 0; i < jug.length; i++) {
            toret.append(jug[i].getNombre());
            toret.append(": \n");
            toret.append(jug[i]);
            toret.append("\nPuntos: ");
            toret.append(jug[i].getPuntuacion());
            toret.append("\n");
        }
        System.out.println(toret.toString());
    }
}
