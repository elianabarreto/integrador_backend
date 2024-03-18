package com.dh.integrador_clinica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    private String dni;

    private LocalDate fecha_ingreso;

    //TODO: en esta clase "Paciente", no hay que hacer esto, como con odontólogos? Porque está en turnos
    //@OneToMany(mappedBy = "odontologo")
    //private Set<Turno> turnoSet = new HashSet<>();
    //private Set < Publicacion > publications = HashSet< Publicacion >();

    public String toString() {
        return "Paciente con " + "id: " + id + ", nombre: " + nombre + ", apellido: " + apellido +
                ", dni: " + dni + ", domicilio: " + domicilio + ", fecha ingreso: " + fecha_ingreso;
    }
}
