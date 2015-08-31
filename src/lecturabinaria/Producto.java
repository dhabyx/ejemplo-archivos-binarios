/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturabinaria;

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class Producto {
    private String nombre;
    private Float precio;

    public Producto() {
        nombre = null;
        precio = 0.0f;
    }
    
    public Producto(String n, Float p) {
        nombre = n;
        precio = p;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
}
