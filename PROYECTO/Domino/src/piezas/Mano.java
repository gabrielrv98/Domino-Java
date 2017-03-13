/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piezas;

/**
 *
 * @author reyga
 */
public class Mano {
    private static final int PIEZAS_MANO=7;
    private int nPiezas;
    private Pieza[] piezas;
    
    public Mano(){
        nPiezas=PIEZAS_MANO;
        //que piezas obtienes
    }
    
    public int getNPiezas(){
        return nPiezas;
    }
}
