package com.dh.integrador_clinica.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    // Cuando listamos turno nos trae todos los datos de odontologo y paciente
    // porque por defecto queda (fetch = FetchType.EAGER)
    // En ManyToOne para que tenga carga LAZY, hay que especificar LAZY
    @ManyToOne
    @JoinColumn(name = "odontologo_id", nullable = false)
    @JsonIgnoreProperties("turnoSet")
    private Odontologo odontologo;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonIgnoreProperties("turnoSet")
    private Paciente paciente;

    private LocalDate fecha;
}
