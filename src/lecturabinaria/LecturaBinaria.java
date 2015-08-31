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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class LecturaBinaria {
    
    private final String archivoEntrada = "entrada.data";
    private final String archivoSalida = "salida.data";
    
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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LecturaBinaria l = new LecturaBinaria();
        l.escrituraSecuencial();
    }
    
}
