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
<<<<<<< HEAD
    private String nombre;
    private boolean puedeJugar;
=======
    private final String nombre;
>>>>>>> origin/master
    
    public Mano(String name){
        nombre=name;
        piezas= new Pieza[PIEZAS_MANO];
        nPiezas=0;
        puedeJugar=true;
        //que piezas obtienes
    }
    
    public void setPuedeJugar(boolean yn){
        puedeJugar=yn;
    }
    
    public void setUnaPieza(Pieza pieza){
        piezas[nPiezas]=pieza;
        nPiezas++;
    }
    
    public int getPIEZAS_MANO(){   
        return PIEZAS_MANO;
    }
    
    public Pieza getUnaPieza(int n){
        return piezas[n];
    }
    
    public int getNPiezas(){
        return nPiezas;
    }
    public String getNombre(){
        return nombre;
    }
    
    public Pieza getPieza(int p){
        return piezas[p];
    }
    public int SumaPuntos(){
        int suma=0;
        for (int i = 0; i < nPiezas; i++) {
            suma+=piezas[i].getN1();
            suma+=piezas[i].getN2();
        }
        
        return suma;
    }
    
    @Override
    public String toString(){
        StringBuilder toret= new StringBuilder();
        for (int i = 0; i < nPiezas; i++) {
            toret.append(i+1);
            toret.append(piezas[i]);
            toret.append("\n");
        }
        return toret.toString();
    }

    
}
