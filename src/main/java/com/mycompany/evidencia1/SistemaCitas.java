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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase singleton que administra listas de Admin, Doctor, Paciente y Cita.
 * Se encarga de cargar/guardar los CSV y ofrecer métodos para crear entidades.
 */
public class SistemaCitas {
    private static SistemaCitas instancia = null;

    private List<Admin> listaAdmins;
    private List<Doctor> listaDoctores;
    private List<Paciente> listaPacientes;
    private List<Cita> listaCitas;

    private CSVManager csvManager;

    // Rutas a los archivos CSV
    private final String RUTA_ADMINS = "db/admins.csv";
    private final String RUTA_DOCTORS = "db/doctors.csv";
    private final String RUTA_PATIENTS = "db/patients.csv";
    private final String RUTA_CITAS = "db/citas.csv";

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private SistemaCitas() {
        csvManager = new CSVFileManager();
        listaAdmins = new ArrayList<>();
        listaDoctores = new ArrayList<>();
        listaPacientes = new ArrayList<>();
        listaCitas = new ArrayList<>();
    }

    /**
     * Obtiene la instancia única de SistemaCitas.
     */
    public static SistemaCitas getInstancia() {
        if (instancia == null) {
            instancia = new SistemaCitas();
        }
        return instancia;
    }

    /**
     * Carga datos de los CSV al iniciar la aplicación.
     */
    public void cargarDatos() {
        cargarAdmins();
        cargarDoctores();
        cargarPacientes();
        cargarCitas();
    }

    private void cargarAdmins() {
        try {
            List<String> lineas = csvManager.leer(RUTA_ADMINS);
            for (String linea : lineas) {
                if (linea.trim().isEmpty()) continue;
                String[] campos = linea.split(",", 3);
                // Formato: id,nombre,password
                String id = campos[0];
                String nombre = campos[1];
                String password = campos[2];
                listaAdmins.add(new Admin(id, nombre, password));
                System.out.println("*** TIP *** ingresar con Usuario ID: "+id+" passw: "+password);
            }
        } catch (IOException e) {
            System.err.println("Error cargando admins: " + e.getMessage());
        }
    }

    private void cargarDoctores() {
        try {
            List<String> lineas = csvManager.leer(RUTA_DOCTORS);
            for (String linea : lineas) {
                if (linea.trim().isEmpty()) continue;
                String[] campos = linea.split(",", 3);
                // Formato: id,nombre,especialidad
                String id = campos[0];
                String nombre = campos[1];
                String especialidad = campos[2];
                listaDoctores.add(new Doctor(id, nombre, especialidad));
            }
        } catch (IOException e) {
            System.err.println("Error cargando doctores: " + e.getMessage());
        }
    }

    private void cargarPacientes() {
        try {
            List<String> lineas = csvManager.leer(RUTA_PATIENTS);
            for (String linea : lineas) {
                if (linea.trim().isEmpty()) continue;
                String[] campos = linea.split(",", 2);
                // Formato: id,nombre
                String id = campos[0];
                String nombre = campos[1];
                listaPacientes.add(new Paciente(id, nombre));
            }
        } catch (IOException e) {
            System.err.println("Error cargando pacientes: " + e.getMessage());
        }
    }

    private void cargarCitas() {
        try {
            List<String> lineas = csvManager.leer(RUTA_CITAS);
            for (String linea : lineas) {
                if (linea.trim().isEmpty()) continue;
                Cita c = Cita.fromCSV(linea, listaDoctores, listaPacientes);
                if (c != null) {
                    listaCitas.add(c);
                }
            }
        } catch (IOException e) {
            System.err.println("Error cargando citas: " + e.getMessage());
        }
    }

    /**
     * Guarda todas las listas en sus respectivos CSV.
     */
    public void guardarDatos() {
        guardarAdmins();
        guardarDoctores();
        guardarPacientes();
        guardarCitas();
    }

    private void guardarAdmins() {
        List<String> lineas = new ArrayList<>();
        for (Admin a : listaAdmins) {
            // id,nombre,password
            lineas.add(a.getId() + "," + a.getNombre() + "," + a.getPassword());
        }
        try {
            csvManager.escribir(RUTA_ADMINS, lineas);
        } catch (IOException e) {
            System.err.println("Error guardando admins: " + e.getMessage());
        }
    }

    private void guardarDoctores() {
        List<String> lineas = new ArrayList<>();
        for (Doctor d : listaDoctores) {
            lineas.add(d.toCSV());
        }
        try {
            csvManager.escribir(RUTA_DOCTORS, lineas);
        } catch (IOException e) {
            System.err.println("Error guardando doctores: " + e.getMessage());
        }
    }

    private void guardarPacientes() {
        List<String> lineas = new ArrayList<>();
        for (Paciente p : listaPacientes) {
            lineas.add(p.toCSV());
        }
        try {
            csvManager.escribir(RUTA_PATIENTS, lineas);
        } catch (IOException e) {
            System.err.println("Error guardando pacientes: " + e.getMessage());
        }
    }

    private void guardarCitas() {
        List<String> lineas = new ArrayList<>();
        for (Cita c : listaCitas) {
            lineas.add(c.toCSV());
        }
        try {
            csvManager.escribir(RUTA_CITAS, lineas);
        } catch (IOException e) {
            System.err.println("Error guardando citas: " + e.getMessage());
        }
    }

    /**
     * Valida credenciales de administrador.
     * @return true si encuentra correspondencia exacta id+password, false en caso contrario.
     */
    public boolean validarAdmin(String id, String password) {
        for (Admin a : listaAdmins) {
            if (a.getId().equals(id) && a.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Crea un nuevo doctor y lo agrega a la lista.
     */
    public Doctor crearDoctor(String id, String nombre, String especialidad) {
        Doctor d = new Doctor(id, nombre, especialidad);
        listaDoctores.add(d);
        return d;
    }

    /**
     * Crea un nuevo paciente y lo agrega a la lista.
     */
    public Paciente crearPaciente(String id, String nombre) {
        Paciente p = new Paciente(id, nombre);
        listaPacientes.add(p);
        return p;
    }

    /**
     * Crea una nueva cita y la agrega a la lista.
     */
    public Cita crearCita(String id, LocalDateTime fechaHora, String motivo,
                         Doctor doctor, Paciente paciente) {
        Cita c = new Cita(id, fechaHora, motivo, doctor, paciente);
        listaCitas.add(c);
        return c;
    }

    /**
     * Busca un doctor por su ID. Retorna null si no existe.
     */
    public Doctor obtenerDoctorPorId(String id) {
        for (Doctor d : listaDoctores) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    /**
     * Busca un paciente por su ID. Retorna null si no existe.
     */
    public Paciente obtenerPacientePorId(String id) {
        for (Paciente p : listaPacientes) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Retorna la lista completa de citas.
     */
    public List<Cita> listarCitas() {
        return listaCitas;
    }
}
