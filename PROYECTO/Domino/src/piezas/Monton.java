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
    private static final int MAX_NUMERO=6;
    private int nPiezasTotales;
    private Pieza[] todas;
    private Pieza[] monton;
    
    public Monton(){
        int nPiezasTotales=0;
        for (int i = 0; i < MAX_NUMERO+1; i++) {
            nPiezasTotales+=i+1;
        }
        this.nPiezasTotales=nPiezasTotales;//se han obtenido el numero de piezas totales.
        todas=new Pieza[nPiezasTotales];

        int k=0;
    
        for (int i = 0; i < MAX_NUMERO+1; i++) {
            for (int j = i; j < MAX_NUMERO+1; j++) {
                todas[k]= new Pieza(i,j);
                k++;
            }
        }//Se han creado todas las piezas y asignado a una posicion del array monton.
        //System.out.println(todas[todas.length-1]+" nPiezasTotales: "+(nPiezasTotales));
        
    }
    
    public int getPiezasTotales(){
        return todas.length;
    }
    
    public String toString(){
        String aux;
        StringBuilder toret= new StringBuilder();
        int j=0,k=MAX_NUMERO+1;
        for (int i = 0; i < nPiezasTotales; i++) {
            if(j==k){
                toret.append("\n");
                k-=1;
                j=0;
            }
            toret.append(todas[i].toString());
            toret.append(" ");
            j++;
        }
        return toret.toString();
    }
}
