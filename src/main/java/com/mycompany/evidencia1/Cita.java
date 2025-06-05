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

public class Cita {
    private String id;
    private LocalDateTime fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Cita(String id, LocalDateTime fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Convierte el objeto Cita a su representación CSV:
     * id,fechaHora_formateado,motivo,doctorId,pacienteId
     */
    public String toCSV() {
        return id + "," + fechaHora.format(FORMATO) + "," + motivo + "," +
               doctor.getId() + "," + paciente.getId();
    }

    @Override
    public String toString() {
        return "Cita[ID=" + id +
               ", FechaHora=" + fechaHora.format(FORMATO) +
               ", Motivo=" + motivo +
               ", Doctor=" + doctor.getNombre() +
               ", Paciente=" + paciente.getNombre() +
               "]";
    }

    /**
     * Parsea una línea CSV a un objeto Cita. Se usa en SistemaCitas al cargar datos.
     * Formato esperado: id,yyyy-MM-dd HH:mm,motivo,doctorId,pacienteId
     */
    public static Cita fromCSV(String linea, java.util.List<Doctor> listaDoctores,
                               java.util.List<Paciente> listaPacientes) {
        try {
            String[] campos = linea.split(",", 5);
            String id = campos[0];
            LocalDateTime fechaHora = LocalDateTime.parse(campos[1], FORMATO);
            String motivo = campos[2];
            String doctorId = campos[3];
            String pacienteId = campos[4];

            // Buscar doctor y paciente por ID
            Doctor doc = null;
            for (Doctor d : listaDoctores) {
                if (d.getId().equals(doctorId)) {
                    doc = d;
                    break;
                }
            }
            Paciente pac = null;
            for (Paciente p : listaPacientes) {
                if (p.getId().equals(pacienteId)) {
                    pac = p;
                    break;
                }
            }
            if (doc != null && pac != null) {
                return new Cita(id, fechaHora, motivo, doc, pac);
            } else {
                return null; // Relación inválida
            }
        } catch (Exception e) {
            System.err.println("Error parseando Cita desde CSV: " + e.getMessage());
            return null;
        }
    }
}
