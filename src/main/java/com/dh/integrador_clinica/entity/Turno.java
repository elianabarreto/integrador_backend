package com.dh.integrador_clinica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "turnos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //(fetch = FetchType.LAZY)
    private Odontologo odontologo;

    @ManyToOne
    private Paciente paciente;

    private LocalDate fecha;

}
