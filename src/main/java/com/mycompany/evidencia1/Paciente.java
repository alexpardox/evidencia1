/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.evidencia1;

/**
 *
 * @author alexpardox
 */
public class Paciente extends Usuario {

    public Paciente(String id, String nombre) {
        super(id, nombre);
    }

    /**
     * Convierte el objeto Paciente a su representación CSV: id,nombre
     */
    public String toCSV() {
        return id + "," + nombre;
    }

    @Override
    public String toString() {
        return "Paciente[ID=" + id + ", Nombre=" + nombre + "]";
    }
}
