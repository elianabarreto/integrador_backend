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

    @ManyToOne //(fetch = FetchType.LAZY) en ManyToOne para que tenga carga LAZY, hay que especificar
    //por defecto queda EAGER, cuando listamos turno nos trae todos los datos de odontologo y paciente
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;

    //TODO: consultar a profe si NULLABLE = FALSE o esta opción de abajo, cual es mejor, lo mismo para paciente
    //@JoinColumn(name = "odontologo_id", referencedColumnName = "id")

    @JoinColumn(name = "paciente_id", nullable = false)
    @ManyToOne
    private Paciente paciente;

    private LocalDate fecha;

}
