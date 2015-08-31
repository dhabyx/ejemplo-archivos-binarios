/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturabinaria;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public final class LecturaBinaria {
    
    private final String archivoEntrada = "entrada.data";
    private final String archivoSalida = "salida.data";
    
    private final ArrayList<Producto> productos;
    
    /**
     * Lectura secuencial de archivos binarios
     * preparación
     */
    public void lecturaSecuencial(){
        try {
            File archivo = new File(archivoEntrada);
            FileInputStream lectorArchivo = new FileInputStream(archivo);
            DataInputStream datos;
            datos = new DataInputStream(lectorArchivo);
                
            
            for (int i=0; i< archivo.length(); i++){
                byte b;
                b = datos.readByte();
                System.out.print(Integer.toHexString(b)+' ');
            }
            

//            while (datos.available()>0){
//                byte b;
//                b = datos.readByte();
//                System.out.print(Integer.toHexString(b)+' ');
//            }
            
            datos.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    /**
     * Escritura secuencial de archivos binarios
     */
    public void escrituraSecuencial(){
        try {
            File archivo = new File(archivoSalida);
            FileOutputStream escritorArchivo = new FileOutputStream(archivo);
            
            DataOutputStream datos = new DataOutputStream(escritorArchivo);
            
            byte [] info = new byte[5];
            info[0] = 0x48;
            info[1] = 0x6f;
            info[2] = 0x6c;
            info[3] = 0x61;
            info[4] = 0x0A;
            
            datos.write(info);
            datos.write(info);
            
            datos.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }
    
    /**
     * Método usado para insertar datos a la lista de Productos
     */
    public void insertarProductos() {
        productos.add(new Producto("Cereal", 35.0f));
        productos.add(new Producto("Leche", 10.0f));
        productos.add(new Producto("Manzanas", 15.0f));
        productos.add(new Producto("Peras", 20.0f));
    }
    
    /**
     * Método que ingresa la información de la estructura de datos
     * a un archivo
     */
    public void guardarEstructura(){
        try {
            File archivo = new File(archivoSalida);
            FileOutputStream escritorArchivo = new FileOutputStream(archivo);
            DataOutputStream datos = new DataOutputStream(escritorArchivo);
            for (Producto p: productos) {
                
                datos.writeByte(p.getNombre().getBytes().length);
                //datos.write(p.getNombre().getBytes().length);
                
                datos.write(p.getNombre().getBytes());
                //datos.writeChars(p.getNombre());
                
                datos.writeFloat(p.getPrecio());
            }
            /*
            datos.writeByte('b');
            datos.writeByte(64);
            datos.writeByte('s');
            datos.writeShort((short)2);
            datos.writeByte('i');
            datos.writeInt((int)32000);
            datos.writeByte('l');
            datos.writeLong(20000000l);
            */
            datos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que obtiene la lista de productos de un archivo, siguiendo
     * la estructura de productos
     */
    public void leerProductos(){
        try {
            File archivo = new File(archivoSalida);
            FileInputStream lectorArchivo = new FileInputStream(archivo);
            DataInputStream datos;
            datos = new DataInputStream(lectorArchivo);
            
            String producto;
            Float precio;
            
            while (datos.available()>0){
                int longitudCadena = datos.readByte();
                
                byte[] cadena;
                cadena = new byte[longitudCadena];
                datos.read(cadena,0,longitudCadena);
                
                producto = new String(cadena);
                
                precio = datos.readFloat();
                
                System.out.println("Producto: "+producto+" Precio: "+precio);
            }
            
            datos.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que lee un archivo mediante acceso aleatorio, se le debe de 
     * pasar un parámetro indicando el número de registro a obtener; los 
     * registros siempre empiezan de 0.
     * @param numDato número de dato
     */
    public void leerAleatoriamente(int numDato) {
        try {
            
            RandomAccessFile archivo = new RandomAccessFile(archivoSalida, "r");
            
            int bytesNoLeidos=0;
            
            if (numDato>0) {
                for (int i = 0; i < numDato; i++) {
                    bytesNoLeidos+=archivo.readByte() + 1 + 4;
                    if (bytesNoLeidos >= archivo.length())
                        return;
                    archivo.seek(bytesNoLeidos);
                }
                
            }
            
            String producto;
            Float precio;
            
            int longitudCadena;
            longitudCadena = archivo.readByte();
            
            byte[] caracteres;
            caracteres = new byte[longitudCadena];
            archivo.read(caracteres);
            producto = new String(caracteres);
            
            precio = archivo.readFloat();
            
            archivo.close();
            
            System.out.println("Producto: "+producto+" Precio: "+precio);
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Modifica el precio de un producto en la estructura.
     * 
     * Para poder modificarlo necesita el número de registro a editar y el
     * precio nuevo.
     * @param numDato número de registro
     * @param nuevoPrecio nuevo precio
     */
    public void cambiarPrecio(int numDato, float nuevoPrecio) {
        try {
            
            RandomAccessFile archivo = new RandomAccessFile(archivoSalida, "rw");
            
            int bytesNoLeidos=0;
            
            if (numDato>0) {
                for (int i = 0; i < numDato; i++) {
                    bytesNoLeidos+=archivo.readByte() + 1 + 4;
                    if (bytesNoLeidos >= archivo.length())
                        return;
                    archivo.seek(bytesNoLeidos);
                }
                
            }
            
            int longitudCadena;
            longitudCadena = archivo.readByte();
            
            archivo.seek(archivo.getFilePointer()+longitudCadena);
            
            archivo.writeFloat(nuevoPrecio);
            
            archivo.close();
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LecturaBinaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Constructor que inicializa la lista de productos
     */
    public LecturaBinaria() {
        productos = new ArrayList();
        insertarProductos();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LecturaBinaria l = new LecturaBinaria();

    }
    
}
