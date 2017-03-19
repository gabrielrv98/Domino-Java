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
    public  int carro;
    
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
