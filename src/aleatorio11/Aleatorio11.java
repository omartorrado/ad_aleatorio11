/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aleatorio11;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author otorradomiguez
 */
public class Aleatorio11 {

    /**
     * @param args the command line arguments
     */
    public static RandomAccessFile raf;
    public static String[] codes ={"p1","p2","p3"};
    public static String[] descricion ={"parafusos","cravos","tachas"};
    public static int[] prices ={3,4,5};
    
    public static void main(String[] args) {
        iniciarRAF("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/aleatorio11/aleatorio11.txt");
        escribir(3);
        leer(2);
    }
    
    public static void iniciarRAF(String ruta){
        try {
            raf = new RandomAccessFile(ruta,"rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Aleatorio11.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Escribimos en el archivo los valores de las tres variables que forman un productos por tantos productos como indicados
     * @param elementos numero de productos que leeremos de los arrays y escribiremos en el archivo
     */
    public static void escribir(int elementos){
        for (int i=0;i<elementos;i++){
            String formatCod=String.format("%-"+"3"+"s", codes[i]);//.replace(" ", "0");
            String formatDes=String.format("%-"+"10"+"s", descricion[i]);
            try {
                raf.writeChars(formatCod);
                raf.writeChars(formatDes);
                raf.writeInt(prices[i]);
            } catch (IOException ex) {
                Logger.getLogger(Aleatorio11.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        try {
            raf.close();
        } catch (IOException ex) {
            Logger.getLogger(Aleatorio11.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * leemos del archivo los 3 valores que compondrian un producto
     * @param NumElemento el elemento que queremos leer (1 es el primero)
     */
    public static void leer(int NumElemento){
        String codigo="";
        String descrip="";
        int precio;
        iniciarRAF("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/aleatorio11/aleatorio11.txt");
        try {
            raf.seek(30*(NumElemento-1));
            for(int i=0;i<3;i++){            
                codigo+=raf.readChar();            
            }
            for(int j=0;j<10;j++){
                descrip+=raf.readChar();
            }
            precio=raf.readInt();
            System.out.println(codigo+", "+descrip+", "+precio);
        } catch (IOException ex) {
                Logger.getLogger(Aleatorio11.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
