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
    private String nombre;
    private boolean puedeJugar;
    
    public Mano(String name, int maxPiezasMano){
        nombre=name;
        //piezas= new Pieza[(maxPiezasMano+2)];///editar, la cuenta esta mal echa en relacion como se reparten las piezas
        piezas= new Pieza[(50)];
        nPiezas=0;
        puedeJugar=true;
    }
    
    public void setPuedeJugar(boolean yn){
        puedeJugar=yn;
    }
    
    public boolean getPuedeJugar(){
        return puedeJugar;
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

    
}
