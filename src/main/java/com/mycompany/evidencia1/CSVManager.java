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
import java.io.IOException;
import java.util.List;

/**
 * Interfaz para manejo genérico de archivos CSV.
 */
public interface CSVManager {
    /**
     * Lee todas las líneas del archivo en la ruta especificada.
     * @param ruta Ruta al archivo CSV.
     * @return Lista de líneas (cada línea como String). Si hay error, retorna lista vacía.
     */
    List<String> leer(String ruta) throws IOException;

    /**
     * Escribe la lista de líneas en el archivo de la ruta especificada.
     * @param ruta   Ruta al archivo CSV.
     * @param datos  Lista de líneas a escribir. Sobrescribe el archivo.
     * @throws IOException
     */
    void escribir(String ruta, List<String> datos) throws IOException;
}
