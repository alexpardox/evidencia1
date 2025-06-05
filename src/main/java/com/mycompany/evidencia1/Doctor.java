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
public class Doctor extends Usuario {
    private String especialidad;

    public Doctor(String id, String nombre, String especialidad) {
        super(id, nombre);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Convierte el objeto Doctor a su representación CSV: id,nombre,especialidad
     */
    public String toCSV() {
        return id + "," + nombre + "," + especialidad;
    }

    @Override
    public String toString() {
        return "Doctor[ID=" + id + ", Nombre=" + nombre + ", Especialidad=" + especialidad + "]";
    }
}
