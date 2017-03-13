/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;
import java.util.Scanner;

/**
 *
 * @author grvidal
 */
public class Excepciones {
    
    public static int introducirNumero(String aux){
        Scanner e= new Scanner (System.in);
        int resp=0;
        boolean repetir;
        do {
            System.out.print(aux);
            try{
                resp=Integer.parseInt(e.nextLine().trim());
                repetir=false;
            }catch(NumberFormatException exp){
              repetir=true;
            }
            if (repetir)
                System.err.println("Valor no valido.");
            
        } while (repetir);
        return resp;
        
    }
    
    public static String introducirCadena(String aux){
        Scanner e= new Scanner (System.in);
        String toret;
        boolean repetir=false;
        do{
            System.out.print(aux);
            try{
                 toret= e.nextLine().trim();
                 repetir=false;
            }catch(Exception exp){
                System.err.println("ERROR OCURRIDO OBTENIENDO CADENA");
                toret="ERROR";
            }
            if(toret.length()==0){
                System.err.println("Cadena no detectada.");
                repetir=true;
            }
                
        }while(repetir);
        
       
        return toret;
        
    }
}
