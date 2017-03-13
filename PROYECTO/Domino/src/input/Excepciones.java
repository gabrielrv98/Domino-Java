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
                resp=Integer.parseInt(e.nextLine());
                repetir=false;
            }catch(NumberFormatException exp){
              repetir=true;
            }
            if (repetir)
                System.out.println("Valor no valido.");
            
        } while (repetir);
        return resp;
        
    }
}
