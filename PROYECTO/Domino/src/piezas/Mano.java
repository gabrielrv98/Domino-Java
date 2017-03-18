/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piezas;
import  Tablero.*;
/**
 *
 * @author reyga
 */
public class Mano {
    private static final int PIEZAS_MANO=7;
    private int nPiezas;
    private Pieza[] piezas;
    private String nombre;
    private boolean puedeJugar;
    /**
     * 
     * @param name nombre del jugador
     * @param maxPiezasMano editar esto.!
     */
    public Mano(String name, int maxPiezasMano){
        nombre=name;
        //piezas= new Pieza[(maxPiezasMano+2)];///editar, la cuenta esta mal echa en relacion como se reparten las piezas
        piezas= new Pieza[(50)];
        nPiezas=0;
        puedeJugar=true;
    }
    
    /**
     *  
     * @param yn si hay piezas en el monton es true, sino mira si hay coincidencias con el tablero
     */
    public void setPuedeJugar(boolean yn){
        puedeJugar=yn;
    }
    
    /**
     *  devuelve el valor de la variable puedeJugar
     * @return booleano (true si puede jugar)
     */
    public boolean getPuedeJugar(){
        return puedeJugar;
    }
    /**
     * Se le añade a la mano del jugador la pieza pasada por el parametro
     * @param pieza una pieza del monton 
     */
    public void setUnaPieza(Pieza pieza){
        piezas[nPiezas]=pieza;
        nPiezas++;
    }
    /**
     * 
     * @return el numero de piezas con las que se inicializa
     * @deprecated
     */
    public int getPIEZAS_MANO(){   
        return PIEZAS_MANO;
    }
    
    /**
     * 
     * @param n la posicion de la pieza que se quiere obtener
     * @return la pieza que esta en la poscion n
     */
    public Pieza getUnaPieza(int n){
        return piezas[n];
    }
    
    /**
     * suma ambos lados de todas las piezas y las devuelve
     * @return la puntuacion del jugador
     */
    public int getPuntuacion(){
        int toret=0;
        for (int i = 0; i < nPiezas; i++) {
            toret+=piezas[i].getN1()+piezas[i].getN2();
        }
        return toret;
    }
    /**
     * 
     * @return numero de piezas que el jugador tiene en la mano
     */
    
    public int getNPiezas(){
        return nPiezas;
    }
    public String getNombre(){
        return nombre;
    }
    public void eliminarPieza(Pieza pieza){
        int n=0;
        while(n<nPiezas && piezas[n]!=pieza)
            n++;
        if(n==nPiezas)
            System.err.println("HUBO UN ERROR, PIEZA NO ESTA EN LA MANO DE ESTE JUGADOR");
        else{
            for (int i = n; i < nPiezas-1; i++) {
                piezas[i]=piezas[i+1];
            }
            nPiezas--;
        }
    }
    
    public String toString(){
        StringBuilder toret= new StringBuilder();
        for (int i = 0; i < nPiezas; i++) {
            toret.append(i+1);
            toret.append(piezas[i]);
            toret.append("\n");
        }
        return toret.toString();
    }
    public String toString(boolean[] puedeJugar){
       StringBuilder toret= new StringBuilder();
        for (int i = 0; i < nPiezas; i++) {
            if(puedeJugar[i]){
                toret.append("\033[32m");
                toret.append(i+1);
                toret.append(piezas[i]);
                toret.append("\033[30m");
                toret.append("\n");
            }else{
                toret.append(i+1);
            toret.append(piezas[i]);
            toret.append("\n");
            }
            
        }
        return toret.toString();
    }
    
    public String ayuda(Partida partida){
        StringBuilder toret= new StringBuilder();
        for (int i = 0; i < nPiezas; i++) {
            //si la pieza[i] tiene coincidencias con getPtincipio() o Partida.getFinal() ponerla en amarillo.
        }
        return toret.toString();
    }

    
}
