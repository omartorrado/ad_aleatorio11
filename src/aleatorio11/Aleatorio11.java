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

    public static RandomAccessFile raf;
    public static String[] codes ={"p1","p2","p3"};
    public static String[] descricion ={"parafusos","cravos","tachas"};
    public static int[] prices ={3,4,5};
    
    public static void main(String[] args) {
        iniciarRAF("/home/local/DANIELCASTELAO/otorradomiguez/NetBeansProjects/aleatorio11/aleatorio11.txt");
        escribir(3);
        Product productoLeido=leer(2);
        System.out.println(productoLeido.toString());
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
     * Hay que tener en cuenta que si ya existiese el archivo solo sobrescribiria tantos elementos como le indiquemos, si hubiese mas
     * elementos que los escritos seguirian en el archivo
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
        /*
        //No es necesario cerrar el RandomAccesFile para que escriba en el archivo,
        //con lo cual podemos dejarlo abierto y usarlo luego para leer o escribir mas
        //Si lo cerramos tendriamos que volver a instanciarlo en el metodo leer o dará error al estar cerrado
        try {
            raf.close();
        } catch (IOException ex) {
            Logger.getLogger(Aleatorio11.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
    /**
     * leemos del archivo los 3 valores que compondrian un producto
     * @param NumElemento el elemento que queremos leer (1 es el primero)
     * @return Product devuelve el producto creado con los datos que hemos leido
     */
    public static Product leer(int NumElemento){
        String codigo="";
        String descrip="";
        int precio;
        Product producto=new Product();
        try {
            raf.seek(30*(NumElemento-1));
            for(int i=0;i<3;i++){
                codigo+=raf.readChar();            
            }
            for(int j=0;j<10;j++){
                descrip+=raf.readChar();
            }
            precio=raf.readInt();
            producto= new Product(codigo.trim(),descrip.trim(),precio);
        } catch (IOException ex) {
               System.out.println("No se ha podido leer el producto, devuelto producto por defecto");
            }
        return producto;
    }
    
}
