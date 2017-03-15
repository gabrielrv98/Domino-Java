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
import Settings.Ajustes;
/**
 *
 * @author grvidal
 */
public class Domino {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ajustes ajustes= new Ajustes();
        System.out.println("\t\tBienvenido a appDomino G&G");
        intro();
        Monton todas= new Monton();
        Mano[] jugadores=new Mano[nJugadores()];
        ajustes.setAyuda(Excepciones.introducirBoolean("Quieres utilizar la ayuda?"));
        //meter para jugar vs ia;
        int maxPiezasMano=todas.getNPiezasTotales()/jugadores.length;
        System.out.println("Jugadores humanos: "+jugadores.length);
        for (int i = 0; i < jugadores.length; i++) {
            String aux=("Introduce el nombre del jugador "+(i+1)+": ");
            aux=Excepciones.introducirCadena(aux);
            jugadores[i]=new Mano(aux,maxPiezasMano);
        }
        visualizarTodasLasPiezas(todas);
        establecerMano(todas,jugadores);
        System.out.println("\nCada jugador tiene "+jugadores[0].getNPiezas()+" piezas.");
        if(todas.getNPiezasMonton()!=0)
            System.out.println("Y en el monton quedan "+todas.getNPiezasMonton());
        else
            System.out.println("No quedan piezas en el monton");
        Partida partida = new Partida();
        int carro=1;
        int actual=carro;
        boolean fin=false;
        int turno;
        System.out.println("--------------------------------------");
        do {
            System.out.println("\n-->Juega: "+jugadores[actual].getNombre());
            //comprobar si puedo seguir
            if (partida.getNumNodos()!=0) {
                if(todas.getNPiezasMonton()>0){//aun puede coger cartas
                    fin=false;
                    System.out.println("Quedan cartas");
                } 
                else jugadores[actual].setPuedeJugar(comprobar(jugadores[actual],partida));//se comprueba si puede
            }
            System.out.println("El jugador "+jugadores[actual].getNombre()+" puede jugar: "+!fin);
            jugada(todas,jugadores[actual],partida,ajustes);
            System.out.println(partida);
            if(jugadores[actual].getNPiezas()==0){
                 System.out.println("El Jugador "+jugadores[actual].getNombre()+ " ha ganado, gg.");//ha Ganado.
                 fin=true;
            }
            fin=sePuedeSeguir(jugadores);//falta testeo
            actual= turno(actual,jugadores.length-1);
        } while (!fin);
    }
    
    public static boolean sePuedeSeguir(Mano [] jugadores){//falta testeo
        boolean toret=false;
        int n=0;
        while(n<jugadores.length && !jugadores[n].getPuedeJugar())
            n++;
        if(n==jugadores.length)
            toret=true;
        return toret;
    }
    
    public static  void jugada(Monton monton,Mano jug, Partida partida, Ajustes ajustes){
        System.out.println(jug);
        final int MAX_VECES_COGER =1;
        int opcion;
        int maxOpciones=2;
        int vecesCogidas=0;
        boolean continuar;
        do {
            continuar=true;
            System.out.println("\t\tEligue que hacer:"
                        + "\n1.- Poner una ficha en el tablero."
                        + "\n2.- Pasar");
            if(monton.getNPiezasMonton()>0 && vecesCogidas<MAX_VECES_COGER){
                System.out.println("3.- Coger una ficha del monton.");
                maxOpciones=3;
            }
            do {
                opcion=Excepciones.introducirNumero("\nOpcion: ");
            } while (opcion>maxOpciones || opcion<0);
            switch (opcion){
                case 1: continuar = anhadirFicha(jug,partida,ajustes);
                        break;
                case 2:
                    break;
                case 3: cogerDelMonton(jug,monton);
                        System.out.println(jug);
                        vecesCogidas++;
                        continuar=false;
                    break;
            }
        } while (!continuar);
            System.out.println("Turno para el siguiente");
    }
    
    public static boolean comprobar(Mano jug,Partida partida){//devuelve false si no puede
        int n=0;
        boolean toret=false;
        int n1=partida.getPrimera();
        int n2=partida.getUltima();
        while(n<jug.getNPiezas() && (jug.getUnaPieza(n).getN1()!=n1 && jug.getUnaPieza(n).getN1()!=n2 
                && jug.getUnaPieza(n).getN2()!=n1 && jug.getUnaPieza(n).getN2()!=n2))
            n++;
        if (n==jug.getNPiezas())
            toret=true;
        return toret;
    }
    
    public static void cogerDelMonton(Mano jug,Monton monton){
        int pos=(int) (Math.random()*monton.getNPiezasMonton());
        Pieza pieza= monton.getUnaPiezaMonton(pos);
        jug.setUnaPieza(pieza);
        monton.eliminarPiezaMonton(pieza);
    }
    
    public static boolean anhadirFicha(Mano jug, Partida partida, Ajustes ajustes){
        if(ajustes.getAyuda()){
            //debuelbe en un array con true las cartas que se pueden jugar
        }else System.out.println(jug);
            
        
        int opcion;
        System.out.println(partida);
        boolean anhadida=true;
        do{
            opcion=Excepciones.introducirNumero("Que pieza deseas jugar: ");
        }while(opcion>jug.getNPiezas()||opcion<1);
        Pieza pieza=jug.getUnaPieza(opcion-1);//tengo la pieza selecionada
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
                                System.out.println("No ha sido posible insertar la pieza");
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
                        if(n1==partida.getPrimera() || n1== partida.getUltima()){
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
                        System.out.println("No ha sido posible introducir la pieza.");
                        anhadida=false;
                    }       
        }
        return anhadida;
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
    
    public static void   establecerMano(Monton todas,Mano[] jugadores){//otro bug
        int pos;
        Pieza pieza;
        for (int i = 0; i < jugadores.length; i++) {
            for (int j = 0; j < jugadores[i].getPIEZAS_MANO(); j++) {
                pos=(int) (Math.random()*todas.getNPiezasMonton());
                pieza=todas.getUnaPiezaMonton(pos);
                jugadores[i].setUnaPieza(pieza);
                todas.eliminarPiezaMonton(pieza);
            }
        }
    }
}
