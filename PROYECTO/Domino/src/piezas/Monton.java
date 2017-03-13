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
public class Monton {
    private Piezas[] monton;
    private static final int MAX_NUMERO=6;
    
    public Monton(){
        int nPiezas=0;
        for (int i = 0; i <= 6; i++) {
            nPiezas+=i+1;
        }
        monton=new Piezas[nPiezas];
        int k=0;
        for (int i = 0; i <= MAX_NUMERO; i++) {
            for (int j = 0; j <= MAX_NUMERO; j++) {
                monton[k]= new Piezas(i,j);
            }
        }
    }
}
