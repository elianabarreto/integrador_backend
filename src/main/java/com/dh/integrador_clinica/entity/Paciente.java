package com.dh.integrador_clinica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private String dni;
    private LocalDate fecha_ingreso;

    public String toString() {
        return "Paciente con " + "id: " + id + ", nombre: " + nombre + ", apellido: " + apellido +
                ", dni: " + dni + ", domicilio: " + domicilio + ", fecha ingreso: " + fecha_ingreso;
    }
}
