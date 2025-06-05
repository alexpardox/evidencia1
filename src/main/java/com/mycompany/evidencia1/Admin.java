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
public class Admin extends Usuario {
    private String password;

    public Admin(String id, String nombre, String password) {
        super(id, nombre);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
