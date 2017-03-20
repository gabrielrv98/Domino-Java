/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Pasar Carro a Ajustes
 * Arreglar añadir ficha
 * mostrar tabla puntuaciones ultima linea main(String [] args)
 * falta meter ia
 * falta meter ayuda
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
        //ajustes.setAyuda(Excepciones.introducirBoolean("Quieres utilizar la ayuda?"));
        //meter para jugar vs ia;
        int maxPiezasMano=todas.getNPiezasTotales()-(Ajustes.PIEZAS_MANO*(jugadores.length-1));
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
        int carro=0;
        int actual=carro;
        boolean fin;
        //int turno;
        System.out.println("--------------------------------------");
        do {//La partida ha empezado
            System.out.println("\n-->Juega: "+jugadores[actual].getNombre());
            //comprobar si puedo seguir
            if (partida.getNumNodos()!=0) {
                if(todas.getNPiezasMonton()>0){//aun puede coger cartas
                    Excepciones.cambiarColorRojo("Quedan piezas en el monton");
                    jugadores[actual].setPuedeJugar(true);
                } 
                else {
                    Excepciones.cambiarColorRojo("no quedan piezas, se ejecuta comprobar()");
                    jugadores[actual].setPuedeJugar(comprobar(jugadores[actual],partida));
                }//se comprueba si puede
            }
            System.out.println("El jugador "+jugadores[actual].getNombre()+" puede jugar: "+jugadores[actual].getPuedeJugar());
            jugada(todas,jugadores[actual],partida,ajustes);
            if(jugadores[actual].getNPiezas()==0){
                 fin=true;
            }
            else {
                Excepciones.cambiarColorRojo("Ejecutando sePuedeSeguir");
                fin=!sePuedeSeguir(jugadores);//falta testeo
                actual= turno(actual,jugadores.length-1);
            }
        } while (!fin);
        quienHaGanado(jugadores, actual,carro);
        System.out.println("Esto deberia ser negro-");
        //mostrar Tabla de fichas y puntos.
    }
    
    /**
     * Primero mira si al ultimo jugador le quedaban fichas y luego  quien tiene menor puntos
     * @param jugadores Los jugadores de la partida
     * @param actual El ultimo jugador que ha jugado
     * @param carro El jugador que inicia la partida, y tiene ventaja a la hora de la puntuacion
     * @return La posicion del ganador en el array de jugadores/-1 en caso de que haya dos jugadores con la misma puntuacion y ninguno sea carro.
     */
    public  static void quienHaGanado(Mano[] jugadores, int actual,int carro){
        if(jugadores[actual].getNPiezas()!=0){
            boolean dosIguales=false;
            actual=0;
            for (int i = 1; i < jugadores.length; i++) {
                if(jugadores[i].getPuntuacion()==jugadores[actual].getPuntuacion()){//El jug i y el actual tienen la misma putnuacion
                    System.out.println("las puntuaciones son iguales");
                    if(i==carro){//el jugador i es el carro
                        System.out.println("el jugador i es el carro");
                         actual=i;
                         dosIguales=false;
                    }
                       
                    else if(actual==carro){//el jugador actual es el carro
                        System.out.println(" el jugador actual es el carro");
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
                System.out.println("\u001B[34mY los GANADORES SON....");
                for (int i = 0; i < jugadores.length; i++) {
                    if (jugadores[i].getPuntuacion()==jugadores[actual].getPuntuacion()) {
                        System.out.println("\t\t\u001B[34mEl jugador nº- "+(i+1)+": "+jugadores[i].getNombre());
                    }
                }
                System.out.println("\u001B[30m");
                actual=-1;
            }
            else{
                System.out.println("\u001B[34mY el GANADOR ES....");
                System.out.println("\t\tEl jugador nº- "+(actual+1)+": "+jugadores[actual].getNombre()+"\u001B[30m");
            }
        }
        else {
            System.out.println("\u001B[34mY el GANADOR ES....");
                System.out.println("\t\tEl jugador nº- "+(actual+1)+": "+jugadores[actual].getNombre()+"\u001B[30m");
        }
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
                continuar = anhadirFicha(jug,partida,ajustes, opcion);//pone una ficha
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
            else System.out.println("no entro en ninguno");
        } while (!continuar);
            System.out.println("Turno para el siguiente");
    }
    
    public static boolean comprobar(Mano jug,Partida partida){//devuelve false si no puede//error
        int n=0;
        boolean toret;
        int n1=partida.getPrimera();
        int n2=partida.getUltima();
        while(n<jug.getNPiezas() && (jug.getUnaPieza(n).getN1()!=n1 && jug.getUnaPieza(n).getN1()!=n2 
                && jug.getUnaPieza(n).getN2()!=n1 && jug.getUnaPieza(n).getN2()!=n2))
            n++;
        if (n==jug.getNPiezas()){
            toret=false;
            System.out.println("n==ultima pieza, no podria jugar");
        }
            
        else{
            toret=true;
            System.out.println(jug.getUnaPieza(n));
        }
        return toret;
    }
    
    public static void cogerDelMonton(Mano jug,Monton monton){
        int pos=(int) (Math.random()*monton.getNPiezasMonton());
        Pieza pieza= monton.getUnaPiezaMonton(pos);
        jug.setUnaPieza(pieza);
        monton.eliminarPiezaMonton(pieza);
    }
    
    public static boolean anhadirFicha(Mano jug, Partida partida, Ajustes ajustes,int ficha){
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
}
