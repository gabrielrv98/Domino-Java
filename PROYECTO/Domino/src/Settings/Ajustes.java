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
    boolean ayuda;
    public static final int MAX_NUMERO=3;
    
    public Ajustes(){
    }
    
    public int getMAX_NUMERO(){//meter en su respectivo hueco.
        return MAX_NUMERO;
    }
    
    public void setAyuda(boolean yn){
        ayuda=yn;
    }
    public  boolean getAyuda(){
        return ayuda;
    }
}
