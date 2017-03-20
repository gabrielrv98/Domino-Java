/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

/**
 *
 * @author reyga
 */
public class Ajustes {
    public boolean ayuda;
    public static final int MAX_NUMERO=3;
    public static final int PIEZAS_MANO=7;
    public static final int MAX_JUGADORES=4;
    public static final int MIN_JUGADORES=2;
    public  int carro;
    
    /**
     * establezco el carro en base a 1) n que yo eliga 2)(en proceso) quien tenga el doble 6
     */
    public Ajustes(){
        carro=1;
    }
 
    public void setAyuda(boolean yn){
        ayuda=yn;
    }
    
    public   boolean getAyuda(){
        return ayuda;
    }
    
    public void setCarro(int n){
        carro=n;
    }
    
    public int getCarro(){
        return carro;
    }
}
