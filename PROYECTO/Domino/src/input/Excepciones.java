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
    
    /**
     * Metodo para obtener un numero a través de una cadena (control de excepciones)
     * @param aux Cadena que solicita la introducion del numero
     * @return Numero introducido
     */
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
            }catch(Exception exp){
                repetir=true;
            }
            if (repetir)
                System.err.println("Valor no valido.");
            
        } while (repetir);
        return resp;
        
    }
    
    /**
     * Metodo para obtener una cadena del usuario
     * @param aux Cadena que solicita la introducion de la cadena
     * @return La cadena introducida
     * @throws java.lang.Exception Error si hay un error no contemplado
     */
    public static String introducirCadena(String aux) throws Exception{
        Scanner e= new Scanner (System.in);
        String toret;
        boolean repetir=false;
        do{
            System.out.print(aux);
            try{
                 toret= e.nextLine().trim();
                 repetir=false;
            }catch(Exception exp){
                cambiarColorRojo("ERROR OCURRIDO OBTENIENDO CADENA");
                toret="ERROR";
                throw new Exception("Error");
            }
            if(toret.length()==0){
                System.err.println("Cadena no detectada.");
                repetir=true;
            }    
        }while(repetir);
        return toret;
    }
    /**
     * Metodo para obtener un booleano del usuario
     * @param aux Cadena que solicita la introducion de 1 o 2
     * @return YES si se introduce 1, FALSE si se introduce 2
     */
    public static boolean introducirBoolean(String aux){
        boolean toret=false;
        int opcion;
        System.out.println(aux);
        do {
            System.out.println("1.-Sí.\n2.-No.");
            opcion=introducirNumero("Opcion: ");
        } while (opcion>2 || opcion<1);
        switch (opcion){
            case 1:toret=true;
                break;
            case 2: toret=false;
                break;
        }
        return toret;
    }
     /**
      * Metodo para cambiar una cadena a rojo
      * @param aux Cadena que se va a cambiar de color
      */
    public static void  cambiarColorRojo(String aux){
        System.out.println("\033[31m"+aux+"\033[30m");
    }
    
     public static void  cambiarColorAzul(String aux){
        System.out.println("\033[34m"+aux+"\033[30m");
    }
     
     public static void  cambiarColorAzul(String aux,int n1, String aux1){
        System.out.println("\033[34m"+aux+n1+aux1+"\033[30m");
    }
    public static String cambiarColorVerde(String aux){
        return ("\033[32m"+aux+"\033[30m");
    }
     
}
