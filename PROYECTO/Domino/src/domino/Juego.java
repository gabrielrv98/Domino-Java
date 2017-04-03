/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;

import Settings.Ajustes;
import Tablero.Partida;
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
     * Busca el doble mas alto que tiene algun jugador en su mano
     * @param jug Jugadores actuales con las piezas ya declaradas
     * @return el numero del jugador con el doble mas alto o con un numero aleatorio.
     */
    public static int seleccionarCarro(Mano[] jug){//error no vio la 0,0
        int numeroDoble=6;
        int n=0;
        int toret=0;
        while(numeroDoble>=0){
            while(n<jug.length){
                int f=0;
                while( f<jug[n].getNPiezas() && 
                        (jug[n].getUnaPieza(f).getN1()!=numeroDoble || jug[n].getUnaPieza(f).getN2()!=numeroDoble))
                    f++;
                
                if(f<jug[n].getNPiezas()){
                    System.out.println("Encontrada la "+numeroDoble+","+numeroDoble
                                         +", la tiene el jug "+(n+1)+" y el tiene"
                            + "el primer turno");
                    toret=n;
                    numeroDoble=-10;
                
                }
                n++;
            }
            numeroDoble--;
        }
        if(numeroDoble==-1){
            toret=(int) (Math.random()*jug.length);
            System.out.println("No se ha encontrado ningun jugador con alguna pieza doble.\n"
                    + "Empieza el jugador "+toret);
        }
        return toret;
    }
    
    /**
     * 
     * @param monton todas las piezas y las piezas del monton
     * @param jug Jugadores presentes
     * @param partida Partida en curso
     * @param ajustes Ajustes variables (modo ayuda)
     */
    public static  void jugada(Monton monton,Mano jug, Partida partida, Ajustes ajustes){
        int opcion;
        int maxOpciones;
        int vecesCogidas=0;
        boolean continuar;
        if (monton.getNPiezasMonton()<=0) {
            vecesCogidas=Ajustes.MAX_VECES_COGER;
        }
        do {
            if (ajustes.getAyuda()) 
                System.out.println(jug.ayuda(partida));

            else System.out.println(jug);

            System.out.println(partida);
        
            continuar=true;
            Excepciones.cambiarColorRojo("\t\tElige que hacer:");
            System.out.println("(1-"+jug.getNPiezas()+")-Poner una ficha en el tablero.");
            if( vecesCogidas>=Ajustes.MAX_VECES_COGER 
                    && ajustes.getAyuda() && partida.getNumNodos()>0 && !Domino.comprobar(jug, partida)){
                    System.out.println(Excepciones.cambiarColorVerde((jug.getNPiezas()+1)+".- Pasar"));
                
            }
            else System.out.println((jug.getNPiezas()+1)+".- Pasar");
            maxOpciones=(jug.getNPiezas()+1);
            
            if(vecesCogidas<Ajustes.MAX_VECES_COGER){
                if((ajustes.getAyuda())&& partida.getNumNodos()>0 && !Domino.comprobar(jug, partida)){// (deberia mirar: si se han gastado todas las fichas que contienen algun numero de los que has jugado)
                        System.out.println(Excepciones.cambiarColorVerde((jug.getNPiezas()+2)+".- Coger una ficha del monton."));
                }
                else System.out.println((jug.getNPiezas()+2)+".- Coger una ficha del monton.");
                maxOpciones=(jug.getNPiezas()+2);
            }
            do {
                opcion=Excepciones.introducirNumero("\nOpcion: ");
            } while (opcion>maxOpciones || opcion<=0);
            
            if(0<opcion && opcion<=jug.getNPiezas()){
                
                System.out.println("poniendo ficha...");
                continuar = anhadirFicha(partida, jug.getUnaPieza(opcion-1));//pone una ficha
                if(continuar){
                    jug.eliminarPieza(jug.getUnaPieza(opcion-1));
                }
            }
            else if(opcion==(jug.getNPiezas()+1)){
                Excepciones.cambiarColorRojo("Pasando...");//Pasa turno
            }
            else if(opcion==(jug.getNPiezas()+2)){//Coge una ficha del monton, y vuelve a ofrecer la opcion 1 y 2
                Domino.cogerDelMonton(jug,monton);
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
     * @param partida Partida en curso
     * @param pieza ficha que se va a añadir
     * @return TRUE si la pieza fue asignada, FALSE si la pieza no tenia coincidencias con ninguna del tablero
     */
    public static boolean anhadirFicha( Partida partida,Pieza pieza){
        boolean anhadida=true;
        if(partida.getNumNodos()==0){
            partida.insertarPrincipio(pieza);
        }
        else{//ya hay alguna pieza colocada
            if(coincidencias(partida,pieza)){
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
                            }
                        }
                        else {//la pieza sobre el tablero es doble
                            if(n1==partida.getPrimera() || n2==partida.getPrimera()){
                                if (n1==partida.getPrimera()) {//coincide con el numero de la izq
                                    
                                    if ( ultima()) {
                                        partida.insertarFinal(pieza);
                                    }
                                    else{
                                        pieza.invertirPieza();
                                        partida.insertarPrincipio(pieza);
                                    }
                                }
                                else if(n2==partida.getPrimera()) {//coincide con el numero de la derecha
                                    if (ultima()) {
                                        pieza.invertirPieza();
                                        partida.insertarFinal(pieza);
                                    }
                                    else{
                                        
                                        partida.insertarPrincipio(pieza);
                                    }
                                }
                            }
                        }
                    }
                    //ninguna pieza es doble
                    else if((n1==partida.getUltima() && n2==partida.getPrimera()) 
                            || (n2==partida.getUltima()&& n1==partida.getPrimera())){
                        if(n1==partida.getUltima() && n2==partida.getPrimera()){
                            if (ultima()) {
                                partida.insertarFinal(pieza);
                            }
                            else partida.insertarPrincipio(pieza);
                        }else{
                            pieza.invertirPieza();
                            if (ultima()) {
                                partida.insertarFinal(pieza);
                            }else partida.insertarPrincipio(pieza);
                        }
                            
                    }
                    else{//si se cumple la pieza se puede colocar en algun lugar
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
                    }
                       
            }
            else {
                        System.out.println("La pieza no tiene coincidencias.");
                        anhadida=false;
                    } 
        }
        return anhadida;
    }
    
    public static boolean ultima(){
        int pos;
        System.out.println("Donde quieres colocar la pieza: "
                                + "\n1.-Izquierda."
                                + "\n2.-Derecha.");
        do {
            pos=Excepciones.introducirNumero("Lugar:");
        } while (pos>2||pos<1);
        return pos==2;
        
    }
    
    /**
     * 
     * @param partida Partida actual    
     * @param pieza Pieza a comprobar si tiene coincidencias
     * @return TRUE si la ficha se puede situar en alguno de los extremos del tablero
     */
    public static boolean coincidencias(Partida partida, Pieza pieza){
        return   (pieza.getN1()==partida.getPrimera() || pieza.getN1()==partida.getUltima()
                ||pieza.getN2()==partida.getPrimera() || pieza.getN2()==partida.getUltima());
        
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
        
        return !(n==jugadores.length);
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
