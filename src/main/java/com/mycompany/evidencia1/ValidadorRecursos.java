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
import java.nio.file.StandardOpenOption;

/**
 * Clase utilitaria para validar que existan la carpeta /db y los archivos CSV necesarios.
 * Si faltan, los crea. Asimismo, genera un admin por defecto en admins.csv.
 */
public class ValidadorRecursos {
    private static final String CARPETA_DB = "db";
    private static final String RUTA_ADMINS = CARPETA_DB + "/admins.csv";
    private static final String RUTA_DOCTORS = CARPETA_DB + "/doctors.csv";
    private static final String RUTA_PATIENTS = CARPETA_DB + "/patients.csv";
    private static final String RUTA_CITAS = CARPETA_DB + "/citas.csv";
    private static final String RUTA_GITIGNORE = CARPETA_DB + "/.gitignore";

    /**
     * Valida existencia de carpeta y archivos. Si no existen, los crea.
     */
    public static void validarCarpetaYArchivos() {
        try {
            Path carpetaDb = Paths.get(CARPETA_DB);
            if (!Files.exists(carpetaDb)) {
                Files.createDirectory(carpetaDb);
            }
            // Crear .gitignore con contenido "*"
            Path gitignore = Paths.get(RUTA_GITIGNORE);
            if (!Files.exists(gitignore)) {
                Files.write(gitignore, "*".getBytes(), StandardOpenOption.CREATE);
            }
            // admins.csv
            Path admins = Paths.get(RUTA_ADMINS);
            if (!Files.exists(admins)) {
                // Crear admin por defecto: id=admin, password=admin, nombre=Administrador
                Files.write(admins, "admin,Administrador,admin\n".getBytes(), StandardOpenOption.CREATE);
            }
            // doctors.csv
            Path doctors = Paths.get(RUTA_DOCTORS);
            if (!Files.exists(doctors)) {
                Files.createFile(doctors);
            }
            // patients.csv
            Path patients = Paths.get(RUTA_PATIENTS);
            if (!Files.exists(patients)) {
                Files.createFile(patients);
            }
            // citas.csv
            Path citas = Paths.get(RUTA_CITAS);
            if (!Files.exists(citas)) {
                Files.createFile(citas);
            }
        } catch (IOException e) {
            System.err.println("Error al validar recursos: " + e.getMessage());
        }
    }
}
