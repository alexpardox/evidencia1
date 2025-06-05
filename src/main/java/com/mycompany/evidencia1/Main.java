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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que contiene el método main y controla el menú de la aplicación.
 */
public class Main {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static SistemaCitas sistema = SistemaCitas.getInstancia();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 1) Validar recursos (carpeta /db y archivos)
        ValidadorRecursos.validarCarpetaYArchivos();

        // 2) Cargar datos desde CSV
        sistema.cargarDatos();

        // 3) Login de administrador
        boolean accesoConcedido = false;
        System.out.println("===== Bienvenido al Sistema de Administración de Citas =====");
        while (!accesoConcedido) {
            System.out.println("Ingrese ID de Administrador: ");
            String id = scanner.nextLine().trim();
            System.out.println("Ingrese Contraseña: ");
            String pwd = scanner.nextLine().trim();
            if (sistema.validarAdmin(id, pwd)) {
                accesoConcedido = true;
                System.out.println("Acceso concedido. ¡Bienvenido!");
            } else {
                System.out.println("Credenciales inválidas. Intente nuevamente.\n");
            }
        }

        // 4) Menú principal
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1":
                    altaDoctor();
                    break;
                case "2":
                    altaPaciente();
                    break;
                case "3":
                    crearCita();
                    break;
                case "4":
                    listarCitas();
                    break;
                case "5":
                    salir = true;
                    // Antes de salir, guardamos datos
                    sistema.guardarDatos();
                    System.out.println("Guardando datos y saliendo. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.\n");
                    break;
            }
            // Guardar cambios tras cada operación
            if (!salir) {
                sistema.guardarDatos();
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n----- Menú Principal -----");
        System.out.println("1. Dar de alta Doctor");
        System.out.println("2. Dar de alta Paciente");
        System.out.println("3. Crear Cita");
        System.out.println("4. Listar Citas");
        System.out.println("5. Salir");
        System.out.println("Seleccione una opción: ");
    }

    private static void altaDoctor() {
        try {
            System.out.println("\n-- Alta Doctor --\nID: ");
            String id = scanner.nextLine().trim();
            System.out.println("Nombre completo: ");
            String nombre = scanner.nextLine().trim();
            System.out.println("Especialidad: ");
            String especialidad = scanner.nextLine().trim();

            Doctor d = sistema.crearDoctor(id, nombre, especialidad);
            System.out.println("Doctor registrado: " + d);
        } catch (Exception e) {
            System.err.println("Error al dar de alta Doctor: " + e.getMessage());
        }
    }

    private static void altaPaciente() {
        try {
            System.out.println("\n-- Alta Paciente --\nID: ");
            String id = scanner.nextLine().trim();
            System.out.println("Nombre completo: ");
            String nombre = scanner.nextLine().trim();

            Paciente p = sistema.crearPaciente(id, nombre);
            System.out.println("Paciente registrado: " + p);
        } catch (Exception e) {
            System.err.println("Error al dar de alta Paciente: " + e.getMessage());
        }
    }

    private static void crearCita() {
        try {
            System.out.println("\n-- Crear Cita --\nID de la cita: ");
            String id = scanner.nextLine().trim();
            System.out.println("Fecha y hora (formato yyyy-MM-dd HH:mm): ");
            String fechaStr = scanner.nextLine().trim();
            LocalDateTime fechaHora;
            try {
                fechaHora = LocalDateTime.parse(fechaStr, FORMATO);
            } catch (DateTimeParseException e) {
                System.err.println("Formato de fecha inválido. Use yyyy-MM-dd HH:mm");
                return;
            }
            System.out.println("Motivo de la cita: ");
            String motivo = scanner.nextLine().trim();

            // Seleccionar doctor
            System.out.println("ID del Doctor: ");
            String idDoctor = scanner.nextLine().trim();
            Doctor d = sistema.obtenerDoctorPorId(idDoctor);
            if (d == null) {
                System.out.println("Doctor no encontrado. Alta de cita cancelada.");
                return;
            }

            // Seleccionar paciente
            System.out.println("ID del Paciente: ");
            String idPaciente = scanner.nextLine().trim();
            Paciente p = sistema.obtenerPacientePorId(idPaciente);
            if (p == null) {
                System.out.println("Paciente no encontrado. Alta de cita cancelada.");
                return;
            }

            Cita c = sistema.crearCita(id, fechaHora, motivo, d, p);
            System.out.println("Cita creada: " + c);
        } catch (Exception e) {
            System.err.println("Error al crear Cita: " + e.getMessage());
        }
    }

    private static void listarCitas() {
        System.out.println("\n-- Listado de Citas --");
        List<Cita> citas = sistema.listarCitas();
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            for (Cita c : citas) {
                System.out.println(c);
            }
        }
    }
}
