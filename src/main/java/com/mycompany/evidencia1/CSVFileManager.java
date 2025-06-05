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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación concreta de CSVManager usando java.nio.file
 */
public class CSVFileManager implements CSVManager {
    @Override
    public List<String> leer(String ruta) throws IOException {
        Path path = Paths.get(ruta);
        if (!Files.exists(path)) {
            // Si no existe, retornamos lista vacía
            return new ArrayList<>();
        }
        return Files.readAllLines(path);
    }

    @Override
    public void escribir(String ruta, List<String> datos) throws IOException {
        Path path = Paths.get(ruta);
        // Si la carpeta padre no existe, la creamos
        if (path.getParent() != null && !Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        Files.write(path, datos);
    }
}
